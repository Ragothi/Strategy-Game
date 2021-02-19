package sample;

import javafx.animation.PathTransition;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class AnimationControls {

    public static final double buttonStartXY=75.0;
    public static final double labelStartX=22.5;
    public static final double labelStartY=6.5;
    public static final int oneRightX=45;
    public static final int oneRightY=0;
    public static final int oneLeftX=-45;
    public static final int oneLeftY=0;
    public static final int oneUpX=20;
    public static final int oneUpY=50;
    public static final int oneDownX=-20;
    public static final int oneDownY=-53;

    public static final int hexOneRightX=80;
    public static final int hexOneRightY=0;
    public static final int hexOneLeftX=-80;
    public static final int hexOneLeftY=0;
    public static final int hexOneUpRightX=40;
    public static final int hexOneUpRightY=-60;
    public static final int hexOneUpLeftX=-40;
    public static final int hexOneUpLeftY=-60;
    public static final int hexOneDownRightX=40;
    public static final int hexOneDownRightY=60;
    public static final int hexOneDownLeftX=-40;
    public static final int hexOneDownLeftY=60;

    private static final int wh = 75;


    private static PathTransition transition = new PathTransition();
    private static SVGPath path = new SVGPath();

    public static PathTransition moveOne(Unit unit,String pathString){

        path.setContent(pathString);
        transition.setPath(path);
        transition.setNode(unit.getImage());
        transition.setCycleCount(1);
        transition.setDuration(Duration.seconds(1));
        return   transition;
    }

    public static PathTransition longMove(Unit unit,String pathString,int fieldsOnWaySize){

        path.setContent(pathString);
        transition.setPath(path);
        transition.setNode(unit.getImage());
        transition.setCycleCount(1);
        transition.setDuration(Duration.seconds(GlobalSettings.unitSpeed*fieldsOnWaySize));
        return   transition;
    }

//    public static void moveOne(Unit unit,int x ,int y){
//
//
//        transition.setByX(x);
//        transition.setByY(y);
//        transition.setNode(unit.getImage());
//        transition.setCycleCount(1);
//        transition.setDuration(Duration.seconds(1));
//        transition.play();
//        transition.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                unit.getImage().setTranslateX(unit.getImage().getTranslateX() + wh);
//                unit.getImage().setTranslateY(unit.getImage().getTranslateY() + wh);
//            }
//        });
//    }

}
