import os
import json

INPUT_DIR = "./files/"
SELECT_FILE = "./champ_select.json"
BAN_FILE = "./champ_ban.json"

if __name__ == '__main__':
    select_file = open(SELECT_FILE, 'r')
    ban_file = open(BAN_FILE, 'r')
    select_json = json.loads(select_file.read())
    ban_json = json.loads(ban_file.read())
    select_file.close()
    ban_file.close()
    for i in os.listdir(INPUT_DIR):
        if 'skin' not in i and 'announcer' not in i and i.endswith('.json'):
            print(i)
            champ_file = open(os.path.join(INPUT_DIR, i), 'r')
            champ_json = json.loads(champ_file.read())
            champ_file.close()
            new_file = open(os.path.join(INPUT_DIR, i), 'w')
            try:
                champ_name = i.replace('.json', '')
                champ_name = champ_name[0].upper() + champ_name[1:]
                champ_json["client"] = [
                    select_json[champ_name + "Select"][0], ban_json[champ_name + "Ban"][0]]
            except:
                print("Error with file", i)
            new_file.write(json.dumps(champ_json))
            new_file.close()
