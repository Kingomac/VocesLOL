import os
import ujson

INPUT_DIR = "./newjson/"

if __name__ == '__main__':
    for i in os.listdir(INPUT_DIR):
        if i.endswith('.json'):
            file = open(os.path.join(INPUT_DIR, i), 'r', encoding='utf-8')
            parsed = ujson.loads(file.read())
            file.close()
            file = open(os.path.join(INPUT_DIR, i), 'w', encoding='utf-8')
            file.write(ujson.dumps(parsed, escape_forward_slashes=False))
            file.close()
