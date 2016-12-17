import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main objMain =
                new Main();
        objMain.Starter();
    }

    private void Starter() {
        try {
            TextTransfer objTextTransfer =
                    new TextTransfer();

            System.out.println("COMPUTER: 1 == CyrToLat");
            System.out.println("COMPUTER: 2 == LatToCyr");
            System.out.println("COMPUTER: 3 == CorrectText");
            System.out.println("COMPUTER: 0 == CloseProgram");

            System.out.print("USER: ");
            Scanner objScanner =
                    new Scanner(System.in);

            String varStringInput = objScanner.nextLine();

            if (varStringInput.equals("1")) {
                String varStringLine = charChangeCyrToLat(objTextTransfer.getData());

                objTextTransfer.setData(varStringLine);
            } else {
                if (varStringInput.equals("2")) {
                    String varStringLine = charChangeLatToCyr(objTextTransfer.getData());

                    objTextTransfer.setData(varStringLine);
                } else {
                    if (varStringInput.equals("3")) {
                        String varStringLine = correctText(objTextTransfer.getData());

                        objTextTransfer.setData(varStringLine);
                    } else {
                        if (varStringInput.equals("0")) {
                            System.out.println("COMPUTER: Close program...");
                            System.exit(0);
                        } else {
                            System.out.println("COMPUTER: Please, enter only '1', '2' or '0'.");
                            Starter();
                        }
                    }
                }
            }

            System.out.println("COMPUTER: Done! Text was copied into clipboard.");
        }
        catch (Exception e) {
            System.out.println("COMPUTER: [Starter] Error. " + e.getMessage() + ".");
        }
        ask();
    }

    private void ask() {
        System.out.println("COMPUTER: Press Enter for continue...");
        System.out.print("USER: ");

        Scanner objScanner =
                new Scanner(System.in);

        objScanner.nextLine();

        Starter();
    }

    private ChangeData changeChars(ChangeData objChangeData) {

        char[] arrayCharText = objChangeData.getText();
        char[] arrayCharOldSymbols = objChangeData.getOldSymbols();
        char[] arrayCharNewSymbols = objChangeData.getNewSymbols();
        int varIntCountOfChanges = objChangeData.getCountOfChanges();

        for (int i = 0; i < arrayCharText.length; i++) {
            for (int j = 0; j < arrayCharNewSymbols.length; j++) {
                if (arrayCharOldSymbols[j] == arrayCharText[i]) {
                    arrayCharText[i] = arrayCharNewSymbols[j];
                    varIntCountOfChanges++;
                    break;
                }
            }
        }

        objChangeData.setText(arrayCharText);
        objChangeData.setCountOfChanges(varIntCountOfChanges);

        return objChangeData;
    }

    private String charChangeCyrToLat(String varStringLineFromClipboard) {

        Main objMain =
                new Main();

        char[] arrayCharCyr = {
                'е', 'х', 'а', 'р', 'о', 'с'
        };

        char[] arrayCharLat = {
                'e', 'x', 'a', 'p', 'o', 'c'
        };

        char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

        String varStringLineToClipboard = "";
        int varIntNumberChangedChars = 0;

        ChangeData objChangeData =
                new ChangeData();
        objChangeData.setText(arrayCharFromString);
        objChangeData.setOldSymbols(arrayCharCyr);
        objChangeData.setNewSymbols(arrayCharLat);
        objChangeData.setCountOfChanges(varIntNumberChangedChars);

        objMain.changeChars(objChangeData);

        arrayCharFromString = objChangeData.getText();
        varIntNumberChangedChars = objChangeData.getCountOfChanges();

        for (char varChar : arrayCharFromString) {
            varStringLineToClipboard += varChar;
        }

        if (varIntNumberChangedChars > 0) {
            System.out.println("COMPUTER: Was changed " + varIntNumberChangedChars + " symbols.");
        } else {
            System.out.println("COMPUTER: No symbols for change.");
        }
        return varStringLineToClipboard;
    }

    private String charChangeLatToCyr(String varStringLineFromClipboard) {

        Main objMain =
                new Main();

        char[] arrayCharLat = {
                'e', 'x', 'a', 'p', 'o', 'c'
        };

        char[] arrayCharCyr = {
                'е', 'х', 'а', 'р', 'о', 'с'
        };

        char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

        String varStringLineToClipboard = "";
        int varIntNumberChangedChars = 0;

        ChangeData objChangeData =
                new ChangeData();
        objChangeData.setText(arrayCharFromString);
        objChangeData.setOldSymbols(arrayCharLat);
        objChangeData.setNewSymbols(arrayCharCyr);
        objChangeData.setCountOfChanges(varIntNumberChangedChars);

        objMain.changeChars(objChangeData);

        arrayCharFromString = objChangeData.getText();
        varIntNumberChangedChars = objChangeData.getCountOfChanges();

        for (char varChar : arrayCharFromString) {
            varStringLineToClipboard += varChar;
        }

        if (varIntNumberChangedChars > 0) {
            System.out.println("COMPUTER: Was changed " + varIntNumberChangedChars + " symbols.");
        } else {
            System.out.println("COMPUTER: No symbols for change.");
        }
        return varStringLineToClipboard;
    }

    private char[] removeSpaceBeforePunctMark(char[] arrayCharText) {

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

    private char[] insertSpaceAfterPunctMark(char[] arrayCharText) {

        try {

            char[] arrayCharSymbols = {
                    '.', '!', '?', ',', ':', ')', ']'
            };

            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbols) {
                    if (arrayCharText[i] == varCharCondition) {
                        if (arrayCharText[i + 1] != varCharCondition) {
                            if (arrayCharText[i + 1] != ' ') {
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

    private char[] insertSpaceBeforeParenthesis(char[] arrayCharText) {

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

    private char[] insertSpaceAfterDoubleParenthesis(char[] arrayCharText) {

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

    private char[] removeSpaceAfterColonInSmiles(char[] arrayCharText) {

        try {
            char[] arrayCharSymbolsColons = {
                    ':', ';', '='
            };
            char[] arrayCharSymbolsParenthesis = {
                    '(', ')'
            };

            for (int i = 0; i < arrayCharText.length; i++) {
                for (char varCharCondition : arrayCharSymbolsColons) {
                    for (char varCharAdditionalCondition : arrayCharSymbolsParenthesis) {
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

    private char[] removeDoubleSpace(char[] arrayCharText) {

        try {

            for (int i = 0; i < arrayCharText.length; i++) {
                if (arrayCharText[i] == ' ' &&
                        arrayCharText[i + 1] == ' ' ||
                        arrayCharText[i] == '\n') {
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

    private char[] upperCaseOfFirstLetters(char[] arrayCharText) {

        try {

            String varString = "";

            for (char varChar : arrayCharText) {
                varString += varChar;
            }

            varString = varString.toLowerCase();

            arrayCharText = varString.toCharArray();

            for (int i = 0; i < arrayCharText.length; i++) {
                if (i == 0) {
                    varString = arrayCharText[i] + "";
                    varString = varString.toUpperCase();
                    arrayCharText[i] = varString.charAt(0);
                } else {
                    if (i > 2) {
                        char[] arrayCharSymbols = {
                                '.', '!', '?'
                        };

                        for (char varCharCondition : arrayCharSymbols) {
                            if (arrayCharText[i - 2] == varCharCondition) {
                                varString = arrayCharText[i] + "";
                                varString = varString.toUpperCase();
                                arrayCharText[i] = varString.charAt(0);
                            }
                        }
                    }

                    char[] arrayCharSymbolsForSmiles = {
                            '(', ')'
                    };

                    for (char varCharCondition : arrayCharSymbolsForSmiles) {
                        if (arrayCharText[i + 1] == varCharCondition &&
                                arrayCharText[i + 2] == ' ') {
                            varString = arrayCharText[i + 3] + "";
                            varString = varString.toUpperCase();
                            arrayCharText[i + 3] = varString.charAt(0);
                        }
                    }

                    if (arrayCharText[i] == '\n' &&
                            arrayCharText[i + 1] != ' ') {
                        varString = arrayCharText[i + 1] + "";
                        varString = varString.toUpperCase();
                        arrayCharText[i + 1] = varString.charAt(0);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("COMPUTER: [upperCaseOfFirstLetters] Error. " + e.getMessage() + ".");
        }

        return arrayCharText;
    }

    private String correctText(String varStringLineFromClipboard) {

        try {

            varStringLineFromClipboard = varStringLineFromClipboard.toLowerCase();

            char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

            arrayCharFromString = removeSpaceBeforePunctMark(arrayCharFromString);
            arrayCharFromString = insertSpaceAfterPunctMark(arrayCharFromString);
            arrayCharFromString = insertSpaceBeforeParenthesis(arrayCharFromString);
            arrayCharFromString = insertSpaceAfterDoubleParenthesis(arrayCharFromString);
            arrayCharFromString = removeSpaceAfterColonInSmiles(arrayCharFromString);
            arrayCharFromString = removeDoubleSpace(arrayCharFromString);
            arrayCharFromString = upperCaseOfFirstLetters(arrayCharFromString);

            varStringLineFromClipboard = "";

            for (char varChar : arrayCharFromString) {
                varStringLineFromClipboard += varChar;
            }

            System.out.println("COMPUTER: Text was checked and corrected.");
        }
        catch (Exception e) {
            System.out.println("COMPUTER: [correctText] Error. " + e.getMessage() + ".");
        }

        return varStringLineFromClipboard;
    }

}