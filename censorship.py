# coding: utf8
u"""Модуль наведения цензуры."""


import pyperclip
import data_manager


def censor():
    u"""Запуск алгоритмов наведения цензуры."""
    number_changes = select_data()
    return number_changes


def censor_algorithm(word, censorship_words, number_changes):
    u"""Алгоритм цензурирования слова."""
    for censorship_word in censorship_words:
        pos = word.lower().find(censorship_word)
        if pos > -1:
            symbs = list(word)
            symbs[pos + 1] = "*"
            symbs[pos + 2] = "*"
            word = ''.join(symbs)
            number_changes += 1
            break
    return word, number_changes


def select_data():
    u"""Сбор необходимых данных для алгоритма цензурирования."""
    PATH = data_manager.read_path()
    number_changes = 0
    text = pyperclip.paste()
    words = text.split(' ')
    words_json_loads = data_manager.read_json(PATH + "res/", "words")
    censorship_words = words_json_loads["censorship"]
    for i, orig_word in enumerate(words):
        word, number_changes = censor_algorithm(
            orig_word, censorship_words, number_changes)
        words[i] = word
    new_text = ' '.join(words)
    pyperclip.copy(new_text)
    return number_changes
