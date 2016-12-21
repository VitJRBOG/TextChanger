import java.util.ArrayList;

class SpaceRemover {

    private char[] algorithmRemoveSpace(char[] arrayCharText, int varIntCondition) {
        ArrayList<Character> objArrayList =
                new ArrayList<>();
        try {
            for (int j = 0; j < arrayCharText.length; j++) {
                if (j != varIntCondition) {
                    objArrayList.add(arrayCharText[j]);
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [algorithmRemoveSpace] Error. " + e.getMessage() + ".");
        }
        char[] arrayCharTextNew =
                new char[objArrayList.size()];
        for (int j = 0; j < objArrayList.size(); j++) {
            arrayCharTextNew[j] = objArrayList.get(j);
        }
        for (int j = 0; j < objArrayList.size(); j++) {
            arrayCharTextNew[j] = objArrayList.get(j);
        }
        return arrayCharTextNew;
    }

    char[] removeSpaceBeforePunctMark(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '.', '!', '?', ',', ':', ')', ']'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (i > 0 &&
                                i < arrayCharText.length - 1) {
                            if (arrayCharText[i - 1] != varCharCondition) {
                                if (arrayCharText[i - 1] == ' ') {
                                    char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i - 1);
                                    return removeSpaceBeforePunctMark(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceBeforePunctMark] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] removeSpaceAfterOpenParenthesis(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '(', '['
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (i > 0 &&
                            i < arrayCharText.length - 1) {
                        if (arrayCharText[i] == varCharCondition &&
                                arrayCharText[i + 1] == ' ') {
                            char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i + 1);
                            return removeSpaceAfterOpenParenthesis(arrayCharTextNew);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceAfterOpenParenthesis] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] removeSpaceBeforeQuotes(char[] arrayCharText) {
        try {
            for (int i = 0; i < arrayCharText.length; i++) {
                if (i > 1 &&
                        i < arrayCharText.length - 1) {
                    if (arrayCharText[i] == '"') {
                        if (arrayCharText[i - 1] == ' ') {
                            if (arrayCharText[i - 2] == '.' ||
                                    arrayCharText[i - 2] == '!' ||
                                    arrayCharText[i - 2] == '?') {
                                char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i - 1);
                                return removeSpaceBeforeQuotes(arrayCharTextNew);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceBeforeQuotes] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }
    char[] removeSpaceAfterColonInSmiles(char[] arrayCharText) {

        try {
            char[] arrayCharSymbolsColons = {
                    ':', ';', '='
            };
            char[] arrayCharSymbolsSecondPartSmile = {
                    '(', ')', '3', 'D', 'p'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbolsColons) {
                    for (char varCharAdditionalCondition : arrayCharSymbolsSecondPartSmile) {
                        if (i > 0 &&
                                i < arrayCharText.length - 2) {
                            if (arrayCharText[i] == varCharCondition) {
                                if (arrayCharText[i + 1] == ' ' &&
                                        arrayCharText[i + 2] == varCharAdditionalCondition) {
                                    char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i + 1);
                                    return removeSpaceAfterColonInSmiles(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceAfterColonInSmiles] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }
    char[] removeDoubleSpace(char[] arrayCharText) {
        try {
            for (int i = 0; i < arrayCharText.length; i++) {
                if (i > 0 &&
                        i < arrayCharText.length - 1) {
                    if (arrayCharText[i] == ' ' &&
                            arrayCharText[i + 1] == ' ') {
                        char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i);
                        return removeDoubleSpace(arrayCharTextNew);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeDoubleSpace] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }
    char[] removeSpaceBetweenPunctMark(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '.', '!', '?', ',', ':'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (i > 0 &&
                            i < arrayCharText.length - 2) {
                        if (arrayCharText[i] == varCharCondition) {
                            if (arrayCharText[i + 1] == ' ') {
                                if (arrayCharText[i + 2] == '.' ||
                                        arrayCharText[i + 2] == '!' ||
                                        arrayCharText[i + 2] == '?' ||
                                        arrayCharText[i + 2] == ',' ||
                                        arrayCharText[i + 2] == ':') {
                                    char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i + 1);
                                    return removeSpaceBetweenPunctMark(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceBetweenPunctMark] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }

    char[] removeSpaceInSmile(char[] arrayCharText) {
        try {
            for (int i = 0; i < arrayCharText.length; i++) {
                if (i > 0 &&
                        i < arrayCharText.length - 2) {
                    if (arrayCharText[i] == ':' &&
                            arrayCharText[i + 2] == 'd' &&
                                arrayCharText[i + 1] == ' ') {
                        char[] arrayCharTextNew = algorithmRemoveSpace(arrayCharText, i + 1);
                        return removeSpaceInSmile(arrayCharTextNew);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceBetweenPunctMark] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }
}
