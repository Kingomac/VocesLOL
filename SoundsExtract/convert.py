from pydub import AudioSegment
import ray
import os

input_dir = "./files/"
#output_dir = "./mp3/"


@ray.remote
def convert(file):
    ogg = AudioSegment.from_file(os.path.join(input_dir, file))
    ogg.export(os.path.join(input_dir, file.replace(
        '.ogg', '.mp3')), format='mp3')
    os.remove(os.path.join(input_dir, file))
    return file.replace('.ogg', '.mp3')


if __name__ == "__main__":
    ray.init()
    for f in os.listdir(input_dir):
        print(ray.get(convert.remote(f)))
