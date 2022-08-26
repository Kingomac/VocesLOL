import os
import sys
import json

print(sys.argv)

if __name__ == "__main__":
    file = open(sys.argv[1], 'r')
    newjson = json.load(file)
    file.close()
    file = open(sys.argv[1], 'w')
    file.write(json.dumps(newjson))
    file.close()
