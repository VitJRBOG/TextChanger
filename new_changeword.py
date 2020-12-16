# coding: utf8


import pyperclip
import re
import data_manager


def add_new_changeword():
    u"""Запуск алгоритмов добавления нового слова."""
    adding_result, cause = select_data()
    return adding_result, cause


def check_forbidden_symbols(text):
    result = re.findall(r"[ .,;:!?]", text)
    if len(result) > 0:
        return True


def check_existence(new_word, changewords):
    u"""Проверка наличия нового слова в списке."""
    for changewords in changewords:
        if len(changewords) == len(new_word):
            pos = new_word.lower().find(changewords)
            if pos > -1:
                return True
    return False


def add_word(new_word, words_json_loads):
    u"""Добавление нового слова в список."""
    PATH = data_manager.read_path()
    changewords = words_json_loads["changewords"]
    changewords.append(new_word.lower())
    words_json_loads["changewords"] = changewords
    data_manager.write_json(PATH + "res/", "words", words_json_loads)


def select_data():
    u"""Сбор необходимых данных для алгоритма добавления нового слова."""
    text = pyperclip.paste()
    if check_forbidden_symbols(text) is not True:
        PATH = data_manager.read_path()
        words_json_loads = data_manager.read_json(PATH + "res/", "words")
        changewords = words_json_loads["changewords"]
        if check_existence(text, changewords) is not True:
            add_word(text, words_json_loads)
            return True, ""
        else:
            return False, "already exist"
    else:
        return False, "forbidden symbols"
    
