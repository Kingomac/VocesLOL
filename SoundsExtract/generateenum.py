import os
import sort
INPUT_DIR = "./files/"

if __name__ == '__main__':
    for i in sort.listdir(INPUT_DIR):
        if os.path.isdir(os.path.join(INPUT_DIR, i)):
            print(i.upper() + ",")
