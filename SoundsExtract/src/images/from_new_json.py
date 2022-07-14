from PIL import Image
import os
import ray

INPUT_DIR = "./newjson/"
CHAMPS_DIR = "D:/Modding/LOL/"

"""

TODO I dont neeed now but it may be necessary :)

"""


@ray.remote
def convert_image(input_path: str, output_path: str):
    try:
        image = Image.open(input_path)
        image.save(output_path)
    except:
        try:
            image = Image.open(input_path.replace('circle', 'circle_0'))
            image.save(output_path)
        except:
            print(f'{input_path} and circle_0.dds missing')


def look_for_folder(champ: str):
    for i in os.listdir(CHAMPS_DIR):
        if champ == i.lower():
            return f"{CHAMPS_DIR}{i}"
    return ""


if __name__ == "__main__":
    ray.init()
    futures = []
    for champ in os.listdir(INPUT_DIR):
        tmp = champ.split('_')
        if len(tmp) > 2:
            skin = tmp[2].removesuffix(".json")
        else:
            skin = ""
        champ = champ.split('_')[0].removesuffix(".json")
        print(champ + " skin " + skin)
        folder = look_for_folder(champ)

        for file in os.listdir(f'{folder}/assets/characters/{champ}/hud/'):
            if os.path.isfile(f'{folder}/assets/characters/{champ}/hud/{file}') and 'circle' in file:
                if (skin != "" and skin in file) or (skin == ""):
                    futures.append(convert_image.remote(
                        f'{folder}/assets/characters/{champ}/hud/{file}',
                        f'./output/{file.replace(".dds", ".webp")}'
                    ))
    ray.get(futures)
