# Sound Extract

These are some python scripts that I use to extract, convert and upload to Firebase the sounds of the app.

Other tools:

- [Morilli/bnk-extract](https://github.com/Morilli/bnk-extract): convert and short audio files compressed into `.bnk` and `.wpf` files.
- [Crauzer/Obsidian](https://github.com/Crauzer/Obsidian): extracts data from WAD files.

## Important

Before running the scripts check the routes and if they do what you want because I made them to meet my requirements.

## Order

Order I use them:

1. Set the champs to work with in `input_champs.py`.
2. Create the folders in some directory with `createfolders.py`.
3. Extract every champ files using [Obsidian](https://github.com/Crauzer/Obsidian) (I always extract all).
4. `organize.py` or `multiple_organize.py` depending on if I want to get the voices directly or if I get sounds by myself. `organize.py` just move and convert the ogg files extracted with [bnk-extract](https://github.com/Morilli/bnk-extract). `multiple_organize.py` get the voice files using [bnk-extract](https://github.com/Morilli/bnk-extract) and converts them.
5. `upload.py` or `multiple_upload.py` uploads the files of a folder and creates a pseudo-json (json with trailing commas) file with the public links of the sound files.
