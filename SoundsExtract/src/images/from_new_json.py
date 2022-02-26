from PIL import Image
import os
import ray

INPUT_DIR = "./to_upload/"

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


if __name__ == "__main__":
    ray.init()
    futures = []
    for champ in os.listdir(INPUT_DIR):
        for file in os.listdir(f'D:/Modding/LOL2/{champ.capitalize()}/assets/characters/{champ}/hud/'):
            if os.path.isfile(f'D:/Modding/LOL2/{champ.capitalize()}/assets/characters/{champ}/hud/{file}') and 'circle' in file:
                futures.append(convert_image.remote(
                    f'D:/Modding/LOL2/{champ.capitalize()}/assets/characters/{champ}/hud/{file}',
                    f'./output/{file.replace(".dds", ".webp")}'
                ))
    ray.get(futures)
