
#
#   Upload a file to Google Cloud
#   python src/upload/file.py local_file_path cloud_file_path
#

from sys import argv
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
import time

if __name__ == '__main__':
    cred = credentials.Certificate(
        "credentials\\memeshare-a3107-firebase-adminsdk-u4zc0-0c4d2b4e35.json")
    firebase_admin.initialize_app(cred, {
        'storageBucket': 'memeshare-a3107.appspot.com'
    })

    bucket = storage.bucket()

    print(argv[1], argv[2])
    blob = bucket.blob(argv[2])
    blob.upload_from_filename(argv[1])
    blob.make_public()
    time.sleep(1)
    print("Uploaded!")
    print(blob.public_url)
