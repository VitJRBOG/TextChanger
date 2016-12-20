import java.util.ArrayList;

class SpaceInserter {
    char[] insertSpaceAfterPunctMark(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '.', '!', '?', ',', ':', ')', ']'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (arrayCharText[i + 1] != varCharCondition) {
                            if (arrayCharText[i + 1] != ' ' &&
                                    arrayCharText[i + 1] != ')' &&
                                    arrayCharText[i + 1] != '(') {
                                ArrayList<Character> objArrayList =
                                        new ArrayList<>();
                                for (int j = 0; j < arrayCharText.length; j++) {
                                    if (j == i + 1) {
                                        objArrayList.add(' ');
                                    }
                                    objArrayList.add(arrayCharText[j]);
                                }
                                char[] arrayCharTextNew =
                                        new char[objArrayList.size()];

                                for (int j = 0; j < objArrayList.size(); j++) {
                                    arrayCharTextNew[j] = objArrayList.get(j);
                                }
                                return insertSpaceAfterPunctMark(arrayCharTextNew);
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
    char[] insertSpaceBeforeParenthesis(char[] arrayCharText) {
        try {
            char[] arrayCharSymbols = {
                    '('
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (arrayCharText[i - 1] != varCharCondition &&
                                arrayCharText[i + 1] != varCharCondition) {
                            if (arrayCharText[i - 1] != ' ') {
                                ArrayList<Character> objArrayList =
                                        new ArrayList<>();
                                for (int j = 0; j < arrayCharText.length; j++) {
                                    if (j == i) {
                                        objArrayList.add(' ');
                                    }
                                    objArrayList.add(arrayCharText[j]);
                                }
                                char[] arrayCharTextNew =
                                        new char[objArrayList.size()];
                                for (int j = 0; j < objArrayList.size(); j++) {
                                    arrayCharTextNew[j] = objArrayList.get(j);
                                }
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
    char[] insertSpaceAfterDoubleParenthesis(char[] arrayCharText) {

        try {
            char[] arrayCharSymbols = {
                    '(', ')'
            };
            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (arrayCharText[i + 1] == varCharCondition &&
                                arrayCharText[i + 2] != varCharCondition &&
                                arrayCharText[i + 2] != ' ') {
                            ArrayList<Character> objArrayList =
                                    new ArrayList<>();
                            for (int j = 0; j < arrayCharText.length; j++) {
                                if (j == i + 2) {
                                    objArrayList.add(' ');
                                }
                                objArrayList.add(arrayCharText[j]);
                            }
                            char[] arrayCharTextNew =
                                    new char[objArrayList.size()];

                            for (int j = 0; j < objArrayList.size(); j++) {
                                arrayCharTextNew[j] = objArrayList.get(j);
                            }
                            return insertSpaceAfterDoubleParenthesis(arrayCharTextNew);
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
