package sample;

import javafx.scene.Node;

public class ShapeStorage {



    public static final String arenaTile = "-fx-shape: \"M 400 300 L 350 200 L 450 200 L 500 300 L 400 300 Z\";";
    public static final String arenaTileSVGPath = "M 0 0 L 42 0 L 60 50 L 18 50 L 0 0 Z";
    public static final String hexArenaTileSVGPath = "M 400 300 L 400 260 L 440 240 L 480 260 L 480 300 L 440 320 Z";
    public static final String hexMap = "M 0 37.5 L 0 112.5 L 65 150 L 130 112.5 L 130 37.5 L 65 0 Z";
    public static final String initBackPath = "M 0 0 L 80 0 L 80 80 L 0 80 L 0 0 Z";
    public static final String initTextPath = "M 0 0 L 240 0 L 240 25 L 0 25 L 0 0 Z";
    public static final String initMainBackPath = "M 0 0 L 270 0 L 270 120 L 0 120 L 0 0 Z";
    public static final String avatarBack = "M 0 0 L 150 0 L 150 150 L 0 150 L 0 0 Z";
    public static final String hexAvatarBack = "M 0 0 L 130 0 L 130 130 L 0 130 L 0 0 Z";

    public static final String combatOptionButPath = "M 0 0 L 50 0 L 50 50 L 0 50 L 0 0 Z";
    public static final String closeStatInfo = "M 0 0 L 30 0 L 30 30 L 0 30 L 0 0 Z";
    public static final String unitAmountLabel = "M 0 0 L 30 0 L 30 15 L 0 15 L 0 0 Z";

    public static void scaleNode(Node node, double x, double y){
        String temp = node.getStyle();
        node.setStyle(temp+ " -fx-scale-x: " + x + "; -fx-scale-y: " + y + ";");
    }

}
