package sample;

import com.jfoenix.controls.JFXButton;

public class Arena {

    private Field[][] fields = new Field[height][width];
    public static final int height = 9;
    public static final int width = 11;
    private final String type;



    public Arena(String type) {
        this.type = type;

        for (int i = 1; i<= height; i++){
            for (int j = 1; j<= width; j++){
                fields[i-1][j-1] =new Field(i,j,null);
            }
        }

    }

    public void setButtonsToFields(JFXButton[][]buttonsField) {
        for (int i = 1; i<= height; i++){
            for (int j = 1; j<= width; j++){
                fields[i-1][j-1].setButton(buttonsField[i-1][j-1]);
            }
        }

    }

    public Field[][] getFields() {
        return fields;
    }

    public Field getField(int i,int j) {
        return fields[i-1][j-1];
    }

    public Field getField(int x,int y,int z) {
        Field field = new Field(-1,-1,null);
        for (int i=1;i<=Arena.height;i++){
            for (int j=1;j<=Arena.width;j++){
                if (getField(i,j).getHexX()==x && getField(i,j).getHexY()==y && getField(i,j).getHexZ()==z){
                    field=getField(i,j);
                }
            }
        }
        return field;
    }

    public boolean getIsUnitHere(int i, int j) {
        return fields[i-1][j-1].isUnitHere();
    }

    public String getType() {
        return type;
    }

    public boolean getIsTrapHere(int i, int j) {
        return fields[i-1][j-1].isTrapHere();
    }

    public boolean getIsTowerAimingHere(int i, int j) {
        return fields[i-1][j-1].isTowerAimingHere();
    }

    public void setIsUnitHere(int i, int j,boolean value) {
        fields[i-1][j-1].setIsUnitHere(value);
    }

    public void setIsTrapHere(int i, int j,boolean value) {
         fields[i-1][j-1].setIsTrapHere(value);
    }

    public void setIsTowerAimingHere(int i, int j,boolean value) {
        fields[i-1][j-1].setIsTowerAimingHere(value);
    }

}
