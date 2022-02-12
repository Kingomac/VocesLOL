import re
import os
from ctypes import wintypes, windll
from functools import cmp_to_key


def alphanumeric(data: list):
    def convert(text): return int(text) if text.isdigit() else text.lower()

    def alphanum_key(key): return [convert(c)
                                   for c in re.split('([0-9]+)', key)]
    return sorted(data, key=alphanum_key)


def listdir(path: str) -> list[str]:
    return winsort(os.listdir(path))


def winsort(data):
    _StrCmpLogicalW = windll.Shlwapi.StrCmpLogicalW
    _StrCmpLogicalW.argtypes = [wintypes.LPWSTR, wintypes.LPWSTR]
    _StrCmpLogicalW.restype = wintypes.INT

    def cmp_fnc(psz1, psz2): return _StrCmpLogicalW(psz1, psz2)
    return sorted(data, key=cmp_to_key(cmp_fnc))
