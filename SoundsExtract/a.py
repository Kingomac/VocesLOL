import os
import shutil


for i in os.listdir("./output/"):
    if not os.path.isdir(f'./output/{i}'):
        continue
    for j in os.listdir(f'./output/{i}'):
        if not os.path.isdir(f'./output/{i}/{j}'):
            continue
        for k in os.listdir(f'./output/{i}/{j}'):
            if os.path.isdir(f'./output/{i}/{j}/{k}'):
                print(f'./output/{i}/{j}/{k}')
                shutil.rmtree(f'./output/{i}/{j}/{k}')
