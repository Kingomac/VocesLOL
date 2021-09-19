import os

"""""
if __name__ == "__main__":
    finput = open("urls.txt", 'r')
    foutput = open("urls.json", 'w')
    foutput.write("[")
    for i in finput.readlines():
        print(i[:-1])
        foutput.write('"')
        foutput.write(i[:-1])
        foutput.write('",')
    foutput.write("]")
    finput.close()
    foutput.close()
"""

if __name__ == '__main__':
    a = "kasdkjhkdsa "
    print(a)
    a = ''.join([a,"1"])
    print(a)
