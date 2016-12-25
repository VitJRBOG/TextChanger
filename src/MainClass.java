import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        MainClass objMainClass =
                new MainClass();
        objMainClass.Starter();
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
                CharChanger objCharChanger =
                        new CharChanger();
                String varStringLine = objCharChanger.charChangeCyrToLat(objTextTransfer.getData());

                objTextTransfer.setData(varStringLine);
            } else {
                if (varStringInput.equals("2")) {
                    CharChanger objCharChanger =
                            new CharChanger();
                    String varStringLine = objCharChanger.charChangeLatToCyr(objTextTransfer.getData());

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

    private static class TextTransfer implements ClipboardOwner {
        @Override
        public void lostOwnership(Clipboard clipboard, Transferable contents) {}
        void setData(String data){
            StringSelection stringSelection;
            stringSelection = new StringSelection(data);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, this);
        }
        String getData() throws IOException, UnsupportedFlavorException {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        }
    }

    private char algorithmUpperCase(char[] arrayCharText, int varIntIndxForUppercase) {
        String varString = arrayCharText[varIntIndxForUppercase] + "";
        varString = varString.toUpperCase();

        return varString.charAt(0);
    }

    private char[] upperCase(char[] arrayCharText) {

        try {

            String varString = "";

            for (char varChar : arrayCharText) {
                varString += varChar;
            }

            varString = varString.toLowerCase();

            arrayCharText = varString.toCharArray();

            for (int i = 0; i < arrayCharText.length; i++) {
                if (i == 0) {
                    arrayCharText[i] = algorithmUpperCase(arrayCharText, i);
                } else {
                    if (i > 2) {
                        char[] arrayCharSymbols = {
                                '.', '!', '?'
                        };

                        for (char varCharCondition : arrayCharSymbols) {

                            if (arrayCharText[i] == varCharCondition) {
                                if (arrayCharText[i] == '.') {
                                    if (i > 1 &&
                                            i < arrayCharText.length - 2) {

                                        if (arrayCharText[i + 1] == ' ') {
                                            if (arrayCharText[i - 1] != 'т') {
                                                if (arrayCharText[i - 1] != 'к' &&
                                                        arrayCharText[i - 1] != 'п' &&
                                                        arrayCharText[i - 1] != 'д' &&
                                                        arrayCharText[i - 1] != 'н' &&
                                                        arrayCharText[i - 1] != 'е') {
                                                    if (arrayCharText[i + 2] != 'к' &&
                                                            arrayCharText[i + 2] != 'п' &&
                                                            arrayCharText[i + 2] != 'д' &&
                                                            arrayCharText[i + 2] != 'н' &&
                                                            arrayCharText[i + 2] != 'е') {
                                                        arrayCharText[i + 2] = algorithmUpperCase(
                                                                arrayCharText, i + 2);
                                                    }
                                                }
                                            } else {
                                                if (arrayCharText[i + 2] != 'к' &&
                                                        arrayCharText[i + 2] != 'п' &&
                                                        arrayCharText[i + 2] != 'д' &&
                                                        arrayCharText[i + 2] != 'н' &&
                                                        arrayCharText[i + 2] != 'е') {
                                                    arrayCharText[i + 2] = algorithmUpperCase(
                                                            arrayCharText, i + 2);
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (i > 1 &&
                                            i < arrayCharText.length - 2) {
                                        if (arrayCharText[i + 1] == ' ') {
                                            arrayCharText[i + 2] = algorithmUpperCase(
                                                    arrayCharText, i + 2);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (i > 2 &&
                            i < arrayCharText.length - 2) {
                        if (arrayCharText[i] == 'п' ||
                                arrayCharText[i] == 'д' ||
                                    arrayCharText[i] == 'н' ||
                                        arrayCharText[i] == 'е' ||
                                            arrayCharText[i] == 'к') {
                            if (arrayCharText[i + 1] != '.' &&
                                    arrayCharText[i - 3] != 'т' &&
                                        arrayCharText[i - 1] == ' ' &&
                                            arrayCharText[i - 2] == '.') {
                                arrayCharText[i] = algorithmUpperCase(arrayCharText, i);
                            }
                        }
                    }

                    char[] arrayCharSymbolsForSmiles = {
                            '(', ')'
                    };


                    for (char varCharCondition : arrayCharSymbolsForSmiles) {
                        if (arrayCharText[i] == varCharCondition &&
                                arrayCharText[i + 1] == varCharCondition &&
                                    arrayCharText[i + 2] == ' ') {
                            arrayCharText[i + 3] = algorithmUpperCase(arrayCharText, i + 3);
                        }
                        if (arrayCharText[i] == varCharCondition &&
                                arrayCharText[i + 1] == varCharCondition &&
                                    arrayCharText[i + 2] == ' ' &&
                                        arrayCharText[i + 3] == '"') {
                            arrayCharText[i + 4] = algorithmUpperCase(arrayCharText, i + 4);
                        }
                        if (arrayCharText[i] == varCharCondition &&
                                arrayCharText[i + 1] == varCharCondition &&
                                    arrayCharText[i + 2] == ' ' &&
                                        arrayCharText[i + 3] == '\'') {
                            arrayCharText[i + 4] = algorithmUpperCase(arrayCharText, i + 4);
                        }
                        if (arrayCharText[i] == varCharCondition &&
                                arrayCharText[i + 1] == ' ') {
                            if (arrayCharText[i - 1] == '.' ||
                                    arrayCharText[i - 1] == '!' ||
                                        arrayCharText[i - 1] == '?') {
                                arrayCharText[i + 2] = algorithmUpperCase(arrayCharText, i + 2);
                            }
                        }
                        if (arrayCharText[i] == varCharCondition &&
                                arrayCharText[i + 1] == ' ') {
                            if (arrayCharText[i - 1] == ':' ||
                                    arrayCharText[i - 1] == ';' ||
                                        arrayCharText[i - 1] == '=') {
                                arrayCharText[i + 2] = algorithmUpperCase(arrayCharText, i + 2);
                            }
                        }
                    }

                    if (arrayCharText[i] == '\n' &&
                            arrayCharText[i + 1] != ' ') {
                        arrayCharText[i + 1] = algorithmUpperCase(arrayCharText, i + 1);
                    }

                    if (arrayCharText[i] == ':' &&
                            arrayCharText[i + 1] == ' ' &&
                                arrayCharText[i + 2] == '"') {
                        arrayCharText[i + 3] = algorithmUpperCase(arrayCharText, i + 3);
                    }

                    if (arrayCharText[i] == ':' &&
                            arrayCharText[i + 1] == 'd') {
                        arrayCharText[i + 1] = algorithmUpperCase(arrayCharText, i + 1);
                    }

                    if (arrayCharText[i] == ':' &&
                            arrayCharText[i + 1] == 'D' &&
                                arrayCharText[i + 2] == ' ') {
                        arrayCharText[i + 3] = algorithmUpperCase(arrayCharText, i + 3);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("COMPUTER: [upperCase] Error. " + e.getMessage() + ".");
        }

        return arrayCharText;
    }

    private String correctText(String varStringLineFromClipboard) {

        try {

            SpaceInserter objSpaceInserter =
                    new SpaceInserter();
            SpaceRemover objSpaceRemover =
                    new SpaceRemover();
            Censorship objCensorship =
                    new Censorship();

            varStringLineFromClipboard = varStringLineFromClipboard.toLowerCase();

            char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

            arrayCharFromString = objCensorship.simpleWords(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceInBeginNewLine(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceBeforePunctMark(arrayCharFromString);
            arrayCharFromString = objSpaceInserter.insertSpaceAfterPunctMark(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceInSmile(arrayCharFromString);
            arrayCharFromString = objSpaceInserter.insertSpaceAfterParenthesis(arrayCharFromString);
            arrayCharFromString = objSpaceInserter.insertSpaceBeforeParenthesis(arrayCharFromString);
            arrayCharFromString = objSpaceInserter.insertSpaceAfterDoubleParenthesis(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceBeforeQuotes(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceAfterColonInSmiles(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceAfterOpenParenthesis(arrayCharFromString);
            arrayCharFromString = objSpaceInserter.insertSpaceAfterSmiles(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeDoubleSpace(arrayCharFromString);
            arrayCharFromString = objSpaceRemover.removeSpaceBetweenPunctMark(arrayCharFromString);
            arrayCharFromString = upperCase(arrayCharFromString);

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