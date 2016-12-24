import java.util.ArrayList;

class SpaceInserter {

    private char[] algorithmInsertSpace(char[] arrayCharText, int varIntCondition) {
        ArrayList<Character> objArrayList =
                new ArrayList<>();
        try {
            for (int j = 0; j < arrayCharText.length; j++) {
                if (j == varIntCondition) {
                    objArrayList.add(' ');
                }
                objArrayList.add(arrayCharText[j]);
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [algorithmInsertSpace] Error. " + e.getMessage() + ".");
        }
        char[] arrayCharTextNew =
                new char[objArrayList.size()];
        for (int j = 0; j < objArrayList.size(); j++) {
            arrayCharTextNew[j] = objArrayList.get(j);
        }
        return arrayCharTextNew;
    }

    char[] insertSpaceAfterPunctMark(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '.', '!', '?', ',', ':'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (i > 0 &&
                            i < arrayCharText.length - 2) {
                        if (arrayCharText[i] == varCharCondition) {
                            if (arrayCharText[i + 1] != varCharCondition) {
                                if (arrayCharText[i + 1] != ' ' &&
                                        arrayCharText[i + 1] != ')' &&
                                            arrayCharText[i + 1] != '(') {
                                    char[] arrayCharTextNew = algorithmInsertSpace(arrayCharText, i + 1);
                                    return insertSpaceAfterPunctMark(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [insertSpaceAfterPunctMark] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] insertSpaceAfterParenthesis(char[] arrayCharText) {

        try {
            char[] arrayCharSymbols = {
                    ')', ']'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                if (i > 0 &&
                        i < arrayCharText.length - 2) {
                    for (char varCharCondition : arrayCharSymbols) {
                        if (arrayCharText[i] == varCharCondition) {
                            if (arrayCharText[i + 1] != varCharCondition) {
                                if (arrayCharText[i + 1] != ' ' &&
                                        arrayCharText[i + 1] != '.' &&
                                            arrayCharText[i + 1] != '!' &&
                                                arrayCharText[i + 1] != '?' &&
                                                    arrayCharText[i + 1] != ',') {
                                    char[] arrayCharTextNew = algorithmInsertSpace(arrayCharText, i + 1);
                                    return insertSpaceAfterParenthesis(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [insertSpaceAfterParenthesis] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] insertSpaceBeforeParenthesis(char[] arrayCharText) {
        try {
            for (int i = 0; i < arrayCharText.length; i++) {
                if (arrayCharText[i] == '(') {
                    if (i > 0 &&
                            i < arrayCharText.length - 2) {
                        if (arrayCharText[i - 1] != ' ') {
                            if (arrayCharText[i - 1] != '(' &&
                                    arrayCharText[i + 1] != '(') {
                                char[] arrayCharTextNew = algorithmInsertSpace(arrayCharText, i);
                                return insertSpaceBeforeParenthesis(arrayCharTextNew);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [insertSpaceBeforeParenthesis] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] insertSpaceAfterSmiles(char[] arrayCharText) {

        try {
            char[] arrayCharSymbols = {
                    '(', ')', 'd'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (i > 0 &&
                                i < arrayCharText.length - 2) {
                            if (arrayCharText[i + 1] != ' ') {
                                if (arrayCharText[i - 1] == ':' ||
                                        arrayCharText[i - 1] == ';' ||
                                        arrayCharText[i - 1] == '=') {
                                    char[] arrayCharTextNew = algorithmInsertSpace(arrayCharText, i + 1);
                                    return insertSpaceAfterSmiles(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [insertSpaceAfterSmiles] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] insertSpaceAfterDoubleParenthesis(char[] arrayCharText) {

        try {
            char[] arrayCharSymbols = {
                    '(', ')'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (i > 0 &&
                            i < arrayCharText.length - 3) {
                        if (arrayCharText[i] == varCharCondition) {
                            if (arrayCharText[i + 1] == varCharCondition &&
                                    arrayCharText[i + 2] != varCharCondition &&
                                        arrayCharText[i + 2] != ' ' &&
                                            arrayCharText[i + 2] != ',' &&
                                                arrayCharText[i + 2] != '.' &&
                                                    arrayCharText[i + 2] != '!' &&
                                                        arrayCharText[i + 2] != '?' ) {
                                char[] arrayCharTextNew = algorithmInsertSpace(arrayCharText, i + 2);
                                return insertSpaceAfterDoubleParenthesis(arrayCharTextNew);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [insertSpaceAfterDoubleParenthesis] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }
}
