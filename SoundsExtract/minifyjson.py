import os
import sys
from json_minify import json_minify

print(sys.argv)

if __name__ == "__main__":
    file = open(sys.argv[1], 'r')
    newjson = json_minify(file.read())
    file.close()
    file = open(sys.argv[1], 'w')
    file.write(newjson)
    file.close()
