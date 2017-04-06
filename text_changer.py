# coding: utf8

import gtk


def main_menu():
    print("COMPUTER: You are in Main Menu.")
    print("COMPUTER: Copy text to clipboard and enter digit for next action.")
    print("COMPUTER [Main Menu -> ..]: 1 == Change symbols from Cyr to Lat.")
    print("COMPUTER [Main Menu -> ..]: 2 == Change symbols from Lat to Cyr.")
    print("COMPUTER [Main Menu -> ..]: 3 == To censor text.")
    print("COMPUTER [Main Menu -> ..]: 4 == To format text.")
    print("COMPUTER [Main Menu -> ..]: 0 == Close program.")

    try:
        user_answer = raw_input("USER [Main menu -> ]: ")

        if user_answer == "0":
            print("COMPUTER [Main Menu]: Exit from program...")
            exit()
        else:
            if user_answer == "1":
                cyr_to_lat()
            else:
                if user_answer == "2":
                    lat_to_cyr()
                else:
                    if user_answer == "3":
                        censor()
                    else:
                        if user_answer == "4":
                            format_text()
                        else:
                            print("COMPUTER: Unknown command. Retry query...")
                            main_menu()

    except Exception as var_except:
        print("COMPUTER [Main Menu]: Error, " + str(var_except) + ". Return to Main Menu...")
        main_menu()


def change(text, type_operation):

    cyr_symb = ['у', 'е', 'х', 'о', 'р', 'а', 'с']
    lat_symb = ['y', 'e', 'x', 'o', 'p', 'a', 'c']

    array_text = list(text.decode("utf8"))

    symb_change_from = []
    symb_change_to = []

    number_changes = 0
    if type_operation == "Cyr to Lat":
        symb_change_from = cyr_symb
        symb_change_to = lat_symb
    else:
        if type_operation == "Lat to Cyr":
            symb_change_from = lat_symb
            symb_change_to = cyr_symb
        else:
            print(
                "COMPUTER: [Main Menu -> " + type_operation +
                " -> Change]: Unknown operation. " + "Return to Main Menu...")
            main_menu()
    for i, char_original in enumerate(array_text):
        for j, char_from in enumerate(symb_change_from):
            if char_original == char_from:
                array_text[i] = symb_change_to[j]
                number_changes += 1
                break

    print(
        "COMPUTER [Main Menu -> " +
        type_operation + " -> Change]: Was changed " + str(number_changes) + " symbols.")
    text = ''.join(array_text)
    return text


