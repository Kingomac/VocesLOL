import os

INPUT_DIR = './Pendiente/ChampSelect/'

if __name__ == '__main__':
    for i in os.listdir(INPUT_DIR):
        os.rename(os.path.join(INPUT_DIR, i), os.path.join(
            INPUT_DIR, i.replace('SelectSelect.mp3', '') + "Select.mp3"))
