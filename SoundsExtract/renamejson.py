import os

INPUT_DIR = './files/'
START_WITH_1 = 'Tutorial '
START_WITH_2 = 'Extra'

if __name__ == '__main__':
    for i in os.listdir(INPUT_DIR):
        print(i)
        if i.startswith(START_WITH_1):
            n = i.replace(START_WITH_1, '')
            os.rename(os.path.join(INPUT_DIR, i), os.path.join(INPUT_DIR, n))
        # elif i.startswith(START_WITH_2):
        #     n = i.replace(START_WITH_2, '')
        #     if not os.path.isfile(os.path.join(INPUT_DIR, i)):
        #         os.rename(os.path.join(INPUT_DIR, i),
        #                   os.path.join(INPUT_DIR, n))
