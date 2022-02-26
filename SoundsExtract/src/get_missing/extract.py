"""
From LOL extracted champs files gets the missing skins
"""

import atexit
import os

MOD_DIR = "D:/Modding/LOL/"
BNK_EXTRACT = ".\\bnk-extract.exe"
OUTPUT_DIR = "./output/"

futures = []
log_file = open("./output/log.txt", 'w', encoding='utf-8')


@atexit.register
def close_file():
    log_file.close()


for champ_assets_dir in os.listdir(MOD_DIR):
    champ = champ_assets_dir.replace(".es_ES", "").lower()
    if champ_assets_dir.endswith("_ES"):
        if not os.path.isdir(f'{MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/'):
            log_file.write(
                f"Missing champ files for directory {MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/\n")
            continue
        for skin_dir in os.listdir(f'{MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/'):
            if skin_dir == "base":
                file_end = ""
                num = 0
            else:
                num = int(skin_dir.replace("skin", ""))
                file_end = "_skin_" + str(num)
            # check whether exists a json file for that skin
            if not os.path.isfile("./json/" + champ + file_end + ".json"):
                if skin_dir != "base":
                    output_name = f'skin{num}'
                else:
                    output_name = skin_dir

                r = os.system(f'{BNK_EXTRACT} -a "{MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/{skin_dir}/{champ}_{skin_dir}_vo_audio.wpk" -e "{MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/{skin_dir}/{champ}_{skin_dir}_vo_events.bnk" -b "{MOD_DIR}{champ_assets_dir.replace(".es_ES","")}/data/characters/{champ}/skins/skin{num}.bin"  -o "{OUTPUT_DIR}/{champ}/{output_name}/" --oggs-only')
                if r != 0:
                    log_file.write(
                        f'bnk-extract error: -a "{MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/{skin_dir}/{champ}_{skin_dir}_vo_audio.wpk" -e "{MOD_DIR}{champ_assets_dir}/assets/sounds/wwise2016/vo/es_es/characters/{champ}/skins/{skin_dir}/{champ}_{skin_dir}_vo_events.bnk" -b "{MOD_DIR}{champ_assets_dir.replace(".es_ES","")}/data/characters/{champ}/skins/skin{num}.bin" -o "{OUTPUT_DIR}/{champ}/{skin_dir}/"\n')

log_file.close()
