import os

INPUT_DIR1 = 'D:/Projects/VocesLOL/app/src/main/res/raw/'
INPUT_DIR2 = "./urls3/"

if __name__ == '__main__':
    for i in os.listdir(INPUT_DIR1):
        if not os.path.isfile(os.path.join(INPUT_DIR2, i)):
            print(i)
