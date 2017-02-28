# coding: utf8

import gtk


def main_menu():
    print("COMPUTER: You are in Main Menu.")
    print("COMPUTER: Copy text to clipboard and enter digit for next action.")
    print("COMPUTER [Main Menu -> ..]: 1 == Change symbols from Cyr to Lat.")
    print("COMPUTER [Main Menu -> ..]: 2 == Change symbols from Lat to Cyr.")
    print("COMPUTER [Main Menu -> ..]: 3 == To format text.")
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
                        format_text()
                    else:
                        print("COMPUTER: Unknown command. Retry query...")

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
            print("COMPUTER: [Main Menu -> " + type_operation + " -> Change]: Unknown operation. " +
                                                                "Return to Main Menu...")
            main_menu()
    for i, char_original in enumerate(array_text):
        for j, char_from in enumerate(symb_change_from):
            if char_original == char_from:
                array_text[i] = symb_change_to[j]
                number_changes += 1
                break

    print("COMPUTER [Main Menu -> " + type_operation + " -> Change]: "
                                                       "Was changed " + str(number_changes) +
          " symbols. Text was been copied to clipboard.")
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

    except Exception as var_except:
        print("COMPUTER [Main Menu -> Cyr to Lat]: Error, " + str(var_except) + ". Return to Main Menu...")

    main_menu()


def lat_to_cyr():
    try:
        cb = gtk.clipboard_get()

        text = gtk.Clipboard.wait_for_text(cb)

        another_text = change(text, "Cyr to Lat").encode("utf8")

        cb.set_text(another_text)
        gtk.timeout_add(1, gtk.main_quit)
        gtk.main()
    except Exception as var_except:
        print("COMPUTER [Main Menu -> Lat to Cyr]: Error, " + str(var_except) + ". Return to Main Menu...")

    main_menu()


def format_text():
    print("COMPUTER [Main Menu -> Format Text]: There is empty yet. Return to Main Menu...")
    main_menu()

main_menu()
