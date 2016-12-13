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
            // FIXME: 12/13/16 //ERROR 350
            System.out.println("COMPUTER: Error. " + e.getMessage() + ".");
        }
        ask();
    }

    private void ask() {
        System.out.println("COMPUTER: Continue?");
        System.out.print("USER: ");

        Scanner objScanner =
                new Scanner(System.in);

        String varStringInput = objScanner.nextLine();

        if (varStringInput.equals("n")) {
            System.out.println("COMPUTER: Close program...");
            System.exit(0);
        } else {
            if (varStringInput.equals("y")) {
                Starter();
            } else {
                System.out.println("COMPUTER: Please, enter only 'y' or 'n'.");
                ask();
            }
        }
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
            varStringLineToClipboard = varStringLineToClipboard + varChar;
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
            varStringLineToClipboard = varStringLineToClipboard + varChar;
        }

        if (varIntNumberChangedChars > 0) {
            System.out.println("COMPUTER: Was changed " + varIntNumberChangedChars + " symbols.");
        } else {
            System.out.println("COMPUTER: No symbols for change.");
        }
        return varStringLineToClipboard;
    }

    private char[] insertAndRemoveSpace(char[] arrayCharText, char varCharCondition) {

        for (int i = 0; i < arrayCharText.length; i++) {
            if (arrayCharText[i] == varCharCondition) {
                if (arrayCharText[i+1] != ' ') {
                    char[] arrayCharTextNew =
                            new char[arrayCharText.length+1];
                    int ja = 0;
                    for (int j = 0; j < arrayCharTextNew.length; j++) {
                        if (j != i+1) {
                            arrayCharTextNew[j] = arrayCharText[ja];
                            ja++;
                        } else {
                            arrayCharTextNew[j] = ' ';
                        }
                    }
                    return insertAndRemoveSpace(arrayCharTextNew, varCharCondition);
                } else {
                    if (i > 1) {
                        if (arrayCharText[i - 1] == ' ') {
                            char[] arrayCharTextNew =
                                    new char[arrayCharText.length+1];
                            int ja = 0;
                            for (int j = 0; j < arrayCharTextNew.length; j++) {
                                if (j != i-1) {
                                    arrayCharTextNew[j] = arrayCharText[ja];
                                    ja++;
                                } else {
                                    ja++;
                                }
                            }
                            return insertAndRemoveSpace(arrayCharTextNew, varCharCondition);
                        }
                    }
                }
            }
        }

        return arrayCharText;
    }

    private char[] loverCaseText(char[] arrayCharText) {

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
                    if (arrayCharText[i - 2] == '.') {
                        varString = arrayCharText[i] + "";
                        varString = varString.toUpperCase();
                        arrayCharText[i] = varString.charAt(0);
                    }
                }
            }
        }

        return arrayCharText;
    }

    private String correctText(String varStringLineFromClipboard) {

        varStringLineFromClipboard = varStringLineFromClipboard.toLowerCase();

        char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

        arrayCharFromString = insertAndRemoveSpace(arrayCharFromString, '.');
        arrayCharFromString = insertAndRemoveSpace(arrayCharFromString, ',');

        arrayCharFromString = loverCaseText(arrayCharFromString);

        varStringLineFromClipboard = "";

        for (char varChar : arrayCharFromString) {
            varStringLineFromClipboard += varChar;
        }

        System.out.println("COMPUTER: Text was checked and corrected.");

        return varStringLineFromClipboard;
    }

}