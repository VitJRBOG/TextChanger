# coding: utf8
u"""Инициализация рабочих файлов программы."""


import os
import data_manager
import output_data


def check_path():
    u"""Проверка существования пути."""
    def create_file_path():
        u"""Создание файла для хранение пути."""
        file_text = open("path.txt", "w")
        file_text.write("")
        file_text.close()
        message = "Was created file \"path.txt\"."
        output_data.show_data_to_console(message)

    def create_path(path, new_folder):
        u"""Создание пути."""
        os.mkdir(path + new_folder)
        message = "Was created directory \"" + new_folder + "\"."
        output_data.show_data_to_console(message)

    if os.path.exists("path.txt") is not True:
        create_file_path()
    PATH = data_manager.read_path()

    if os.path.exists(PATH + "res") is not True:
        create_path(PATH, "res")

    if os.path.exists(PATH + "res/symbs.json") is not True:
        message = "File \"symbs.json\" is not exist."
        output_data.show_data_to_console(message)

    if os.path.exists(PATH + "res/words.json") is not True:
        message = "File \"words.json\" is not exist."
        output_data.show_data_to_console(message)
