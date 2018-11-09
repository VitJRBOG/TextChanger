# coding: utf8
u"""Модуль замены символов."""


import pyperclip
import data_manager


def change(operation):
    u"""Запуск алгоритмов замены символов."""
    number_changes = select_data(operation)
    return number_changes


def change_algorithm(original_word, dict_with_symbs, number_changes):
    u"""Алгоритм замены символов."""
    symbs = list(original_word)
    for i, orig_symb in enumerate(symbs):
        if orig_symb in dict_with_symbs:
            symbs[i] = dict_with_symbs[orig_symb]
            number_changes += 1
    new_word = ''.join(symbs)
    return new_word, number_changes


def select_data(condition):
    u"""Сбор необходимых данных для замены символов."""
    PATH = data_manager.read_path()
    number_changes = 0
    symbs_json_loads = data_manager.read_json(PATH + "res/", "symbs")
    text = pyperclip.paste()
    words_from_text = text.split(' ')
    if condition == "cyr_to_lat":
        dict_with_symbs = symbs_json_loads["cyr_to_lat"]
        words_json_loads = data_manager.read_json(PATH + "res/", "words")
        changewords = words_json_loads["changewords"]
        for i, orig_word in enumerate(words_from_text):
            for changeword in changewords:
                if len(changeword) == len(orig_word):
                    pos = orig_word.lower().find(changeword)
                    if pos > -1:
                        new_word, number_changes = change_algorithm(
                            orig_word, dict_with_symbs, number_changes)
                        words_from_text[i] = new_word
                        break
    elif condition == "lat_to_cyr":
        dict_with_symbs = symbs_json_loads["lat_to_cyr"]
        for i, orig_word in enumerate(words_from_text):
            new_word, number_changes = change_algorithm(
                orig_word, dict_with_symbs, number_changes)
            words_from_text[i] = new_word
    elif condition == "all_cyr_to_lat":
        dict_with_symbs = symbs_json_loads["cyr_to_lat"]
        for i, orig_word in enumerate(words_from_text):
            new_word, number_changes = change_algorithm(
                orig_word, dict_with_symbs, number_changes)
            words_from_text[i] = new_word
    else:
        return "condition is wrong"
    new_text = ' '.join(words_from_text)
    pyperclip.copy(new_text)
    return number_changes
