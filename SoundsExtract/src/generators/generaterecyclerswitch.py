import os

INPUT_DIR = "./newjson/"

if __name__ == '__main__':
    for i in os.listdir(INPUT_DIR):
        if os.path.isfile(os.path.join(INPUT_DIR, i)):
            print("Champ.{} -> R.drawable.".format(i.replace('.json', '').upper()))
