# coding: utf8
u"""Модуль консольного пользовательского интерфейса."""


import changer
import censorship
import formatting
import new_changeword


def main_menu():
    u"""Отображает основное меню."""
    def show_actions():
        u"""Вывод доступных действий."""
        actions = [
            "Cyr to Lat", "Lat to Cyr", "Censorship",
            "Formatting", "All Cyr to Lat", "New changeword"
        ]
        for i, action in enumerate(actions):
            output = "COMPUTER [Main menu]: " + \
                str(i + 1) + " == " + action + ""
            print(output)
        print("COMPUTER [Main menu]: 00 == Quit")
        number_actions = len(actions)
        return number_actions

    def check_user_answer(number_actions):
        u"""Получение ответа пользователя."""
        output = "USER [Main menu]: (1-" + str(number_actions) + "/00) "
        user_answer = raw_input(output)
        if user_answer == "00":
            print("COMPUTER: Quit...")
            exit(0)
        elif user_answer == "1":
            run_cyr_to_lat_changer()
        elif user_answer == "2":
            run_lat_to_cyr_changer()
        elif user_answer == "3":
            run_censorship()
        elif user_answer == "4":
            run_formatting()
        elif user_answer == "5":
            run_all_cyr_to_lat()
        elif user_answer == "6":
            run_new_changeword()
        else:
            print("COMPUTER: Error. Pleace, repeat input.")
            return check_user_answer(number_actions)
    print("COMPUTER: You are in Main menu.")
    number_actions = show_actions()
    check_user_answer(number_actions)


def run_cyr_to_lat_changer():
    u"""Запуск функций замены кириллицы на латиницу в словах из списка."""
    number_changes = changer.change("cyr_to_lat")
    output = "COMPUTER [.. -> Cyr to Lat]: " + str(number_changes) + \
        " symbols has been changed."
    print(output)
    main_menu()


def run_lat_to_cyr_changer():
    u"""Запуск функций замены латиницы на кириллицу во всех словах."""
    number_changes = changer.change("lat_to_cyr")
    output = "COMPUTER [.. -> Lat to Cyr]: " + str(number_changes) + \
        " symbols has been changed."
    print(output)
    main_menu()


def run_censorship():
    u"""Запуск функций наведения цензуры в словах из списка."""
    number_changes = censorship.censor()
    output = "COMPUTER [.. -> Censorship]: " + str(number_changes) + \
        " words has been censored."
    print(output)
    main_menu()


def run_formatting():
    u"""Запуск функций форматирования текста."""
    formatting.formatting_text()
    output = "COMPUTER [.. -> Formatting]: " + \
        "Formatting of text has been successfully completed."
    print(output)
    main_menu()


def run_all_cyr_to_lat():
    u"""Запуск функций замены кириллицы на латиницу во всех словах."""
    number_changes = changer.change("all_cyr_to_lat")
    output = "COMPUTER [.. -> All Cyr to Lat]: " + str(number_changes) + \
        " symbols has been changed."
    print(output)
    main_menu()


def run_new_changeword():
    u"""Запуск функций добавления нового слова в список для замены символов."""
    adding_result, cause = new_changeword.add_new_changeword()
    output = "COMPUTER [.. -> New changeword]: "
    if adding_result is True:
        output += "New changeword has been successfully added."
    else:
        output += "Changeword not added. Cause: " + cause + "."
    main_menu()
