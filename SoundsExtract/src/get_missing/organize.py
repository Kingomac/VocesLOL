import os
import shutil
import ray
from pydub import AudioSegment
import re

INPUT_DIR = "./output/"


def check_subdirs(directory: str):
    """
        Checks wheter exists any champ that didn't get all of their sounds sorted with de bin file and groups them into the __Others folder
    """
    unidentified = []
    for i in os.listdir(directory):
        if os.path.isdir(os.path.join(directory, i)):
            subdirs = False
            for j in os.listdir(os.path.join(directory, i)):
                if os.path.isdir(os.path.join(directory, i, j)):
                    subdirs = True
                    for k in os.listdir(os.path.join(directory, i, j)):
                        print("Moving " + os.path.join(directory, i, j, k) +
                              " to " + os.path.join(directory, i, k))
                        shutil.move(os.path.join(directory, i, j, k),
                                    os.path.join(directory, i, k))
                    os.rmdir(os.path.join(directory, i, j))
                    print("Subdir " + os.path.join(directory, i, j) + " removed")
            if subdirs:
                print("\t" + i)
        else:
            unidentified.append(i)
    if len(unidentified) > 0:
        os.mkdir(os.path.join(directory, "___Others"))
        for i in unidentified:
            shutil.move(os.path.join(directory, i),
                        os.path.join(directory, "___Others", i))


def delete_from_string(term: str, *look_for: str) -> str:
    toret = term
    for i in look_for:
        toret = toret.replace(i, "")
    return toret


def sound_name_tweak(sound: str, champ_name: str) -> str:
    output: str = "".join(sound.split("_")[3:])
    output = re.compile(champ_name, re.IGNORECASE).sub(
        "", output)  # Replace champ_name case insensitive
    output = delete_from_string(output, '2D', '3D', 'General')
    output = output.replace('BasicAttack', 'Basic').replace(
        'CritAttack', 'Critical').replace('OnBuff', 'Buff')
    return output


def generate_output_filename(inputname: str, champ_name: str, cont: int) -> str:
    output = sound_name_tweak(inputname, champ_name)
    if cont == 1:
        return f'{output}.mp3'
    return f'{output}{cont}.mp3'


@ray.remote
def soundcopy(input_dir: str, champ_name: str, skin: str, sound_name: str, sound_file: str, cont: int):
    output_filename = generate_output_filename(sound_name, champ_name, cont)
    output_path = f'{input_dir}/{champ_name}/{skin}/{output_filename}'
    ogg = AudioSegment.from_ogg(
        f'{input_dir}/{champ_name}/{skin}/{sound_name}/{sound_file}')
    ogg.export(output_path, format='mp3')
    return output_filename + "\n"


if __name__ == '__main__':
    ray.init()
    futures = []
    for champ in os.listdir(INPUT_DIR):  # i -> champ
        if not os.path.isdir(f'{INPUT_DIR}/{champ}'):
            continue
        for skin in os.listdir(f'{INPUT_DIR}/{champ}/'):
            check_subdirs(f'{INPUT_DIR}/{champ}/{skin}')
            for sound_dir in os.listdir(f'{INPUT_DIR}/{champ}/{skin}'):
                cont = 1
                last_tweak = ""
                for sound in os.listdir(f'{INPUT_DIR}/{champ}/{skin}/{sound_dir}'):
                    if sound_name_tweak(sound, champ) != last_tweak:
                        cont = 1
                    futures.append(soundcopy.remote(
                        INPUT_DIR, champ, skin, sound_dir, sound, cont))
                    last_tweak = sound_name_tweak(sound, champ)
                    cont += 1
    for i in ray.get(futures):
        print(i)
    for champ in os.listdir(INPUT_DIR):
        if not os.path.isdir(f'{INPUT_DIR}/{champ}'):
            continue
        for skin in os.listdir(f'{INPUT_DIR}/{champ}/'):
            for entry in os.listdir(f'{INPUT_DIR}/{champ}/{skin}'):
                if os.path.isdir(f'{INPUT_DIR}/{champ}/{skin}/{entry}'):
                    shutil.rmtree(f'{INPUT_DIR}/{champ}/{skin}/{entry}')
