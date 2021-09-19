import sort
import os
import ujson

INPUT_DIR = "./urls3/"

if __name__ == '__main__':
    for i in os.listdir(INPUT_DIR):
        print("Sorting file", i)
        file = open(os.path.join(INPUT_DIR, i), 'r', encoding='utf-8')
        parsed: dict = ujson.loads(file.read())
        file.close()
        for j in parsed:
            parsed[j] = sort.winsort(parsed[j])
        newfile = open(os.path.join('./urls/', i), 'w')
        newfile.write(ujson.dumps(parsed, escape_forward_slashes=False))
        newfile.close()
