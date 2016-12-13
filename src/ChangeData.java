public class ChangeData {
    private char[] arrayCharText;
    private char[] arrayCharOldSymbols;
    private char[] arrayCharNewSymbols;
    private int varIntCountOfChanges = 0;

    public void setText(char[] arrayCharText) {
        this.arrayCharText = arrayCharText;
    }
    public void setOldSymbols(char[] arrayCharOldSymbols) {
        this.arrayCharOldSymbols = arrayCharOldSymbols;
    }
    public void setNewSymbols(char[] arrayCharNewSymbols) {
        this.arrayCharNewSymbols = arrayCharNewSymbols;
    }
    public void setCountOfChanges(int varIntCountOfChanges) {
        this.varIntCountOfChanges = varIntCountOfChanges;
    }
    public char[] getText() {
        return arrayCharText;
    }
    public char[] getOldSymbols() {
        return arrayCharOldSymbols;
    }
    public char[] getNewSymbols() {
        return arrayCharNewSymbols;
    }
    public int getCountOfChanges() {
        return varIntCountOfChanges;
    }

    ChangeData() {
        this.arrayCharText = null;
        this.arrayCharOldSymbols = null;
        this.arrayCharNewSymbols = null;
        this.varIntCountOfChanges = 0;
    }
}
