import os
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
import time
import sort

INPUT_DIR = "./files/"

if __name__ == '__main__':
    cred = credentials.Certificate(
        "./voces-lol-firebase-adminsdk-82xgq-aacb54374b.json")
    firebase_admin.initialize_app(cred, {
        'storageBucket': 'voces-lol.appspot.com'
    })

    bucket = storage.bucket()
    to_upload = os.listdir(INPUT_DIR)
    total_index = 0
    index = 0
    total_files = 0
    for i in to_upload:
        if os.path.isfile(os.path.join(INPUT_DIR, i)):
            continue
        total_files += len(os.listdir(os.path.join(INPUT_DIR, i)))
    for i in to_upload:  # i sería el champ
        if os.path.isfile(os.path.join(INPUT_DIR, i)):
            continue
        print("Uploading champ " + i)
        urls_file = open(os.path.join(INPUT_DIR, i.lower() + ".json"), 'w')
        urls_file.write("{\n")
        list_dir = sort.listdir(os.path.join(INPUT_DIR, i))
        prev_filename = '.mp3'
        index = 0
        for f in list_dir:
            blob = bucket.blob(i + "/" + f)
            blob.upload_from_filename(os.path.join(INPUT_DIR, i, f))
            print(str(round(100 * total_index / total_files, 2)) + "%" +
                  " | " + f)
            blob.make_public()

            if not f.startswith(prev_filename):
                prev_filename = f.split('-')[0].replace('.mp3', '')
                if index > 0:
                    urls_file.write('  ],\n')
                urls_file.write(
                    '  "' + f.replace('.mp3', '').replace('3D', '').replace('2D', '').replace(' ', '').replace('General', '').replace('cast', '').replace('OnCast', '').replace(i.split('_')[0].capitalize(), '') + '":[\n')

            urls_file.write('\t"' + blob.public_url + '",\n')
            time.sleep(0.5)
            index += 1
            total_index += 1

        urls_file.write("  ]\n}\n")
        urls_file.close()
