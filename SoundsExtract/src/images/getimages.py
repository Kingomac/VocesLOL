from PIL import Image
import os
import ray
import src.input_champs as input_champs


output_dir = "./output/"
input_dir = "D:/Modding/LOL2/"
check_dirs = [i.lower() for i in input_champs.CHAMPS]


@ray.remote
def convert_image(images_path, filename):
    image = Image.open(os.path.join(images_path, filename))
    image.save(os.path.join(output_dir, filename.replace(".dds", ".webp")))
    return os.path.join(output_dir, filename.replace(".dds", ".webp"))


if __name__ == '__main__':
    ray.init()
    for i in os.listdir(input_dir):
        if i in check_dirs:
            images_path = os.path.join(
                input_dir, i, "assets", "characters", i, "hud")
            for j in os.listdir(images_path):
                if "." in j:
                    print(ray.get(convert_image.remote(images_path, j)))
