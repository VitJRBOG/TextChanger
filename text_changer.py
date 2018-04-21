# coding: utf8

import gtk
import json
import os
import re
import copy


def starter():
    try:

        if os.path.exists("path.txt") is False:
            file_text = open("path.txt", "w")
            file_text.write("")
            file_text.close()
            print("COMPUTER: Was created file \"path.txt\".")

        path = read_path_txt("Starter")

        if os.path.exists(path + "json") is False:
            os.mkdir(path + "json")
            print("\nCOMPUTER: Was created directory \"json\".")

        if os.path.exists(path + "json/words.json") is False:
            print("\nCOMPUTER: WARNING! File \"words.json\" is empty.")
            file_json = open(path + "json/words.json", "w")
            file_json.write("{}")
            file_json.close()
            print("\nCOMPUTER: File \"words.json\" was succesfully created.")

    except Exception as var_except:
        print(
            "COMPUTER: Error, " + str(var_except) + ". Exit from program...")
        exit()

    main_menu()


def read_path_txt(sender):
    sender += " -> Read \"path.txt\""
    try:
        path = str(open("path.txt", "r").read())

        if len(path) > 0 and path[len(path) - 1] != "/":
            path += "/"

        return path

    except Exception as var_except:
        print(
            "COMPUTER [" + sender +
            "]: Error, " + str(var_except) +
            ". Return to Main menu...")
    main_menu()


def read_json(sender, path, file_name):
    sender += " -> Read JSON"
    try:
        loads_json = json.loads(open(str(path) + str(file_name) +
                                     ".json", 'r').read())  # dict

        return loads_json
    except Exception as var_except:
        print(
            "COMPUTER [" + str(sender) +
            "]: Error, " + str(var_except) +
            ". Return to Main menu...")
    main_menu()


def write_json(sender, path, file_name, loads_json):
    sender += " -> Write JSON"

    try:
        file_json = open(str(path) + str(file_name) + ".json", "w")
        file_json.write(json.dumps(loads_json, indent=4, ensure_ascii=False))
        file_json.close()

    except Exception as var_except:
        print(
            "COMPUTER [" + str(sender) +
            "]: Error, " + str(var_except) +
            ". Return to Main menu...")
        main_menu()


def main_menu():
    sender = "Main menu"

    print("\nCOMPUTER: You are in Main Menu.")
    print("COMPUTER: Copy text to clipboard and enter digit for next action.")
    print("COMPUTER [" + sender + "]: 1 == Change checkwords from Cyr to Lat.")
    print("COMPUTER [" + sender + "]: 2 == Change symbols from Lat to Cyr.")
    print("COMPUTER [" + sender + "]: 3 == To censor text.")
    print("COMPUTER [" + sender + "]: 4 == To format text.")
    print("COMPUTER [" + sender + "]: 5 == Change symbols from Cyr to Lat.")
    print("COMPUTER [" + sender + "]: 6 == New changeword.")
    print("COMPUTER [" + sender + "]: 0 == Close program.")

    try:
        user_answer = raw_input("USER [" + sender + "]: (1-5/0) ")

        user_answer = re.sub("[^0123456789\.]", "", user_answer)

        if user_answer == "0":
            print("COMPUTER [" + sender + "]: Exit from program...")
            exit()
        elif user_answer == "1":
            change(sender, "Cyr to Lat")
        elif user_answer == "2":
            change(sender, "Lat to Cyr")
        elif user_answer == "3":
            censor(sender)
        elif user_answer == "4":
            format_text(sender)
        elif user_answer == "5":
            change(sender, "All Cyr to Lat")
        elif user_answer == "6":
            new_changeword(sender)
        else:
            print("COMPUTER: Unknown command. Retry query...")
            main_menu()

    except Exception as var_except:
        print("COMPUTER [" + sender + "]: Error, " + str(var_except) +
              ". Return to Main Menu...")
        main_menu()


