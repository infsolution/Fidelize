package infsolution.com.br.fidelize.tools;

/**
 * Created by Cicero on 11/07/2016.
 */
public class Tools {
    private String object;
    private boolean aBoolean;
    private long aLong;
    private int anInt;
    public Tools(){

    }

    public String newSubString(String word){
        if(word.length()>7)
        return word.substring(0,7);
        else
            return word;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}
