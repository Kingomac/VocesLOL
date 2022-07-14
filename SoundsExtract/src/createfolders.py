import os
#import src.input_champs as input_champs

#champs = input_champs.CHAMPS
mod_path = "D:/Modding/LOL/"
champs_path = "C:/Riot Games/League of Legends/Game/DATA/FINAL/Champions/"

if __name__ == "__main__":
    for folder in os.listdir(champs_path):
        name = folder.removesuffix(".wad.client")
        if os.path.isdir(f"{mod_path}{name}"):
            continue
        os.mkdir(f"{mod_path}{name}")
