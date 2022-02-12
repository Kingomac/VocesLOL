import sort
import os
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
import time
import json

INPUT_DIR = "./output/"
OUTPUT_DIR = "./newjson/"


def get_key(filename: str):
    word_beggings = ["FirstEncounter", "EnemyFirstEncounter", "Kill", "Attack", "Joke", "Death", "Laugh", "Dance", "Near", "Ping", "UseItem",
                     "Recall", "Taunt", "Move", "Basic", "Critical", "BuyItem", "Respawn", "Spell", "Other", "Animations", "Receive", "Passive", "SpecialWeapon", "Q", "W", "E", "R"]
    for i in word_beggings:
        if filename.startswith(i):
            return i.replace('.mp3', '')
    return "".join([i for i in filename.replace('.mp3', '') if not i.isdigit()])


def upload_skin(input_dir: str, champ: str, skin: str):
    if skin == "base":
        num = 0
        full_champ = champ
    else:
        num = skin.replace('skin', '')
        full_champ = f'{champ}_skin_{num}'
    print(f'Uploading {full_champ}')
    urls_lists = dict()

    index = 0
    key = ""
    sounds = sort.listdir(f'{input_dir}/{champ}/{skin}')

    for sound in sounds:
        blob = bucket.blob(f'{full_champ}/{sound}')
        blob.upload_from_filename(
            f'{input_dir}/{champ}/{skin}/{sound}', content_type='audio/mpeg')
        blob.make_public()

        newindex = get_key(sound)
        if key == "" or key != newindex:
            key = newindex
            if not key in urls_lists:
                urls_lists[key] = []

        urls_lists[key].append(blob.public_url)
        index += 1
        print(f'{index}/{len(sounds)} -> {sound} from {champ}/{skin}')
        time.sleep(0.5)
    with open(f"./{OUTPUT_DIR}/{full_champ}.json", 'w') as urls_file:
        json.dump(urls_lists, urls_file, separators=(',', ':'))


if __name__ == '__main__':
    cred = credentials.Certificate(
        "credentials\\prueba-d1c99-firebase-adminsdk-ogmm4-f38f4be1f2.json")
    firebase_admin.initialize_app(cred, {
        'storageBucket': 'prueba-d1c99.appspot.com'
    })

    bucket = storage.bucket()

    for champ in os.listdir(INPUT_DIR):
        if not os.path.isdir(f'{INPUT_DIR}/{champ}'):
            print(f'{INPUT_DIR}/{champ} is not a champ directory')
            continue
        for skin in os.listdir(f'{INPUT_DIR}/{champ}'):
            if not os.path.isdir(f'{INPUT_DIR}/{champ}/{skin}'):
                print(f'{INPUT_DIR}/{champ} is not a champ directory')
                continue
            upload_skin(INPUT_DIR, champ, skin)
