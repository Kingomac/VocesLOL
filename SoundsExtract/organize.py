import os
import shutil
import ray
from pydub import AudioSegment


INPUT_DIR = "./output/"
OUTPUT_DIR = "./files/gwen/"


@ray.remote
def soundcopy(i: str, j: str, cont: float):
    split = i.split("_")
    output_file_name = split[3]
    k = 4
    while k < len(split):
        output_file_name += " " + split[k]
        k += 1
    if cont > 1:
        if output_file_name[len(output_file_name)-1].isnumeric():
            output_file_name += "-"
        output_file_name += str(cont)
    output_file_name += ".mp3"
    output_path = os.path.join(OUTPUT_DIR, output_file_name)
    ogg = AudioSegment.from_ogg(os.path.join(INPUT_DIR, i, j))
    ogg.export(output_path, format='mp3')
    return " " + j + " -> " + os.path.join(OUTPUT_DIR, output_file_name)


def main():
    if not os.path.isdir(OUTPUT_DIR):
        os.mkdir(OUTPUT_DIR)
    for i in os.listdir(INPUT_DIR):
        print(i)
        cont = 1
        for j in os.listdir(os.path.join(INPUT_DIR, i)):
            # split = i.split("_")
            # output_file_name = split[3]
            # k = 4
            # while k < len(split):
            #     output_file_name += " " + split[k]
            #     k += 1
            # if cont > 1:
            #     output_file_name += str(cont)
            # output_file_name += ".mp3"
            # output_path = os.path.join(output_dir, output_file_name)
            # ogg = AudioSegment.from_ogg(os.path.join(input_dir, i, j))
            # ogg.export(output_path, format='mp3')
            # print(" ", j, " -> ", os.path.join(output_dir, output_file_name))
            print(ray.get(soundcopy.remote(i, j, cont)))
            cont += 1


def check_subdirs():
    print("Checking subdirs...")
    unidentified = []
    for i in os.listdir(INPUT_DIR):
        if os.path.isdir(os.path.join(INPUT_DIR, i)):
            subdirs = False
            for j in os.listdir(os.path.join(INPUT_DIR, i)):
                if os.path.isdir(os.path.join(INPUT_DIR, i, j)):
                    subdirs = True
                    for k in os.listdir(os.path.join(INPUT_DIR, i, j)):
                        print("Moving " + os.path.join(INPUT_DIR, i, j, k) +
                              " to " + os.path.join(INPUT_DIR, i, k))
                        shutil.move(os.path.join(INPUT_DIR, i, j, k),
                                    os.path.join(INPUT_DIR, i, k))
                    os.rmdir(os.path.join(INPUT_DIR, i, j))
                    print("Subdir " + os.path.join(INPUT_DIR, i, j) + " removed")
            if subdirs:
                print("\t" + i)
        else:
            unidentified.append(i)
    if len(unidentified) > 0:
        os.mkdir(os.path.join(INPUT_DIR, "___Others"))
        for i in unidentified:
            shutil.move(os.path.join(INPUT_DIR, i),
                        os.path.join(INPUT_DIR, "___Others", i))


if __name__ == "__main__":
    ray.init()
    check_subdirs()
    main()
    # ray.init()
    #futures = []

    # for i in os.listdir(input_dir):
    #    cont = 1
    #    for j in os.listdir(os.path.join(input_dir, i)):
    #        futures.append(soundcopy.remote(i, j, cont))
    #        cont += 1

    # ray.get(futures)
