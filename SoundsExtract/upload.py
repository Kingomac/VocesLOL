import os
import shutil
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
import time
import math
import sort

input_dir = "./files/"
champion = "announcer_tournament_female"

if __name__ == '__main__':
    cred = credentials.Certificate(
        "./voces-lol-firebase-adminsdk-82xgq-aacb54374b.json")
    firebase_admin.initialize_app(cred, {
        'storageBucket': 'voces-lol.appspot.com'
    })

    bucket = storage.bucket()
    urls_file = open("./" + champion + ".json", 'w')
    urls_file.write("{\n")
    list_dir = sort.listdir(input_dir)
    index = 0
    prev_filename = '.mp3'

    for f in list_dir:
        if not f.endswith('.mp3'):
            continue
        blob = bucket.blob(champion + "/" + f)
        blob.upload_from_filename(os.path.join(input_dir, f))
        print(str(round(100 * index / len(list_dir), 1)) + "% -> " + f)
        blob.make_public()

        if not f.startswith(prev_filename):
            prev_filename = f.split('-')[0].replace('.mp3', '')
            if index > 0:
                urls_file.write('  ],\n')
            urls_file.write(
                '  "' + f.replace('.mp3', '').replace('3D', '').replace('2D', '').replace(' ', '').replace('General', '').replace('cast', '').replace(champion.split('_')[0].capitalize(), '') + '":[\n')

        urls_file.write('\t"' + blob.public_url + '",\n')
        time.sleep(0.5)
        index += 1

    urls_file.write("  ]\n}\n")
    urls_file.close()
