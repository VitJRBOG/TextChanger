# coding: utf8
u"""Модуль для форматирования текста."""


import pyperclip
import re


def formatting_text():
    U"""Запуск алгоритмов форматирования текста."""
    select_data()


def lowercase_text(text):
    u"""Установка нижнего регистра."""
    new_text = text.lower()
    return new_text


def remove_spaces(text):
    u"""Удаление лишних пробелов"""
    def remove_space_before_symbol(text):
        u"""Удаление лишних пробелов перед символом."""
        text_symbs = list(text)
        i = 0
        while i < len(text_symbs):
            if i > 0:
                if len(re.findall(r"[.,!?):; ]", text_symbs[i])) > 0:
                    if text_symbs[i - 1] == " ":
                        text_symbs.pop(i - 1)
            if i >= len(text_symbs):
                break
            i += 1
        new_text = ''.join(text_symbs)
        return new_text

    def remove_space_after_symbol(text):
        u"""Удаление лишних пробелов после символа."""
        text_symbs = list(text)
        i = 0
        while i < len(text_symbs):
            if i < len(text_symbs) - 2:
                if len(re.findall(r"[(\n ]", text_symbs[i])) > 0:
                    if text_symbs[i + 1] == " ":
                        text_symbs.pop(i + 1)
            if i >= len(text_symbs):
                break
            i += 1
        new_text = ''.join(text_symbs)
        return new_text

    new_text = remove_space_before_symbol(text)
    new_text = remove_space_after_symbol(new_text)
    return new_text


def insert_spaces(text):
    u"""Вставка недостающих пробелов"""
    def insert_space_before_space(text):
        u"""Вставка недостающего пробела перед символом."""
        text_symbs = list(text)
        i = 0
        while i < len(text_symbs):
            if i > 0:
                if text_symbs[i] == "(":
                    if text_symbs[i - 1] != "(" and text_symbs[i - 1] != " ":
                        text_symbs.insert(i, " ")
            if i >= len(text_symbs):
                break
            i += 1
        new_text = ''.join(text_symbs)
        return new_text

    def insert_space_after_space(text):
        u"""Вставка недостающего пробела после символа."""
        text_symbs = list(text)
        i = 0
        while i < len(text_symbs):
            if i < len(text_symbs) - 2:
                if len(re.findall(r"[.,!?:;]", text_symbs[i])) > 0 and \
                   len(re.findall(r"[.,!?]", text_symbs[i + 1])) == 0:
                    if text_symbs[i + 1] != " ":
                        text_symbs.insert(i + 1, " ")
            if i >= len(text_symbs):
                break
            i += 1
        i = 0
        while i < len(text_symbs):
            if i < len(text_symbs) - 2:
                if text_symbs[i] == ")" and \
                   len(re.findall(r"[.,!?)]", text_symbs[i + 1])) == 0:
                    if text_symbs[i + 1] != " ":
                        text_symbs.insert(i + 1, " ")
            if i >= len(text_symbs):
                break
            i += 1
        new_text = ''.join(text_symbs)
        return new_text
    
    new_text = insert_space_before_space(text)
    new_text = insert_space_after_space(new_text)
    return new_text


def uppercase_characters(text):
    u"""Установка верхнего регистра у некоторых букв."""
    text_symbs = list(text)
    text_symbs[0] = text_symbs[0].upper()
    i = 0
    while i < len(text_symbs):
        if i < len(text_symbs) - 3:
            if len(re.findall(r"[.!?]", text_symbs[i])) > 0:
                if text_symbs[i + 1] == " " and text_symbs[i + 2] != " ":
                    text_symbs[i + 2] = text_symbs[i + 2].upper()
            if len(re.findall(r"[\n]", text_symbs[i])) > 0:
                if text_symbs[i + 1] != " ":
                    text_symbs[i + 1] = text_symbs[i + 1].upper()
        if i < len(text_symbs) - 4:
            if len(re.findall(r"[:]", text_symbs[i])) > 0:
                if text_symbs[i + 1] == " " and text_symbs[i + 2] == "\"":
                    text_symbs[i + 3] = text_symbs[i + 3].upper()

        i += 1
    new_text = ''.join(text_symbs)
    return new_text


def select_data():
    u"""Сбор необходимых данных для замены символов."""
    text = pyperclip.paste()
    new_text = lowercase_text(text)
    new_text = remove_spaces(new_text)
    new_text = insert_spaces(new_text)
    new_text = uppercase_characters(new_text)
    pyperclip.copy(new_text)