def cyr_to_lat():
    try:

        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        another_text = change(text, "Cyr to Lat").encode("utf8")

        cb.set_text(another_text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

        print(
            "COMPUTER [Main Menu -> Cyr to Lat]: Text was been copied to clipboard.")

    except Exception as var_except:
        print(
            "COMPUTER [Main Menu -> Cyr to Lat]: Error, " +
            str(var_except) + ". Return to Main Menu...")

    main_menu()


def lat_to_cyr():
    try:
        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        another_text = change(text, "Lat to Cyr").encode("utf8")

        cb.set_text(another_text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

        print(
            "COMPUTER [Main Menu -> Lat to Cyr]: " +
            "Text was been copied to clipboard.")

    except Exception as var_except:
        print(
            "COMPUTER [Main Menu -> Lat to Cyr]: Error, " +
            str(var_except) + ". Return to Main Menu...")

    main_menu()


def censor():

    try:
        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        dirty_words = ["блят", "сук", "суч", "еба", "трах", "хер", "хре", "хуй", "ганд",
                       "хуе", "пид", "ублю", "муда", "жоп", "ебл", "блеа", "пздц", "уёб",
                       "уеб", "ваги", "шлюх", "член", "пизд", "говн", "гове", "говё", "гонд"]

        text = text.decode("utf8")
        array_words = text.split(' ')

        number_changes = 0

        def censor_algorithm(array_words, number_changes):
            try:

                for i, word_original in enumerate(array_words):
                    if len(word_original) >= 3:
                        for dirty_word in array_dirty_words:
                            dirty_word = dirty_word.decode("utf8")
                            j = word_original.lower().find(dirty_word)
                            print(word_original + " " + dirty_word)
                            if j != -1:
                                if len(word_original[j:]) > 3:
                                    array_words[i] = word_original[0:j + 1] +\
                                        "**" + word_original[j + 3:]
                                    number_changes += 1
                                else:
                                    array_words[i] = word_original[0:j + 1] + "**"
                                    number_changes += 1
                                break

            except Exception as var_except_inner:
                print(
                    "COMPUTER [Main Menu -> To censor text]: Error, " +
                    str(var_except_inner) + ". Return to Main Menu...")
                main_menu

            print(
                "COMPUTER [Main Menu -> To censor text]: Was censored " +
                str(number_changes) + " words.")
            return array_words

        array_words = censor_algorithm(array_words, number_changes)

        text = ' '.join(array_words)

        cb.set_text(text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

    except Exception as var_except:
        print(
            "COMPUTER [Main Menu -> To censor text]: Error, " +
            str(var_except) + ". Return to Main Menu...")

    main_menu()


def format_text():

    def format_text_insert_spaces(array_text):

        number_changes = 0

        array_symbols = ['.', ',', '?', '!', ':', ';']

        def insert_spaces_algorithm(array_text, number_changes):
            try:
                for i, char_from_text in enumerate(array_text):
                    if i >= 1 and \
                       i < len(array_text) - 1 and \
                       array_text[i + 1] != "\n":
                        for symb_from_array in array_symbols:
                            if array_text[i] == symb_from_array \
                               and array_text[i + 1] != ' ':
                                array_text.insert(i + 1, ' ')
                                number_changes += 1
                                return insert_spaces_algorithm(array_text, number_changes)

                print(
                    "COMPUTER [Main Menu -> To format text -> Insert spaces]: " +
                    "Was did " + str(number_changes) + " changes.")
                return array_text

            except Exception as var_except:
                print(
                    "COMPUTER [Main Menu -> To format text -> Insert spaces]: " +
                    "Error, " + str(var_except) + ".")
                return array_text

        array_text = insert_spaces_algorithm(array_text, number_changes)

        return array_text

    def format_text_remove_spaces(array_text):

        number_changes = 0

        array_symbols = ['.', ',', '?', '!', ':', ';', ' ']

        def remove_spaces_algorithm(array_text, number_changes):
            try:
                for i, char_from_text in enumerate(array_text):
                    if i >= 1:
                        for symb_from_array in array_symbols:
                            if array_text[i] == symb_from_array and array_text[i - 1] == ' ':
                                array_text.pop(i - 1)
                                number_changes += 1
                                return remove_spaces_algorithm(array_text, number_changes)
                            if array_text[i] == ' ' and array_text[i - 1] == "\n":
                                array_text.pop(i)
                                number_changes += 1
                                return remove_spaces_algorithm(array_text, number_changes)
                            if array_text[i] == ')' and array_text[i - 1] == ')' and \
                               array_text[i - 2] == ' ':
                                array_text.pop(i - 2)
                                number_changes += 1
                                return remove_spaces_algorithm(array_text, number_changes)
                            if array_text[i] == '(' and array_text[i - 1] == '(' and \
                               array_text[i - 2] == ' ':
                                array_text.pop(i - 2)
                                number_changes += 1
                                return remove_spaces_algorithm(array_text, number_changes)

                print(
                    "COMPUTER [Main Menu -> To format text -> Remove spaces]: Was did " +
                    str(number_changes) + " changes.")
                return array_text

            except Exception as var_except:
                print(
                    "COMPUTER [Main Menu -> To format text -> Remove spaces]: Error, " +
                    str(var_except) + ".")
                return array_text

        array_text = remove_spaces_algorithm(array_text, number_changes)

        return array_text

    def format_text_uppercase(array_text):

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
                        if array_text[i] == symb_from_array and array_text[i + 1] == ' ':
                            array_text[i + 2] = array_text[i + 2].upper()
                            number_changes += 1
                            break
                    if array_text[i] == ')' and array_text[i - 1] == ')' and \
                       array_text[i + 1] == ' ':
                        array_text[i + 2] = array_text[i + 2].upper()
                        number_changes += 1
                    if array_text[i] == '(' and array_text[i - 1] == '(' and \
                       array_text[i + 1] == ' ':
                        array_text[i + 2] = array_text[i + 2].upper()
                        number_changes += 1

        except Exception as var_except:
            print(
                "COMPUTER [Main Menu -> To format text -> Uppercase]: " +
                "Error, " + str(var_except) + ".")
            return array_text

        print(
            "COMPUTER [Main Menu -> To format text -> Uppercase]: " +
            "Was did " + str(number_changes) + " changes.")

        return array_text

    try:
        cb = gtk.clipboard_get()

        text = str(gtk.Clipboard.wait_for_text(cb))

        text = text.decode("utf8")
        text = text.lower()
        array_text = list(text)

        array_text = format_text_insert_spaces(array_text)
        array_text = format_text_remove_spaces(array_text)
        array_text = format_text_uppercase(array_text)

        text = ''.join(array_text)

        cb.set_text(text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()

        print(
            "COMPUTER [Main Menu -> To format text]: " +
            "Text was been copied to clipboard.")
    except Exception as var_except:
        print(
            "COMPUTER [Main Menu -> To format text]: " +
            "Error, " + str(var_except) + ". Return to Main Menu...")

    main_menu()


main_menu()
