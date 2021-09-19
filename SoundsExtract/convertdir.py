from genericpath import isfile
from pydub import AudioSegment
import os
import ray

INPUT_DIR = './files/'


@ray.remote
def convert(source: str):
    file = AudioSegment.from_ogg(source)
    file.export(source.replace('.ogg', '.mp3'), format='mp3')
    os.remove(source)
    return source.replace('.ogg', '.mp3')


if __name__ == '__main__':
    ray.init()
    for i in os.listdir(INPUT_DIR):
        if(os.path.isfile(os.path.join(INPUT_DIR, i))) and i.endswith('.ogg'):
            print(ray.get(convert.remote(os.path.join(INPUT_DIR, i))))
