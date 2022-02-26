import os
import pyperclip as pc

INPUT_DIR = "./newjson/"
INPUT_DIR2 = "./json/"

if __name__ == '__main__':
    allchamps = []
    for champ in os.listdir(INPUT_DIR):
        if not champ.endswith(".json"):
            continue
        allchamps.append(champ.replace('.json', '').upper())
    for champ in os.listdir(INPUT_DIR2):
        if not champ.endswith('.json'):
            continue
        allchamps.append(champ.replace('.json', '').upper())
    allchamps.sort()
    for champ in allchamps:
        print(champ + ",")
    pc.copy(",\n".join(allchamps))
    print("Copied to clipboard 😉")