def change(sender, type_operation):
    sender += " -> " + type_operation

    def algorithm(sender, text, type_operation):
        sender += " -> Change"

        PATH = read_path_txt(sender)

        loads_json = read_json("Censor", PATH + "json/", "words")
        cyr_symb = loads_json["cyr_symb"]
        lat_symb = loads_json["lat_symb"]
        changewords = loads_json["changewords"]

        cyr_symb = ''.join(cyr_symb)
        cyr_symb = list(cyr_symb)
        lat_symb = ''.join(lat_symb)
        lat_symb = list(lat_symb)

        array_words = []
        array_text = []

        number_changes = 0
        if type_operation == "Cyr to Lat":
            array_words = text.decode("utf8").split(' ')
            symb_change_from = cyr_symb
            symb_change_to = lat_symb

            for i, word_original in enumerate(array_words):
                for j, search_word in enumerate(changewords):
                    check = word_original.lower().find(search_word)
                    if check == 0 and\
                       len(str(word_original)) == len(str(search_word)):
                        array_word = list(word_original)
                        for n, char_original in enumerate(array_word):
                            for k, char_from in enumerate(symb_change_from):
                                if char_original == char_from:
                                    array_word[n] = symb_change_to[k]
                                    number_changes += 1
                                    word_original = ''.join(array_word)
                                    break
                array_words[i] = word_original
            text = ' '.join(array_words)
        elif type_operation == "Lat to Cyr":
            array_text = list(text.decode("utf8"))
            symb_change_from = lat_symb
            symb_change_to = cyr_symb

            for i, char_original in enumerate(array_text):
                for j, char_from in enumerate(symb_change_from):
                    if char_original == char_from:
                        array_text[i] = symb_change_to[j]
                        number_changes += 1
                        break
            text = ''.join(array_text)
        elif type_operation == "All Cyr to Lat":
            array_text = list(text.decode("utf8"))
            symb_change_from = cyr_symb
            symb_change_to = lat_symb

            for i, char_original in enumerate(array_text):
                for j, char_from in enumerate(symb_change_from):
                    if char_original == char_from:
                        array_text[i] = symb_change_to[j]
                        number_changes += 1
                        break
            text = ''.join(array_text)
        else:
            print(
                "COMPUTER: [" + sender + "]: Unknown operation. " +
                "Return to Main Menu...")
            main_menu()

        print(
            "COMPUTER [" + sender + "]: Was changed " +
            str(number_changes) + " symbols.")
        return text

    try:

        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        another_text = algorithm(sender, text, type_operation).encode("utf8")

        cb.set_text(another_text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

        print(
            "COMPUTER [" + sender + "]: " +
            "Text was been copied to clipboard.")

    except Exception as var_except:
        print(
            "COMPUTER [" + sender + "]: Error, " +
            str(var_except) + ". Return to Main Menu...")

    main_menu()


def censor(sender):
    sender += " -> To censor text"

    PATH = read_path_txt(sender)

    try:
        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        loads_json = read_json("Censor", PATH + "json/", "words")
        array_search_words = loads_json["censorship"]

        text = text.decode("utf8")
        array_words = text.split(' ')

        number_changes = 0

        def censor_algorithm(array_words, number_changes):
            try:

                for i, word_original in enumerate(array_words):
                    if len(word_original) >= 3:
                        for search_word in array_search_words:
                            search_word = search_word.decode("utf8")
                            j = word_original.lower().find(search_word)
                            if j != -1:
                                if len(word_original[j:]) > 3:
                                    array_words[i] = word_original[0:j + 1] +\
                                        "**" + word_original[j + 3:]
                                    number_changes += 1
                                else:
                                    array_words[i] = word_original[0:j + 1] +\
                                        "**"
                                    number_changes += 1
                                break

            except Exception as var_except_inner:
                print(
                    "COMPUTER [" + sender + "]: Error, " +
                    str(var_except_inner) + ". Return to Main Menu...")
                main_menu

            print(
                "COMPUTER [" + sender + "]: Was censored " +
                str(number_changes) + " words.")
            return array_words

        array_words = censor_algorithm(array_words, number_changes)

        text = ' '.join(array_words)

        cb.set_text(text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

    except Exception as var_except:
        print(
            "COMPUTER [" + sender + "]: Error, " +
            str(var_except) + ". Return to Main Menu...")

    main_menu()


def format_text(sender):
    sender += " -> To format text"

    def format_text_insert_spaces(sender, array_text):
        sender += " -> Insert spaces"

        number_changes = 0

        array_symbols = ['.', ',', '?', '!', ':', ';']

        def insert_spaces_algorithm(sender, array_text, number_changes):
            sender += " -> Algorithm"

            try:
                i = 0
                while True:
                    if i >= 1 and \
                       i < len(array_text) - 1 and \
                       array_text[i + 1] != "\n":
                        for symb_from_array in array_symbols:
                            if array_text[i] == symb_from_array \
                               and array_text[i + 1] != ' ':
                                array_text.insert(i + 1, ' ')
                                number_changes += 1
                            if i < len(array_text) - 2 and \
                               array_text[i] == ')' and \
                               array_text[i + 1] != ')' and \
                               array_text[i + 1] != ' ':
                                array_text.insert(i + 1, ' ')
                                number_changes += 1
                            if i < len(array_text) - 2 and \
                               array_text[i] == ')' and \
                               array_text[i + 1] == ')' and \
                               array_text[i + 2] != ')' and \
                               array_text[i + 2] != ' ':
                                array_text.insert(i + 2, ' ')
                                number_changes += 1
                            if i < len(array_text) - 2 and \
                               array_text[i] == '(' and \
                               array_text[i - 1] != '(' and \
                               array_text[i - 1] != ' ':
                                array_text.insert(i, ' ')
                                number_changes += 1
                            if i < len(array_text) - 2 and \
                               array_text[i] == '(' and \
                               array_text[i + 1] == '(' and \
                               array_text[i + 2] != '(' and \
                               array_text[i + 2] != ' ':
                                array_text.insert(i + 2, ' ')
                                number_changes += 1
                    if i >= len(array_text) - 1:
                        break
                    i += 1

                print(
                    "COMPUTER [" + sender + "]: Was did " +
                    str(number_changes) +
                    " changes.")
                return array_text

            except Exception as var_except:
                print(
                    "COMPUTER [" + sender + "]: Error, " +
                    str(var_except) + ".")
                return array_text

        array_text = insert_spaces_algorithm(sender, array_text,
                                             number_changes)

        return array_text

    def format_text_remove_spaces(sender, array_text):
        sender += " -> Remove spaces"

        number_changes = 0

        array_symbols = ['.', ',', '?', '!', ':', ';', ' ']

        def remove_spaces_algorithm(sender, array_text, number_changes):
            sender += " -> Algorithm"

            try:
                i = 0
                while True:
                    if i >= 1 and \
                       i < len(array_text):
                        for symb_from_array in array_symbols:
                            if array_text[i] == symb_from_array and \
                               array_text[i - 1] == ' ':
                                array_text.pop(i - 1)
                                number_changes += 1
                            if array_text[i] == ' ' and \
                               array_text[i - 1] == "\n":
                                array_text.pop(i)
                                number_changes += 1
                            if i >= 1 and \
                               array_text[i] == ')' and \
                               array_text[i - 1] == ' ':
                                array_text.pop(i - 1)
                                number_changes += 1
                            if i >= 2 and \
                               array_text[i] == ')' and \
                               array_text[i - 1] == ')' and \
                               array_text[i - 2] == ' ':
                                array_text.pop(i - 2)
                                number_changes += 1
                            if i >= 1 and \
                               i < len(array_text) - 3 and \
                               array_text[i] == '(' and \
                               array_text[i - 1] != '(' and \
                               array_text[i + 1] == ' ':
                                array_text.pop(i + 1)
                                number_changes += 1
                            if i >= 2 and \
                               array_text[i] == '(' and \
                               array_text[i - 1] == '(' and \
                               array_text[i - 2] == ' ':
                                array_text.pop(i - 2)
                                number_changes += 1
                    if i >= len(array_text) - 1:
                        break
                    i += 1

                print(
                    "COMPUTER [" + sender + "]: Was did " +
                    str(number_changes) + " changes.")
                return array_text

            except Exception as var_except:
                print(
                    "COMPUTER [" + sender + "]: Error, " +
                    str(var_except) + ".")
                return array_text

        array_text = remove_spaces_algorithm(sender, array_text,
                                             number_changes)

        return array_text

    def format_text_uppercase(sender, array_text):
        sender += " -> Uppercase"

        number_changes = 0

        array_symbols = ['.', '?', '!']

        try:
            array_text[0] = array_text[0].upper()
            number_changes += 1

            for i, char_from_text in enumerate(array_text):
                if i >= 1:
                    if array_text[i - 1] == "\n":
                        array_text[i] = array_text[i].upper()
                        number_changes += 1
                if i < len(array_text) - 2:
                    for symb_from_array in array_symbols:
                        if array_text[i] == symb_from_array and \
                           array_text[i + 1] == ' ':
                            array_text[i + 2] = array_text[i + 2].upper()
                            number_changes += 1
                            break
                    if array_text[i] == ')' and \
                       array_text[i - 1] == ')' and \
                       array_text[i + 1] == ' ':
                        array_text[i + 2] = array_text[i + 2].upper()
                        number_changes += 1
                    if array_text[i] == '(' and \
                       array_text[i - 1] == '(' and \
                       array_text[i + 1] == ' ':
                        array_text[i + 2] = array_text[i + 2].upper()
                        number_changes += 1

        except Exception as var_except:
            print(
                "COMPUTER [" + sender + "]: " +
                "Error, " + str(var_except) + ".")
            return array_text

        print(
            "COMPUTER [" + sender + "]: " +
            "Was did " + str(number_changes) + " changes.")

        return array_text

    try:
        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        text = text.decode("utf8")
        text = text.lower()
        array_text = list(text)

        array_text = format_text_insert_spaces(sender, array_text)
        array_text = format_text_remove_spaces(sender, array_text)
        array_text = format_text_uppercase(sender, array_text)

        text = ''.join(array_text)

        cb.set_text(text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

        print(
            "COMPUTER [" + sender + "]: " +
            "Text was been copied to clipboard.")
    except Exception as var_except:
        print(
            "COMPUTER [" + sender + "]: " +
            "Error, " + str(var_except) + ". Return to Main Menu...")

    main_menu()


def new_changeword(sender):
    sender += " -> New changeword"

    def check_existing(sender, check_word, words_list):
        sender += " -> Check existing"

        not_exist = True

        i = 0
        while i < len(words_list):
            if words_list[i].find(check_word) == 0 and\
              len(str(words_list[i].lower())) == len(str(check_word)):
                not_exist = False

                break

            i += 1

        return not_exist

    PATH = read_path_txt(sender)

    cb = gtk.clipboard_get()
    new_word = str(gtk.Clipboard.wait_for_text(cb))
    new_word = unicode(new_word, 'utf-8').lower().encode('utf-8')

    if new_word.find(" ") != -1:
        print("COMPUTER [" + sender + "]: Do not add spaces. " +
              "Return to Main menu...")
        main_menu()

    loads_json = read_json(sender, PATH + "json/", "words")

    changewords = copy.deepcopy(loads_json["changewords"])

    not_exist = check_existing(sender, new_word, changewords)

    if not_exist:
        changewords.append(new_word)

        loads_json["changewords"] = changewords

        write_json(sender, PATH + "json/", "words", loads_json)
        print("COMPUTER [" + sender + "]: Changewords list has been " +
              "successfully updated. Return to Main menu...")
    else:
        print("COMPUTER [" + sender + "]: This word " +
              "already exist in changewords list. Return to Main menu...")

    main_menu()


starter()
