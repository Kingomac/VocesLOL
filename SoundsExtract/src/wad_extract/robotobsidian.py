import pyautogui
import time

SLEEP_TIME = 0.85

pyautogui.click(714, 1058)  # open program
for i in range(0, 21):
    print("menu file")
    pyautogui.click(355, 214)  # menu file
    pyautogui.sleep(SLEEP_TIME)

    print("file dialog")
    pyautogui.click(410, 268)  # open file dialog
    pyautogui.sleep(SLEEP_TIME)

    print("open file")
    pyautogui.doubleClick(583, 333 + i * 22)  # open that file
    pyautogui.sleep(SLEEP_TIME)

    print("menu file")
    pyautogui.click(355, 214)  # menu file
    pyautogui.sleep(SLEEP_TIME)

    print("extract all")
    pyautogui.click(416, 323)
    pyautogui.sleep(SLEEP_TIME)

    print("select folder")
    pyautogui.click(583, 333 + i * 22)  # select folder
    pyautogui.sleep(SLEEP_TIME * 0.5)

    print("click select folder")
    pyautogui.click(1096, 876)
    pyautogui.sleep(SLEEP_TIME * 10 * i % 2 + SLEEP_TIME * 5)
