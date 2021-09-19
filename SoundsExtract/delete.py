import os
import functools


def remove_files_from_dir(directory):
    for i in os.listdir(directory):
        remove_recursive(os.path.join(directory, i))


@functools.lru_cache(maxsize=2)
def remove_recursive(directory):
    if os.path.isfile(directory):
        os.remove(directory)
        return
    for i in os.listdir(directory):
        remove_recursive(os.path.join(directory, i))
    os.rmdir(directory)


if __name__ == "__main__":
    remove_files_from_dir('./files/')
    remove_files_from_dir('./output/')
    remove_files_from_dir('./images/')
