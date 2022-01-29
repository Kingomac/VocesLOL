import os

INPUT_DIR = "./newjson/"

if __name__ == '__main__':
    for champ in os.listdir(INPUT_DIR):
        if not champ.endswith(".json"):
            continue
        print(f'<string name="{champ.replace(".json", "")}"></string>')
    """
    for champ in os.listdir(INPUT_DIR):
        if not os.path.isdir(f'{INPUT_DIR}/{champ}'):
            continue
        for skin in os.listdir(f'{INPUT_DIR}/{champ}'):
            if not os.path.isdir(f'{INPUT_DIR}/{champ}/{skin}'):
                continue
            num = skin.replace('skin', '')
            if num == '0':
                print(f'<string name="{champ}"></string>')
            else:
                print(f'<string name="{champ}_skin_{num}"></string>')
    """
