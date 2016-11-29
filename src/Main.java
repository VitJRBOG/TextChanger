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
                    if (varStringInput.equals("0")) {
                        System.out.println("COMPUTER: Close program...");
                        System.exit(0);
                    } else {
                        System.out.println("COMPUTER: Please, enter only '1', '2' or '0'.");
                        Starter();
                    }
                }
            }

            System.out.println("COMPUTER: Done! Text was copied into clipboard.");
        }
        catch (Exception e) {
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

    private String charChangeCyrToLat(String varStringLineFromClipboard) {

        char[] arrayCharCyr = {
                'е', 'х', 'а', 'р', 'о', 'с'
        };

        char[] arrayCharLat = {
                'e', 'x', 'a', 'p', 'o', 'c'
        };

        char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

        String varStringLineToClipboard = "";
        int varIntNumberChangedChars = 0;

        for (int i = 0; i < arrayCharFromString.length; i++) {
            for (int j = 0; j < arrayCharLat.length; j++) {
                if (arrayCharCyr[j] == arrayCharFromString[i]) {
                    arrayCharFromString[i] = arrayCharLat[j];
                    varIntNumberChangedChars++;
                    break;
                }
            }
        }

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

        char[] arrayCharLat = {
                'e', 'x', 'a', 'p', 'o', 'c'
        };

        char[] arrayCharCyr = {
                'е', 'х', 'а', 'р', 'о', 'с'
        };

        char[] arrayCharFromString = varStringLineFromClipboard.toCharArray();

        String varStringLineToClipboard = "";
        int varIntNumberChangedChars = 0;

        for (int i = 0; i < arrayCharFromString.length; i++) {
            for (int j = 0; j < arrayCharCyr.length; j++) {
                if (arrayCharLat[j] == arrayCharFromString[i]) {
                    arrayCharFromString[i] = arrayCharCyr[j];
                    varIntNumberChangedChars++;
                    break;
                }
            }
        }

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

}