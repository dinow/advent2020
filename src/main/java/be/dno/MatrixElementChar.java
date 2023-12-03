package be.dno;

public class MatrixElementChar {
    private String cValue;
    //private char charValue;
    //private String stringValue;
    private boolean flagged;

    //public MatrixElement(String _str){
    //    this.stringValue = _str;
    //    this.flagged = false;
    //}
    //public MatrixElement(char _c){
    //    this.charValue = _c;
    //    this.flagged = false;
   // }
    public MatrixElementChar(String _c){
        this.cValue = _c;
        this.flagged = false;
    }
    
    public MatrixElementChar(){
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

    public String getValue(){
        return this.cValue;
    }

   
    public void setValue(String c) {
        this.cValue = c;
    }
    public String toString(){
        return this.cValue;
    }
}
