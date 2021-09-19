import os
import input_champs

champs = input_champs.CHAMPS
mod_path = "D:/Modding/LOL/"

if __name__ == "__main__":
    for c in champs:
        os.mkdir(os.path.join(mod_path, c.lower()))
        os.mkdir(os.path.join(mod_path, c.lower() + "_es"))
