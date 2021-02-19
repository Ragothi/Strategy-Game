package sample;

import javafx.scene.paint.Paint;

public class FractionGraphicData {


    private String name;
    private String backgroundImageURL;
    private String unitBackground;
    private String fractionDescription;
    private Paint fractionColor;

    public FractionGraphicData(String name, String backgroundImageURL,Paint fractionColor) {
        this.name = name;
        this.backgroundImageURL = backgroundImageURL;
        this.unitBackground="";
        this.fractionColor=fractionColor;
    }

    public String getFractionDescription() {
        return fractionDescription;
    }

    public void setFractionDescription(String fractionDescription) {
        this.fractionDescription = fractionDescription;
    }

    public String getName() {
        return name;
    }

    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    public void setUnitBackground(String unitBackground) {
        this.unitBackground = unitBackground;
    }

    public String getUnitBackground() {
        return unitBackground;
    }

    public Paint getFractionColor() {
        return fractionColor;
    }

    public void setFractionColor(Paint fractionColor) {
        this.fractionColor = fractionColor;
    }
}
