import os
import json
from pydub import AudioSegment
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage

INPUT_DIR = "D:/Modding/Client/plugins/rcp-be-lol-game-data/global/es_es/v1/"
OUTPUT_DIR = "./CLIENT/"

if __name__ == "__main__":
    cred = credentials.Certificate(
        "credentials\\memeshare-a3107-firebase-adminsdk-u4zc0-0c4d2b4e35.json")
    firebase_admin.initialize_app(cred, {
        'storageBucket': 'memeshare-a3107.appspot.com'
    })

    bucket = storage.bucket()
    for champ_file in os.listdir(f'{INPUT_DIR}/champions/'):
        with open(f'{INPUT_DIR}/champions/{champ_file}', 'r', encoding='utf-8') as file:
            data = json.load(file)
            champ = data["alias"]
            if os.path.isfile(f"./newjson/{champ.lower()}.json"):
                champ_urls = None
                with open(f"./newjson/{champ.lower()}.json", 'r') as champ_json:
                    champ_urls = json.load(champ_json)
                    champ_json.close()
                if champ_urls is None:
                    print(f"Error with champ {champ}")
                else:
                    audio_select = AudioSegment.from_ogg(
                        f'{INPUT_DIR}/champion-choose-vo/{champ_file.replace(".json", ".ogg")}')
                    audio_ban = AudioSegment.from_ogg(
                        f'{INPUT_DIR}/champion-ban-vo/{champ_file.replace(".json", ".ogg")}')
                    audio_select.export(
                        f'{OUTPUT_DIR}/{champ.lower()}_select.mp3', format='mp3')
                    audio_ban.export(
                        f'{OUTPUT_DIR}/{champ.lower()}_ban.mp3', format='mp3')

                    urls = []

                    blob = bucket.blob(f'{champ.lower()}/select.mp3')
                    blob.upload_from_filename(
                        f'{OUTPUT_DIR}/{champ.lower()}_select.mp3')
                    blob.make_public()
                    urls.append(blob.public_url)

                    blob = bucket.blob(f'{champ.lower()}/ban.mp3')
                    blob.upload_from_filename(
                        f'{OUTPUT_DIR}/{champ.lower()}_ban.mp3')
                    blob.make_public()
                    urls.append(blob.public_url)

                    champ_urls["Client"] = urls
                    with open(f"./newjson/{champ.lower()}.json", 'w') as champ_json:
                        json.dump(champ_urls, champ_json)
                        champ_json.close()
                    print(f'{champ.lower()}.json')
