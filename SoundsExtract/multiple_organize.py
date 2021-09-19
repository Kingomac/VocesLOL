import os
from pydub import AudioSegment
import shutil
from delete import remove_files_from_dir
import ray
import input_champs


def get_skin_num(skin: str):
    try:
        return int(skin.replace('skin', ''))
    except:
        print("Error getting skin folder number for skin " + skin)
        exit(-1)


def check_subdirs(input_dir: str):
    print("Checking subdirs...")
    unidentified = []
    for i in os.listdir(input_dir):
        if os.path.isdir(os.path.join(input_dir, i)):
            subdirs = False
            for j in os.listdir(os.path.join(input_dir, i)):
                if os.path.isdir(os.path.join(input_dir, i, j)):
                    subdirs = True
                    for k in os.listdir(os.path.join(input_dir, i, j)):
                        print("Moving " + os.path.join(input_dir, i, j, k) +
                              " to " + os.path.join(input_dir, i, k))
                        shutil.move(os.path.join(input_dir, i, j, k),
                                    os.path.join(input_dir, i, k))
                    os.rmdir(os.path.join(input_dir, i, j))
                    print("Subdir " + os.path.join(input_dir, i, j) + " removed")
            if subdirs:
                print("\t" + i)
        else:
            unidentified.append(i)
    if len(unidentified) > 0:
        os.mkdir(os.path.join(input_dir, "___Others"))
        for i in unidentified:
            shutil.move(os.path.join(input_dir, i),
                        os.path.join(input_dir, "___Others", i))


@ray.remote
def soundcopy(i: str, j: str, input_dir: str, output_dir: str, cont: float, champ: str):
    split = i.split("_")
    output_file_name = split[3].replace('BasicAttack', 'Basic').replace(
        champ[0].upper() + champ[1:], '').replace('VO', '')
    k = 4
    while k < len(split):
        output_file_name += " " + split[k]
        k += 1
    if cont > 1:
        if output_file_name[len(output_file_name)-1].isnumeric():
            output_file_name += "-"
        output_file_name += str(cont)
    output_file_name += ".mp3"
    output_path = os.path.join(output_dir, output_file_name)
    ogg = AudioSegment.from_ogg(os.path.join(input_dir, i, j))
    ogg.export(output_path, format='mp3')
    return " " + j + " -> " + os.path.join(output_dir, output_file_name) + "\n"


CHAMPS = input_champs.CHAMPS
MODDING_DIR = 'D:\\Modding\\LOL\\'
BNK_EXTRACT = ".\\bnk-extract.exe"
OUTPUT_DIR = "./files/"

# sounds\wwise2016\vo\es_es\characters\varus\skins

if __name__ == '__main__':
    ray.init()
    for champ in CHAMPS:
        champ_lower = champ.lower()
        print("----------------------------------")
        print("Organizing champ " + champ_lower.capitalize())
        champ_dir_es = os.path.join(MODDING_DIR, champ_lower + "_es", 'assets',
                                    'sounds', 'wwise2016', 'vo', 'es_es', 'characters', champ_lower, "skins")
        for skin in os.listdir(champ_dir_es):
            print(champ_lower + " skin " + skin)
            bin_file = ''
            output_dir = ''
            if skin == 'base':
                bin_file = os.path.join(
                    MODDING_DIR, champ_lower, 'data', 'characters', champ_lower, 'skins', 'skin0.bin')
            else:
                bin_file = os.path.join(
                    MODDING_DIR, champ_lower, 'data', 'characters', champ_lower, 'skins', 'skin' + str(get_skin_num(skin)) + ".bin")

            os.system(BNK_EXTRACT + " -a " + os.path.join(champ_dir_es, skin, "_".join(
                [champ_lower, skin, 'vo', 'audio.wpk'])) + " -e " + os.path.join(champ_dir_es, skin, "_".join([champ_lower, skin, 'vo', 'events.bnk']))
                + " -b " + bin_file + " --oggs-only")

            output_dir = ''
            if skin == 'base':
                output_dir = os.path.join(OUTPUT_DIR, champ_lower)
            else:
                output_dir = os.path.join(
                    OUTPUT_DIR, champ_lower + "_skin_" + str(get_skin_num(skin)))
            os.mkdir(output_dir)
            input_dir = "./output/"
            check_subdirs(input_dir)
            input_dirs = os.listdir(input_dir)
            futures = []
            for i in input_dirs:
                print(i)
                cont = 1
                for j in os.listdir(os.path.join(input_dir, i)):
                    futures.append(soundcopy.remote(
                        i, j, input_dir, output_dir, cont, champ))
                    cont += 1
            print("".join(ray.get(futures)))
            remove_files_from_dir("./output/")
