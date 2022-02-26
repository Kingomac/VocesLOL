import os
import cdragontoolbox
from cdragontoolbox.hashes import default_hashfile

if __name__ == "__main__":
    for file in os.listdir("C:/Riot Games/League of Legends/Game/DATA/FINAL/Champions/"):
        file_path = f"C:/Riot Games/League of Legends/Game/DATA/FINAL/Champions/{file}"
        wad = cdragontoolbox.Wad(
            file_path, hashes=default_hashfile(file_path).load())
        wad.guess_extensions()
        filename = os.path.split(file_path)[1].replace('.wad.client', '')
        champ_name = filename.replace('.es_ES', '').lower()
        filtered_files = []
        for i in wad.files:
            if i.path is None:
                continue
            if i.path.startswith("assets/sounds") or i.path.startswith(f"data/characters/{champ_name}/skins") or i.path.startswith(f"assets/characters/{champ_name}/hud"):
                filtered_files.append(i)
                print(i.path)
        print(filename)
        wad.files = filtered_files
        wad.extract(f"D:/Modding/LOL/{filename}", overwrite=False)
