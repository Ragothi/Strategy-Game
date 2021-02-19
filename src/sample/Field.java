package sample;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class Field {

    private final int x,y;
    private String actualBackgroundURL;
    private String initialBackgroundURL;
    private JFXButton button;
    private Unit unit;
    private boolean isUnitHere,isTrapHere,isTowerAimingHere;
    private Label unitAmountLabel = new Label();
    private SVGPath tile;
    private SVGPath labelBack = new SVGPath();
    private Paint initialTileColor;
    private String row;
    private int hexX,hexY,hexZ;

    public void setHexCords(int x,int y,int z){
        if (x+y+z==0){
            this.hexX=x;
            this.hexY=y;
            this.hexZ=z;
        } else {
            System.out.println("inconsistent x+y+z in hex cords creator");
        }
    }

    public int getHexX() {
        return hexX;
    }

    public int getHexY() {
        return hexY;
    }

    public int getHexZ() {
        return hexZ;
    }

    public SVGPath getLabelBack() {
        return labelBack;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setLabelBack(SVGPath labelBack) {
        this.labelBack = labelBack;
    }

    public Paint getInitialTileColor() {
        return initialTileColor;
    }

    public void setInitialTileColor(Paint initialTileColor) {
        this.initialTileColor = initialTileColor;
    }

    public Field(int x, int y, JFXButton button) {
        this.x = x;
        this.y = y;
        this.button=button;
        this.actualBackgroundURL="";
        this.unit=Units.emptyUnit;
        isUnitHere=false;
        isTrapHere=false;
        isTowerAimingHere=false;
        this.initialBackgroundURL=null;
        unitAmountLabel.setStyle(GraphicsStorage.begin+GraphicsStorage.labelBack+GraphicsStorage.end+ GraphicsStorage.universal+
                "-fx-font-size: 8pt; -fx-text-fill: black; -fx-font-weight: bold;");
        unitAmountLabel.setText("    " + 0);
        unitAmountLabel.setPrefSize(13,45);
        unitAmountLabel.setMinSize(unitAmountLabel.getPrefHeight(),unitAmountLabel.getPrefWidth());
        unitAmountLabel.setMaxSize(unitAmountLabel.getPrefHeight(),unitAmountLabel.getPrefWidth());

    }

    public SVGPath getTile() {
        return tile;
    }

    public void setTile(SVGPath tile) {
        this.tile = tile;
    }

    public void resizeLabel(int height, int width){
        unitAmountLabel.setPrefSize(height,width);
        unitAmountLabel.setMinSize(height,width);
        unitAmountLabel.setMaxSize(height,width);
    }

    public void setLabel(Label unitAmountLabel) {
        this.unitAmountLabel = unitAmountLabel;
    }

    public Label getLabel() {
        return this.unitAmountLabel;
    }

    public void setInitialBackgroundURL(String initialBackgroundURL) {
        this.initialBackgroundURL = initialBackgroundURL;
    }

    public void restoreButtonInitialStyle(){
        button.setStyle(initialBackgroundURL);
    }

    public boolean isUnitHere() {
        return isUnitHere;
    }

    public void setIsUnitHere(boolean unitHere) {
        isUnitHere = unitHere;
    }

    public boolean isTrapHere() {
        return isTrapHere;
    }

    public void setIsTrapHere(boolean trapHere) {
        isTrapHere = trapHere;
    }

    public boolean isTowerAimingHere() {
        return isTowerAimingHere;
    }

    public void setIsTowerAimingHere(boolean towerAimingHere) {
        isTowerAimingHere = towerAimingHere;
    }

    public JFXButton getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }



    public String getInitialBackgroundURL() {
        return initialBackgroundURL;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        if (unit.getActualX()==x && unit.getActualY()==y){
            this.unit = unit;
            isUnitHere=true;
            unitAmountLabel.setText("    " + unit.getAmount());
        } else {
            System.out.println("Unit and Field x,y cords mismatched");
        }
    }


    public String getCords(){
        return x+","+y;
    }

    public void emptyUnit(){
        this.unit=Units.emptyUnit;
        this.isUnitHere=false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getActualBackgroundURL() {
        return actualBackgroundURL;
    }

    public void setActualBackgroundURL(String actualBackgroundURL) {
        this.actualBackgroundURL = actualBackgroundURL;
    }
}
