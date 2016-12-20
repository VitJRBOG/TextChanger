class CharChanger {

    private class ChangeData {
        private char[] arrayCharText;
        private char[] arrayCharOldSymbols;
        private char[] arrayCharNewSymbols;
        private int varIntCountOfChanges = 0;
        void setText(char[] arrayCharText) {
            this.arrayCharText = arrayCharText;
        }
        void setOldSymbols(char[] arrayCharOldSymbols) {
            this.arrayCharOldSymbols = arrayCharOldSymbols;
        }
        void setNewSymbols(char[] arrayCharNewSymbols) {
            this.arrayCharNewSymbols = arrayCharNewSymbols;
        }
        void setCountOfChanges(int varIntCountOfChanges) {
            this.varIntCountOfChanges = varIntCountOfChanges;
        }
        char[] getText() {
            return arrayCharText;
        }
        char[] getOldSymbols() {
            return arrayCharOldSymbols;
        }
        char[] getNewSymbols() {
            return arrayCharNewSymbols;
        }
        int getCountOfChanges() {
            return varIntCountOfChanges;
        }
        ChangeData() {
            this.arrayCharText = null;
            this.arrayCharOldSymbols = null;
            this.arrayCharNewSymbols = null;
            this.varIntCountOfChanges = 0;
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

    String charChangeCyrToLat(String varStringLineFromClipboard) {
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
        changeChars(objChangeData);
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

    String charChangeLatToCyr(String varStringLineFromClipboard) {
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
        changeChars(objChangeData);
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
}
