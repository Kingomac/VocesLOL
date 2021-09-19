import os
import ujson

input_dir = "D:/Projects/VocesLOL/app/src/main/res/raw/"

if __name__ == "__main__":
    cont = 0
    for i in os.listdir(input_dir):
        file = open(os.path.join(input_dir, i), 'r', encoding='utf-8')
        content = file.read()
        file.close()
        parsed = ujson.loads(content)

        for key in parsed:
            cont += len(parsed[key])

    print(cont)
