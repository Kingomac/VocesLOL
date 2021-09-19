import os
import time
import ray
import json


INPUT_DIR = "D:/Modding/LOL/client_es_assets/plugins/rcp-be-lol-game-data/global/es_es/v1/championsids/"
SOUNDS_DIR = "D:/Programs/LOL/Pendiente/ChampSelect/"


# @ray.remote
def rename(path: str, file: str):
    stream = open(os.path.join(path, file), 'r', encoding='utf-8')
    text = stream.read()
    stream.close()
    # print(text)
    parsed = json.loads(text)
    os.rename(os.path.join(path, file), os.path.join(
        path, str(parsed["id"]) + " - " + parsed["name"] + ".json"))
    try:
        os.rename(os.path.join(SOUNDS_DIR, str(
            parsed["id"]) + ".mp3"), os.path.join(SOUNDS_DIR, parsed["name"] + ".mp3"))
    except:
        print("Couldn't change " + str(parsed["id"]) + " sound file")
    return str(parsed["id"]) + " - " + parsed["name"]


if __name__ == "__main__":
    for i in os.listdir(INPUT_DIR):
        if os.path.isfile(os.path.join(INPUT_DIR, i)) and i.endswith('.json'):
            #print(ray.get(rename.remote(INPUT_DIR, i)))
            print(rename(INPUT_DIR, i))
