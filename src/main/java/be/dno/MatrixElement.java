package be.dno;

public class MatrixElement {
    private int intValue;
    private char charValue;
    private String stringValue;
    private boolean flagged;

    public MatrixElement(String _str){
        this.stringValue = _str;
        this.flagged = false;
    }
    public MatrixElement(char _c){
        this.charValue = _c;
        this.flagged = false;
    }
    public MatrixElement(int _int){
        this.intValue = _int;
        this.flagged = false;
    }
    
    public MatrixElement(){
        this.flagged = false;
    }

    public boolean isFlagged(){
        return this.flagged;
    }
    public void flag(){
        this.flagged = true;
    }
    public void unFlag(){
        this.flagged = false;
    }

    public int getIntValue(){
        return this.intValue;
    }

    public void addIntValue(int i){
        this.intValue += i;
    }
    public void setIntValue(int i) {
        this.intValue = i;
    }
    public String toString(){
        return this.intValue + " - " + this.flagged;
    }
}
