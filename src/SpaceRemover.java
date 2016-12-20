import java.util.ArrayList;

class SpaceRemover {
    char[] removeSpaceBeforePunctMark(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '.', '!', '?', ',', ':', ')', ']'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (i > 1) {
                            if (arrayCharText[i - 1] != varCharCondition) {
                                if (arrayCharText[i - 1] == ' ') {
                                    ArrayList<Character> objArrayList =
                                            new ArrayList<>();
                                    for (int j = 0; j < arrayCharText.length; j++) {
                                        if (j != i - 1) {
                                            objArrayList.add(arrayCharText[j]);
                                        }
                                    }
                                    char[] arrayCharTextNew =
                                            new char[objArrayList.size()];

                                    for (int j = 0; j < objArrayList.size(); j++) {
                                        arrayCharTextNew[j] = objArrayList.get(j);
                                    }
                                    return removeSpaceBeforePunctMark(arrayCharTextNew);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("COMPUTER: [removeSpaceBeforePunctMark] Error. " + e.getMessage() + ".");
        }
        return arrayCharText;
    }
    char[] removeSpaceBeforeQuotes(char[] arrayCharText) {
        try {
            for (int i = 0; i < arrayCharText.length; i++) {
                if (arrayCharText[i] == '"') {
                    if (arrayCharText[i - 1] == ' ') {
                        if (arrayCharText[i - 2] == '.' ||
                                arrayCharText[i - 2] == '!' ||
                                arrayCharText[i - 2] == '?') {
                            ArrayList<Character> objArrayList =
                                    new ArrayList<>();
                            for (int j = 0; j < arrayCharText.length; j++) {
                                if (j != i - 1) {
                                    objArrayList.add(arrayCharText[j]);
                                }
                            }
                            char[] arrayCharTextNew =
                                    new char[objArrayList.size()];

                            for (int j = 0; j < objArrayList.size(); j++) {
                                arrayCharTextNew[j] = objArrayList.get(j);
                            }
                            return removeSpaceBeforeQuotes(arrayCharTextNew);
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
                    '(', ')', '3'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbolsColons) {
                    for (char varCharAdditionalCondition : arrayCharSymbolsSecondPartSmile) {
                        if (arrayCharText[i] == varCharCondition) {
                            if (arrayCharText[i + 1] == ' ' &&
                                    arrayCharText[i + 2] == varCharAdditionalCondition) {
                                ArrayList<Character> objArrayList =
                                        new ArrayList<>();
                                for (int j = 0; j < arrayCharText.length; j++) {
                                    if (j != i + 1) {
                                        objArrayList.add(arrayCharText[j]);
                                    }
                                }
                                char[] arrayCharTextNew =
                                        new char[objArrayList.size()];
                                for (int j = 0; j < objArrayList.size(); j++) {
                                    arrayCharTextNew[j] = objArrayList.get(j);
                                }
                                return removeSpaceAfterColonInSmiles(arrayCharTextNew);
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
                if (arrayCharText[i] == ' ' &&
                        arrayCharText[i + 1] == ' ') {
                    ArrayList<Character> objArrayList =
                            new ArrayList<>();
                    for (int j = 0; j < arrayCharText.length; j++) {
                        if (j != i) {
                            objArrayList.add(arrayCharText[j]);
                        }
                    }
                    char[] arrayCharTextNew =
                            new char[objArrayList.size()];
                    for (int j = 0; j < objArrayList.size(); j++) {
                        arrayCharTextNew[j] = objArrayList.get(j);
                    }
                    return removeDoubleSpace(arrayCharTextNew);
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
                    if (arrayCharText[i] == varCharCondition) {
                        if (arrayCharText[i + 1] == ' ') {
                            if (arrayCharText[i + 2] == '.' ||
                                    arrayCharText[i + 2] == '!' ||
                                    arrayCharText[i + 2] == '?' ||
                                    arrayCharText[i + 2] == ',' ||
                                    arrayCharText[i + 2] == ':') {
                                ArrayList<Character> objArrayList =
                                        new ArrayList<>();
                                for (int j = 0; j < arrayCharText.length; j++) {
                                    if (j != i + 1) {
                                        objArrayList.add(arrayCharText[j]);
                                    }
                                }
                                char[] arrayCharTextNew =
                                        new char[objArrayList.size()];
                                for (int j = 0; j < objArrayList.size(); j++) {
                                    arrayCharTextNew[j] = objArrayList.get(j);
                                }
                                return removeSpaceBetweenPunctMark(arrayCharTextNew);
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
}
