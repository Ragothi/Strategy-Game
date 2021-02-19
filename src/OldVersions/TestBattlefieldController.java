package OldVersions;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestBattlefieldController {
    //<editor-fold desc="Declarations">
    @FXML
    public AnchorPane battlefieldPanel,victoryPanel,unitStatPanel;
    @FXML
    public Label victoryGif,winnerLostLabel,looserLostLabel,unitInfo,unitInfoIcon,unitAtkStat,unitHpStat,unitRangeStat,unitMovementStat,selectedUnitActualPosition;
    @FXML
    public JFXButton proceed;
    @FXML
    public SVGPath unitInfoBack;
    public AnchorPane airContainer,landContainer,waterContainer;
    SVGPath unitPathShape = new SVGPath();
    PathTransition transition = new PathTransition();
    String unitPath = "";
    private boolean isAnimationOn = false;

    private ImageView airArenaBack,landArenaBack,waterArenaBack,avatar1,avatar2,flee,skip,autoFight,settings;
    private SVGPath initiativeBack1,initiativeBack2,initiativeBack3,initBack,avatarBack1,avatarBack2,
            minimize,maximize,fleeBack,skipBack,autoFightBack,settingsBack;
    private ArrayList<SVGPath> initiativeBackList = new ArrayList<>();
    private Label initiationText = new Label();

    private boolean isMaximized = false;
    private boolean isComputerMove = true;

    private ArrayList<SVGPath> airTiles = new ArrayList<>();
    private ArrayList<SVGPath> landTiles = new ArrayList<>();
    private ArrayList<SVGPath> waterTiles = new ArrayList<>();
    private ArrayList<ArrayList<SVGPath>> tilesList = new ArrayList<>();

    private Arena airArena = new Arena("Air");
    private Arena landArena = new Arena("Land");
    private Arena waterArena = new Arena("Water");
    private ArrayList<Arena> arenasList = new ArrayList<>();
    private final int wh = 75;
    private int saveX=0;
    private int saveY=0;

    private Unit actualUnitTurn;
    private List<Unit> unitsTurnOrder = new ArrayList<>();
    private int turnCounter=1;
    private Arena actualUnitArena;
    private FractionGameData actualPlayerTurn,looser,winner;
    private Arena actualMaximizedArena;
    private boolean wasAttackRanged  = false;
    private String action;
    private int actualAnimationDuration;
    private int[] indexArray;
    private ArrayList<Unit> attackersArmy = Players.getActualPlayer().getPlayersArmy();
    private ArrayList<Unit> defendersArmy = mapPanelController.attackedSector.getArmy();
    private ArrayList<Unit> actualArmyTurn;
    private ArrayList<ArrayList<Unit>> armiesList = new ArrayList<>();
    private ArrayList<FractionGameData> fightingPlayers = new ArrayList<>();
    private ArrayList<ImageView> initiativeLabels = new ArrayList<>();
    private FractionGameData attackingPlayer = Players.getActualPlayer();
    private FractionGameData defendingPlayer = mapPanelController.attackedSector.getOwner();
    private ArrayList<ImageView> attackersIcons = new ArrayList<>();
    private ArrayList<ImageView> defendersIcons = new ArrayList<>();
    private ScaleTransition scale1 = new ScaleTransition();
    private ScaleTransition scale2 = new ScaleTransition();
    private ScaleTransition scale3 = new ScaleTransition();
    private int scaleCycle=1;
    private int rotationCycle=1;
    double div=1;
    private Line line1 = new Line(-30/div,150/div,20/div,400/div);
    private Line line2 = new Line(20/div,400/div,700/div,300/div);
    private Line line3 = new Line(700/div,300/div,-30/div,150/div);
    private TranslateTransition transition1;
    private TranslateTransition transition2;
    private TranslateTransition transition3;
    private TranslateTransition attackerTransition = new TranslateTransition();
    private TranslateTransition defenderTransition = new TranslateTransition();
    private TranslateTransition move1 = new TranslateTransition();
    private TranslateTransition move2 = new TranslateTransition();
    private TranslateTransition move3 = new TranslateTransition();
    private TranslateTransition move4 = new TranslateTransition();
    private TranslateTransition move5 = new TranslateTransition();
    private TranslateTransition moveOneField = new TranslateTransition();

    private SequentialTransition moveSequence = new SequentialTransition();
    private ArrayList<TranslateTransition> moveList = new ArrayList<>();
    private SequentialTransition attackerSequence = new SequentialTransition();
    private SequentialTransition defenderSequence = new SequentialTransition();
    private TranslateTransition attackerPhase1 = new TranslateTransition();
    private TranslateTransition attackerPhase2 = new TranslateTransition();
    private TranslateTransition defenderPhase1 = new TranslateTransition();
    private TranslateTransition defenderPhase2 = new TranslateTransition();
    private TranslateTransition defenderPhase3 = new TranslateTransition();
    private ParallelTransition parallel = new ParallelTransition();
    private ParallelTransition parallelMove = new ParallelTransition();
    private ScaleTransition unitsScaling = new ScaleTransition();



    @FXML
    public GridPane winnerArmyBox,looserArmyBox;

    private String actualShowArenaSize = "small";
    private Field actualUnitField;
    private SVGPath initiativeTurn = new SVGPath();
    private String dx = "none";
    private String dy = "none";
    private boolean moveThenAttack = false;
    private boolean gameEnded =false;
    private int fieldsOnWay=0;
    private String path;

    //</editor-fold>

    public void initialize() {
        Sectors.playCombatMusic();
        fillArenasBackgrounds();
        fillLists();
        fillTilesColors();
        if (Players.getHumanPlayers().contains(defendingPlayer)) {
            defendersArmy = defendingPlayer.getPlayersArmy();
        } else {
            defendingPlayer.setPlayersArmy(defendersArmy);
        }
        armiesList.add(attackersArmy);
        armiesList.add(defendersArmy);

        placeAttackersArmy();
        placeDefendersArmy();
        initialFieldsSettings();
        actualArmyTurn = attackersArmy;

        sortUnitsOrderList();
        placeAvatars();
        placeActionPanel();
        showActualUnitMovementRange();
        addUnitImagesMouseEvents();
        addTilesMouseEvents();
        fillContainers();
        initialScaling();

    }

    private void placeActionPanel() {
        fleeBack = new SVGPath();
        skipBack = new SVGPath();
        autoFightBack = new SVGPath();
        settingsBack = new SVGPath();
        flee = new ImageView();
        skip = new ImageView();
        autoFight = new ImageView();
        settings = new ImageView();

        fleeBack.setContent(ShapeStorage.initBackPath);
        skipBack.setContent(ShapeStorage.initBackPath);
        autoFightBack.setContent(ShapeStorage.initBackPath);
        settingsBack.setContent(ShapeStorage.initBackPath);

        flee.setImage(new Image(GraphicsStorage.flee));
        skip.setImage(new Image(GraphicsStorage.time));
        autoFight.setImage(new Image(GraphicsStorage.autoFight));
        settings.setImage(new Image(GraphicsStorage.settingsIcon));

        flee.setStyle(flee.getStyle()+GraphicsStorage.combatOptionScale);
        skip.setStyle(skip.getStyle()+GraphicsStorage.combatOptionScale);
        autoFight.setStyle(autoFight.getStyle()+GraphicsStorage.combatOptionScale);
        settings.setStyle(settings.getStyle()+GraphicsStorage.combatOptionScale);

        fleeBack.setFill(ColorsStorage.brown);
        fleeBack.setStroke(ColorsStorage.black);
        fleeBack.setStrokeWidth(3);
        skipBack.setFill(ColorsStorage.brown);
        skipBack.setStroke(ColorsStorage.black);
        skipBack.setStrokeWidth(3);
        autoFightBack.setFill(ColorsStorage.brown);
        autoFightBack.setStroke(ColorsStorage.black);
        autoFightBack.setStrokeWidth(3);
        settingsBack.setFill(ColorsStorage.brown);
        settingsBack.setStroke(ColorsStorage.black);
        settingsBack.setStrokeWidth(3);

        battlefieldPanel.getChildren().addAll(fleeBack);
        battlefieldPanel.getChildren().addAll(skipBack);
        battlefieldPanel.getChildren().addAll(autoFightBack);
        battlefieldPanel.getChildren().addAll(settingsBack);

        battlefieldPanel.getChildren().addAll(flee);
        battlefieldPanel.getChildren().addAll(skip);
        battlefieldPanel.getChildren().addAll(autoFight);
        battlefieldPanel.getChildren().addAll(settings);


        AnchorPane.setBottomAnchor(fleeBack,30.0);
        AnchorPane.setBottomAnchor(skipBack,30.0);
        AnchorPane.setBottomAnchor(autoFightBack,30.0);
        AnchorPane.setBottomAnchor(settingsBack,30.0);
        AnchorPane.setBottomAnchor(flee,45.0);
        AnchorPane.setBottomAnchor(skip,45.0);
        AnchorPane.setBottomAnchor(autoFight,45.0);
        AnchorPane.setBottomAnchor(settings,45.0);

        AnchorPane.setLeftAnchor(settingsBack,50.0);
        AnchorPane.setLeftAnchor(fleeBack,130.0);
        AnchorPane.setLeftAnchor(skipBack,210.0);
        AnchorPane.setLeftAnchor(autoFightBack,290.0);
        AnchorPane.setLeftAnchor(settings,65.0);
        AnchorPane.setLeftAnchor(flee,145.0);
        AnchorPane.setLeftAnchor(skip,225.0);
        AnchorPane.setLeftAnchor(autoFight,305.0);


        flee.setOnMouseClicked((MouseEvent e) -> {
            flee();
        });

        flee.setOnMouseEntered((MouseEvent e)->{
            flee.setStyle(flee.getStyle()+GraphicsStorage.avatarShadowEffect);
        });

        flee.setOnMouseExited((MouseEvent e)->{
            flee.setStyle(flee.getStyle()+GraphicsStorage.nullShadow);
        });
        settings.setOnMouseClicked((MouseEvent e) -> {
            if (!isAnimationOn){
                openSettings();
            }

        });

        settings.setOnMouseEntered((MouseEvent e)->{
            settings.setStyle(settings.getStyle()+GraphicsStorage.avatarShadowEffect);
        });

        settings.setOnMouseExited((MouseEvent e)->{
            settings.setStyle(settings.getStyle()+GraphicsStorage.nullShadow);
        });

        skip.setOnMouseClicked((MouseEvent e) -> {
            skipTurn();
        });

        skip.setOnMouseEntered((MouseEvent e)->{
            skip.setStyle(skip.getStyle()+GraphicsStorage.avatarShadowEffect);
        });

        skip.setOnMouseExited((MouseEvent e)->{
            skip.setStyle(skip.getStyle()+GraphicsStorage.nullShadow);
        });

        autoFight.setOnMouseClicked((MouseEvent e) -> {
            computerMove();
        });

        autoFight.setOnMouseEntered((MouseEvent e)->{
            autoFight.setStyle(autoFight.getStyle()+GraphicsStorage.avatarShadowEffect);
        });

        autoFight.setOnMouseExited((MouseEvent e)->{
            autoFight.setStyle(autoFight.getStyle()+GraphicsStorage.nullShadow);
        });

        settings.setPickOnBounds(true);
        flee.setPickOnBounds(true);
        skip.setPickOnBounds(true);
        autoFight.setPickOnBounds(true);



    }

    private void openSettings() {
//        moveUnitAnimation(actualUnitArena.getField(1,2));
//        System.out.println(dx + ","+dy);
        isAnimationOn=true;
        rotate();
        scale();
    }

    private void moveUnitAnimation(Field target) {
        actualUnitField.getLabelBack().setVisible(false);
        actualUnitField.getLabel().setVisible(false);
        actualUnitField.getTile().setFill(actualUnitField.getInitialTileColor());
        hideActualUnitMovementRange();
        hideActualUnitATKRange();
        saveX = (int)actualUnitTurn.getImage().getTranslateX() + wh;
        saveY = (int)actualUnitTurn.getImage().getTranslateY()+wh;
        unitPath = "m " + saveX + " " + saveY  + createPath(target);
//        System.out.println(unitPath);
        path=unitPath;

        unitPathShape.setContent(unitPath);
        transition.setCycleCount(1);
        transition.setDuration(Duration.seconds(actualAnimationDuration*GlobalSettings.unitSpeed));
        transition.setPath(unitPathShape);
        transition.setNode(actualUnitTurn.getImage());
        actualUnitTurn.getImage().toFront();
    }

    private void showActionButtons(boolean trueOrFalse){
        skip.setVisible(trueOrFalse);
        autoFight.setVisible(trueOrFalse);
        flee.setVisible(trueOrFalse);
        settings.setVisible(trueOrFalse);
        skipBack.setVisible(trueOrFalse);
        autoFightBack.setVisible(trueOrFalse);
        fleeBack.setVisible(trueOrFalse);
        settingsBack.setVisible(trueOrFalse);
    }

    private String leftOrRight(int dy){
        if (dy>0){
            return "right";
        } else if (dy<0){
            return "left";
        } else {
            return "mid";
        }
    }

    private String upOrDown(int dx){
        if (dx>0){
            return "down";
        } else if (dx<0){
            return "up";
        } else {
            return "mid";
        }
    }

    private StringBuilder createPath(Field target){
        int ax = actualUnitField.getX();
        int ay = actualUnitField.getY();
        int tx = target.getX();
        int ty = target.getY();
        int x = tx-ax;
        int y = ty-ay;

        // TODO: 25.10.2020 make it work
        ArrayList<Field> fields = getFieldsOnWay(target);
        int counter=0;
        StringBuilder path= new StringBuilder();
        for (Field field:fields){
            if (!field.isUnitHere()){
                counter++;
            }
        }
        fieldsOnWay=fields.size();

//        System.out.println(dx +"," + dy +"   " + fields.size());
        if (counter==fields.size()){
            if (dx.equals("mid") && dy.equals("right")){
                for (int i=1; i<=fields.size();i++){
                    String temp = " l " + AnimationControls.oneRightX + " " + AnimationControls.oneRightY;
                    path.append(temp);
                }
            } else if (dx.equals("mid") && dy.equals("left")){
                for (int i=1; i<=fields.size();i++){
                    String temp = " l " + AnimationControls.oneLeftX + " " + AnimationControls.oneLeftY;
                    path.append(temp);
                }
            } else if (dx.equals("down") && dy.equals("mid")){
                for (int i=1; i<=fields.size();i++){
                    String temp = " l " + AnimationControls.oneUpX + " " + AnimationControls.oneUpY;
                    path.append(temp);
                }
            } else if (dx.equals("up") && dy.equals("mid")){
                for (int i=1; i<=fields.size();i++){
                    String temp = " l " + AnimationControls.oneDownX + " " + AnimationControls.oneDownY;
                    path.append(temp);
                }
            } else if (dx.equals("down") && dy.equals("right")){
                for (int i=1; i<=y;i++){
                    String temp = " l " + AnimationControls.oneRightX + " " + AnimationControls.oneRightY;
                    path.append(temp);
                }
                for (int i=1; i<=x;i++){
                    String temp = " l " + AnimationControls.oneUpX + " " + AnimationControls.oneUpY;
                    path.append(temp);
                }
            } else if (dx.equals("up") && dy.equals("right")){
                for (int i=1; i<=y;i++){
                    String temp = " l " + AnimationControls.oneRightX + " " + AnimationControls.oneRightY;
                    path.append(temp);
                }
                for (int i=1; i<=-x;i++){
                    String temp = " l " + AnimationControls.oneDownX + " " + AnimationControls.oneDownY;
                    path.append(temp);
                }
            } else if (dx.equals("down") && dy.equals("left")){
                for (int i=1; i<=-y;i++){
                    String temp = " l " + AnimationControls.oneLeftX + " " + AnimationControls.oneLeftY;
                    path.append(temp);
                }
                for (int i=1; i<=x;i++){
                    String temp = " l " + AnimationControls.oneUpX + " " + AnimationControls.oneUpY;
                    path.append(temp);
                }
            } if (dx.equals("up") && dy.equals("left")){
                for (int i=1; i<=-y;i++){
                    String temp = " l " + AnimationControls.oneLeftX + " " + AnimationControls.oneLeftY;
                    path.append(temp);
                }
                for (int i=1; i<=-x;i++){
                    String temp = " l " + AnimationControls.oneDownX + " " + AnimationControls.oneDownY;
                    path.append(temp);
                }
            }

        } else{
            StringBuilder newPath = new StringBuilder();
            Field firstObstacle = new Field(-1,-1,null);
            boolean firstRun=true;
            for (Field field:fields){
                if (field.isUnitHere() && firstRun){
                    firstObstacle=field;
                    firstRun=false;
                }
            }
            int index = fields.indexOf(firstObstacle);
            if (index-1!=-1){
                newPath =  createPath(fields.get(index-1));
            }
            newPath.append(createWorkAroundPath(firstObstacle));
            System.out.println("wrong moveX value");

            return newPath;
        }

        actualAnimationDuration=fields.size()*4/5;
//        System.out.println(path);
        return path;
    }

    private StringBuilder createWorkAroundPath(Field firstObstacle) {
        StringBuilder path = new StringBuilder();


        return path;
    }

    private ArrayList<Field> getFieldsOnWay(Field target){
        ArrayList<Field> fields = new ArrayList<>();

        int ax = actualUnitField.getX();
        int ay = actualUnitField.getY();
        int tx = target.getX();
        int ty = target.getY();
        int dx = tx-ax;
        int dy = ty-ay;
        this.dx=upOrDown(dx);
        this.dy=leftOrRight(dy);

        // TODO: 25.10.2020 make it work, add detector for other fields

        if (upOrDown(dx).equals("mid") && leftOrRight(dy).equals("right")){
            for (int i=1;i<=dy;i++){
                fields.add(actualUnitArena.getField(ax,ay+i));
            }
        } else if (upOrDown(dx).equals("mid") && leftOrRight(dy).equals("left")){
            for (int i=1;i<=-dy;i++){
                fields.add(actualUnitArena.getField(ax,ay-i));
            }
        } else if (upOrDown(dx).equals("down") && leftOrRight(dy).equals("mid")){
            for (int i=1;i<=dx;i++){
                fields.add(actualUnitArena.getField(ax+i,ay));
            }
        } else if (upOrDown(dx).equals("up") && leftOrRight(dy).equals("mid")){
            for (int i=1;i<=-dx;i++){
                fields.add(actualUnitArena.getField(ax-i,ay));
            }
        } else if (upOrDown(dx).equals("down") && leftOrRight(dy).equals("left")){
            for (int i=1;i<=-dy;i++){
                fields.add(actualUnitArena.getField(ax,ay-i));
            }
            for (int i=1;i<=dx;i++){
                fields.add(actualUnitArena.getField(ax+i,ay-dy));
            }
        } else if (upOrDown(dx).equals("down") && leftOrRight(dy).equals("right")){
            for (int i=1;i<=dy;i++){
                fields.add(actualUnitArena.getField(ax,ay+i));
            }
            for (int i=1;i<=dx;i++){
                fields.add(actualUnitArena.getField(ax+i,ay+dy));
            }
        } else if (upOrDown(dx).equals("up") && leftOrRight(dy).equals("left")){
            for (int i=1;i<=-dy;i++){
                fields.add(actualUnitArena.getField(ax,ay-i));
            }
            for (int i=1;i<=-dx;i++){
                fields.add(actualUnitArena.getField(ax-i,ay-dy));
            }
        } else if (upOrDown(dx).equals("up") && leftOrRight(dy).equals("right")){
            for (int i=1;i<=dy;i++){
                fields.add(actualUnitArena.getField(ax,ay+i));
            }
            for (int i=1;i<=-dx;i++){
                fields.add(actualUnitArena.getField(ax-i,ay+dy));
            }
        }
//        for (Field field:fields){
//            System.out.println(field.getX() + "," + field.getY());
//        }
        return fields;
    }

    private void skipTurn() {
        hideActualUnitATKRange();
        hideActualUnitMovementRange();
        actualUnitField.getTile().setFill(actualUnitTurn.getFractionColor());
        nextUnitTurn();
    }

    private void hideActualUnitATKRange(){
        if (actualUnitTurn.getATKRange()>1){
            for (Unit unit:unitsTurnOrder){
                if (CombatSystem.isInATKRange(actualUnitTurn,unit)
                        && !actualPlayerTurn.getPlayersArmy().contains(unit)
                        && unit.getType().equals(actualUnitArena.getType())){
                    Field field = actualUnitArena.getField(unit.getActualX(),unit.getActualY());
                    field.getTile().setFill(field.getInitialTileColor());
                }
            }
            actualUnitField.getTile().setFill(actualUnitField.getInitialTileColor());
        }
    }

    private void hideActualUnitMovementRange(){
        Field field;
        Arena arena = actualUnitArena;
        for (int k = 1; k <= Arena.height; k++) {
            for (int n = 1; n <= Arena.width; n++) {
                field = arena.getField(k,n);
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && !field.equals(arena.getFields()[actualUnitField.getX() - 1][actualUnitField.getY() - 1])//field is not actual unit position
                        && !field.isUnitHere()) {    //field doesn't contain unit
                    field.getTile().setFill(field.getInitialTileColor());
                } else if (CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && !field.equals(arena.getFields()[actualUnitField.getX() - 1][actualUnitField.getY() - 1])//field is not actual unit position
                        && field.isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(field.getUnit())) {             //field  contain unit
                    field.getTile().setFill(field.getUnit().getFractionColor());
                }
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && k * n == 50) {
//                    attackingUnitStatInfoPanel.setVisible(false);
                    actualUnitField.getTile().setFill(actualUnitField.getUnit().getFractionColor());
                }
            }
        }
        actualUnitField.getUnit().getImage().setStyle(actualUnitField.getUnit().getImage().getStyle()+GraphicsStorage.nullShadow);
    }

    private void showActualUnitMovementRange() {
        if (Players.getHumanPlayers().contains(actualPlayerTurn)){
//            System.out.println("Show unit movement range");
            Unit unit = actualUnitTurn;
            Arena arena = actualUnitArena;
//            if (isMaximized ) {
//                updateUnitInfoPanel();
//            }
            if (actualPlayerTurn.equals(attackingPlayer)){
                avatar2.setStyle(avatar2.getStyle()+GraphicsStorage.nullShadow);
                avatar1.setStyle(avatar1.getStyle()+GraphicsStorage.avatarShadowEffect);
            } else if (actualPlayerTurn.equals(defendingPlayer)){
                avatar1.setStyle(avatar1.getStyle()+GraphicsStorage.nullShadow);
                avatar2.setStyle(avatar2.getStyle()+GraphicsStorage.avatarShadowEffect);
            }
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    Field field = arena.getFields()[i - 1][j - 1];
                    if (CombatSystem.isFieldInUnitRange(unit, field)
                            && !field.equals(actualUnitField)
                            && !arena.getIsUnitHere(i, j)) {
                        field.getTile().setFill(unit.getFractionColor());
                    } else if (CombatSystem.isFieldInUnitRange(unit, field)
                            && !field.equals(actualUnitField)
                            && arena.getIsUnitHere(i, j)
                            && !actualPlayerTurn.getPlayersArmy().contains(field.getUnit())) {
                        field.getTile().setFill(ColorsStorage.attack);
                    }
                }
            }
            actualUnitField.getUnit().getImage().setStyle(actualUnitField.getUnit().getImage().getStyle()+GraphicsStorage.unitShadow);
            showUnitATKRange();
        }
        else {
            computerMove();
        }
    }

    private void attackLeftToRightAnimation(Field target){
        parallel.setCycleCount(1);

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(target.getUnit().getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByX(40);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByX(-40);
        attackerPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase2.setNode(actualUnitTurn.getImage());
        attackerPhase2.setCycleCount(1);

        defenderPhase1.setByX(0);
        defenderPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        defenderPhase1.setNode(target.getUnit().getImage());
        defenderPhase1.setCycleCount(1);
        defenderPhase1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Soundtracks.playSound(Soundtracks.swordSound);
            }
        });

       defenderPhase2.setByX(15);
       defenderPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
       defenderPhase2.setNode(target.getUnit().getImage());
       defenderPhase2.setCycleCount(1);

        defenderPhase3.setByX(-15);
        defenderPhase3.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase3.setNode(target.getUnit().getImage());
        defenderPhase3.setCycleCount(1);

        isAnimationOn=true;
        parallel.play();
        parallel.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackRightToLeftAnimation(Field target){
        parallel.setCycleCount(1);

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByX(-40);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByX(40);
        attackerPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase2.setNode(actualUnitTurn.getImage());
        attackerPhase2.setCycleCount(1);

        defenderPhase1.setByX(0);
        defenderPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        defenderPhase1.setNode(target.getUnit().getImage());
        defenderPhase1.setCycleCount(1);
        defenderPhase1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Soundtracks.playSound(Soundtracks.swordSound);
            }
        });

        defenderPhase2.setByX(-15);
        defenderPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase2.setNode(target.getUnit().getImage());
        defenderPhase2.setCycleCount(1);

        defenderPhase3.setByX(15);
        defenderPhase3.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase3.setNode(target.getUnit().getImage());
        defenderPhase3.setCycleCount(1);

        isAnimationOn=true;
        parallel.play();
        parallel.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackUpToDownAnimation(Field target){
        parallel.setCycleCount(1);

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByY(50);
        attackerPhase1.setByX(20);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByY(-50);
        attackerPhase2.setByX(-20);
        attackerPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase2.setNode(actualUnitTurn.getImage());
        attackerPhase2.setCycleCount(1);

        defenderPhase1.setByX(0);
        defenderPhase1.setByY(0);
        defenderPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        defenderPhase1.setNode(target.getUnit().getImage());
        defenderPhase1.setCycleCount(1);
        defenderPhase1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Soundtracks.playSound(Soundtracks.swordSound);
            }
        });

        defenderPhase2.setByX(9);
        defenderPhase2.setByY(9);
        defenderPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase2.setNode(target.getUnit().getImage());
        defenderPhase2.setCycleCount(1);

        defenderPhase3.setByX(-9);
        defenderPhase3.setByY(-9);
        defenderPhase3.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase3.setNode(target.getUnit().getImage());
        defenderPhase3.setCycleCount(1);

        isAnimationOn=true;
        parallel.play();
        parallel.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackDownToUpAnimation(Field target){
        parallel.setCycleCount(1);

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByY(-50);
        attackerPhase1.setByX(-20);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByY(50);
        attackerPhase2.setByX(20);
        attackerPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase2.setNode(actualUnitTurn.getImage());
        attackerPhase2.setCycleCount(1);

        defenderPhase1.setByX(0);
        defenderPhase1.setByY(0);
        defenderPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        defenderPhase1.setNode(target.getUnit().getImage());
        defenderPhase1.setCycleCount(1);
        defenderPhase1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Soundtracks.playSound(Soundtracks.swordSound);
            }
        });

        defenderPhase2.setByX(-9);
        defenderPhase2.setByY(-9);
        defenderPhase2.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase2.setNode(target.getUnit().getImage());
        defenderPhase2.setCycleCount(1);

        defenderPhase3.setByX(9);
        defenderPhase3.setByY(9);
        defenderPhase3.setDuration(Duration.seconds(GlobalSettings.attackSpeed/2));
        defenderPhase3.setNode(target.getUnit().getImage());
        defenderPhase3.setCycleCount(1);

        isAnimationOn=true;
        parallel.play();
        parallel.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void showUnitATKRange(){
        for (Unit unit:unitsTurnOrder){
            if (CombatSystem.isInATKRange(actualUnitTurn,unit)
                    && !actualPlayerTurn.getPlayersArmy().contains(unit)
                    && unit.getType().equals(actualUnitArena.getType())){
                Field field = actualUnitArena.getField(unit.getActualX(),unit.getActualY());
                field.getTile().setFill(ColorsStorage.attack);
            }
        }
    }

    private void addUnitImagesMouseEvents(){
        for (Unit unit:unitsTurnOrder){
            unit.getImage().setPickOnBounds(false);
            unit.getImage().setOnMouseEntered((MouseEvent e) ->{
                unit.getImage().setStyle(unit.getImage().getStyle()+ GraphicsStorage.unitShadow);
                if (GlobalSettings.showUnitStatPanel){
                    showUnitStatPanel(unit);
                }
            });
            unit.getImage().setOnMouseExited((MouseEvent e) ->{
                if (!unit.equals(actualUnitTurn)){
                    unit.getImage().setStyle(unit.getImage().getStyle()+ GraphicsStorage.nullShadow);
                }
                if (GlobalSettings.showUnitStatPanel){
                    unitStatPanel.setVisible(false);
                }
            });
            unit.getImage().setOnMouseClicked((MouseEvent e) ->{
                attackUnit(unit);
            });
        }
    }

    private void attackUnit(Unit unit) {
        Field target;
        if (!actualPlayerTurn.getPlayersArmy().contains(unit)){
            target=actualUnitArena.getField(unit.getActualX(),unit.getActualY());
            if (target.isUnitHere()
                    && !actualPlayerTurn.getPlayersArmy().contains(target.getUnit())
                    && actualUnitArena.getType().equals(target.getUnit().getType())
                    && CombatSystem.isInATKRange(actualUnitTurn, target.getUnit())
                    && actualUnitTurn.getATKRange() > 1) {
                Soundtracks.playSound(Soundtracks.archerSound);
                action = "archer: " + target.getUnit().getName();
                wasAttackRanged=true;
                humanPlayerAttack(target);
            } else if (target.isUnitHere()
                    && !actualPlayerTurn.getPlayersArmy().contains(target.getUnit())
                    && actualUnitArena.getType().equals(target.getUnit().getType())
                    && CombatSystem.isFieldInUnitRange(actualUnitTurn, target)
                    && actualUnitTurn.getATKRange() == 1
                    && isActualUnitNearby(target)) {
                Soundtracks.playSound(Soundtracks.swordSound);
                action = "close combat";
                attackAnimationInCorrectDirection(target);
            } else if (target.isUnitHere()
                    && !actualPlayerTurn.getPlayersArmy().contains(target.getUnit())
                    && actualUnitArena.getType().equals(target.getUnit().getType())
                    && CombatSystem.isFieldInUnitRange(actualUnitTurn, target)
                    && actualUnitTurn.getATKRange() == 1) {
                action = "move then attack";
                Field temp = target;
//                moveThenAttack=true;
                target = returnBestUnoccupiedField(target);
                moveUnit(target,temp);
//                Soundtracks.playSound(Soundtracks.swordSound);
//                humanPlayerAttack(target);
            }
        }

    }

    public boolean isActualUnitNearby(Field target){
        Field field;
        for (int x = target.getX()-1;x<= target.getX()+1;x++){
            for (int y= target.getY()-1;y<= target.getY()+1;y++){
                if (x>0 && y>0 && x<=5 && y<=10){
                    field=actualUnitArena.getField(x,y);
                    if (field.equals(actualUnitField)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void restoreTileDefault(Field target){
        target.getTile().setFill(target.getInitialTileColor());
        target.getUnit().getImage().setVisible(false);
        target.getLabel().setVisible(false);
        target.getLabelBack().setVisible(false);
    }

    private void humanPlayerAttack(Field target){
        if (CombatSystem.dealDamageIsKilled(actualUnitTurn, target.getUnit())){
            restoreTileDefault(target);
            unitsTurnOrder.remove(target.getUnit());
            target.emptyUnit();
            resizeIndexArray();
        } else  {
            target.getLabel().setText("" + target.getUnit().getAmount());
        }
        hideActualUnitMovementRange();
        hideActualUnitATKRange();

        nextUnitTurn();
    }

    private void commonMoveUnitMethod(Field target){
        isAnimationOn=false;
        hideActualUnitATKRange();
        hideActualUnitMovementRange();
        actualUnitField.getTile().setFill(actualUnitField.getInitialTileColor());
        target.getTile().setFill(actualUnitTurn.getFractionColor());
        actualUnitField.getLabel().setVisible(false);
        target.getLabel().setVisible(true);
        actualUnitField.getLabelBack().setVisible(false);
        target.getLabelBack().setVisible(true);
        actualUnitTurn.setActualPosition(target.getX(), target.getY());
        target.setUnit(actualUnitTurn);
        actualUnitField.emptyUnit();
        actualUnitField = target;
        actualUnitField.getLabel().setText("" + actualUnitField.getUnit().getAmount());
    }

    private void moveUnit( Field target) {

        moveUnitAnimation(target);
        actualUnitField.getTile().setFill(actualUnitField.getInitialTileColor());
        isAnimationOn=true;
        if (fieldsOnWay==1){
            PathTransition pt = AnimationControls.moveOne(actualUnitTurn,path);
            pt.play();
            pt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               commonMoveUnitMethod(target);
                nextUnitTurn();
            }
        });
            isAnimationOn=false;
        } else if (fieldsOnWay>1){
            transition.play();
            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    commonMoveUnitMethod(target);
                    nextUnitTurn();
                }
            });
        }



    }

    private void moveUnit( Field freeTile,Field enemyUnit) {
        Field enemy = enemyUnit;
        moveUnitAnimation(freeTile);
        isAnimationOn=true;
        actualUnitField.getTile().setFill(actualUnitField.getInitialTileColor());

        if (fieldsOnWay==1){
            PathTransition pt = AnimationControls.moveOne(actualUnitTurn,path);
            pt.play();
            pt.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    commonMoveUnitMethod(freeTile);
                    attackAnimationInCorrectDirection(enemy);
                }
            });
        } else if (fieldsOnWay>1){
            transition.play();
            transition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    commonMoveUnitMethod(freeTile);
                    attackAnimationInCorrectDirection(enemy);
                }
            });
        }
    }

    private void attackAnimationInCorrectDirection(Field target) {
        if (getOrientation(target).equals("toRight")){
            attackLeftToRightAnimation(target);
        } else if (getOrientation(target).equals("toLeft")){
            attackRightToLeftAnimation(target);
        }else if (getOrientation(target).equals("toDown")){
            attackUpToDownAnimation(target);
        } else if (getOrientation(target).equals("toUp")){
            attackDownToUpAnimation(target);
        }
//        else if (getOrientation(target).equals("toRD")){
//            attackDiagonallyToRightDown(target);
//        } else if (getOrientation(target).equals("toLD")){
//            attackDiagonallyToLeftDown(target);
//        } else if (getOrientation(target).equals("toRU")){
//            attackDiagonallyToRightUp(target);
//        } else if (getOrientation(target).equals("toLU")){
//            attackDiagonallyToLeftUp(target);
//        }

    }

    private String getOrientation(Field target){

        int dx = target.getX() -  actualUnitTurn.getActualX();
        int dy = target.getY() -  actualUnitTurn.getActualY();

        if (dx==0 && dy==1){
            return "toRight";
        } else if (dx==0 && dy==-1){
            return "toLeft";
        } else if (dx==1 && dy==0){
            return "toDown";
        } else if (dx==-1 && dy==0){
            return "toUp";
        } else if (dx==1 && dy==1){
            return "toRD";
        } else if (dx==1 && dy==-1){
            return "toLD";
        } else if (dx==-1 && dy==1){
            return "toRU";
        } else if (dx==-1 && dy==-1){
            return "toLU";
        }

        return "none";
    }

    private Field returnBestUnoccupiedField(Field target){
        Field check = new Field(0,0,null);
        if (target.getY()-1>=1
                && !actualUnitArena.getField(target.getX(),target.getY()-1).isUnitHere()
                && fieldPositions(actualUnitTurn,target).equals("left")){
            return actualUnitArena.getField(target.getX(),target.getY()-1);
        } else if (target.getY()+1<=10
                && !actualUnitArena.getField(target.getX(),target.getY()+1).isUnitHere()
                && fieldPositions(actualUnitTurn,target).equals("right")) {
            return actualUnitArena.getField(target.getX(),target.getY()+1);
        } else if (target.getX()-1>=1
                && !actualUnitArena.getField(target.getX()-1,target.getY()).isUnitHere()
                && fieldPositions(actualUnitTurn,target).equals("up")) {
            return actualUnitArena.getField(target.getX()-1,target.getY());
        } else if (target.getX()+1<=10
                && !actualUnitArena.getField(target.getX()+1,target.getY()).isUnitHere()
                && fieldPositions(actualUnitTurn,target).equals("down")) {
            return actualUnitArena.getField(target.getX()+1,target.getY());
        }
        for (int x= target.getX()-1;x<= target.getX()+1;x++){
            for (int y=target.getY()-1;y<=target.getY();y++){
                if (x>=1 && x<=5 && y>=1  && y<=10 && actualUnitArena.getField(x,y)!=null && !actualUnitArena.getField(x,y).equals(target)
                        && !actualUnitArena.getField(x,y).isUnitHere()){
//                    System.out.println(actualUnitArena.getField(x,y).getCords());
                    return actualUnitArena.getField(x,y);
                }
            }
        }
        System.out.println("Returning empty field");
        return check;


    }

    private String fieldPositions(Unit actual, Field target) {
        if (actual.getActualY()- target.getY()<0){
            return "left";
        } else if (actual.getActualY()- target.getY()>0){
            return "right";
        } else if (actual.getActualX()- target.getX()<0){
            return "up";
        } else if (actual.getActualX()- target.getX()>0){
            return "down";
        }
        return "error";
    }

    private void showUnitStatPanel(Unit unit){
        unitStatPanel.setVisible(true);
        unitStatPanel.setStyle("-fx-background-color: #381d04; -fx-border-color: black; -fx-border-width: 3;");

        unitAtkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitHpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitRangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitMovementStat.setGraphic(new ImageView(GraphicsStorage.movement));

        unitInfoBack.setFill(unit.getFractionColor());
        unitInfoBack.setContent(ShapeStorage.avatarBack);
        unitInfoBack.setStrokeWidth(3);
        unitInfoBack.setStroke(ColorsStorage.black);

        unitAtkStat.setText(unit.getATK()+"");
        unitHpStat.setText(unit.getHP()+"");
        unitRangeStat.setText(unit.getATKRange()+"");
        unitMovementStat.setText(unit.getMovementRange()+"");
        selectedUnitActualPosition.setText("(" +unit.getActualX() + "," +  unit.getActualY() + ")");
        unitInfo.setText(unit.getName());
        unitInfoIcon.setGraphic(new ImageView(unit.getIcon()));
//        unitInfoIcon.getGraphic().setStyle(unitInfoIcon.getStyle()+GraphicsStorage.statImageView);
        unitInfoIcon.toFront();
        unitStatPanel.toFront();
    }

    private void computerMove(){
        isComputerMove=true;
        Field target;
        if (actualUnitTurn.getATKRange()>1
                && !pickBestUnitInATKRange().getName().equals("error404 unit")) {
            Soundtracks.playSound(Soundtracks.archerSound);
            target = actualUnitArena.getField(pickBestUnitInATKRange().getActualX(),pickBestUnitInATKRange().getActualY());
            action = "archer:  " + target.getUnit().getName();
            wasAttackRanged=true;
            humanPlayerAttack(target);
        } else if (actualUnitTurn.getATKRange()==1
                && !pickBestUnitInMovementRange().getName().equals("error404 unit")) {
            target = actualUnitArena.getField(pickBestUnitInMovementRange().getActualX(),pickBestUnitInMovementRange().getActualY());
            if (isActualUnitNearby(target)) {
                action = "close";
                attackAnimationInCorrectDirection(target);
            } else {
                action = "move then attack";
                Field tempF = target;
                target=returnBestUnoccupiedField(target);
                moveUnit(target,tempF);
            }
        } else if (pickBestUnitInMovementRange().getName().equals("error404 unit")) {
            int distance = actualUnitTurn.getMovementRange();
            if (actualUnitTurn.getActualY()-distance>=1 && (Players.getComputerPlayers().contains(actualPlayerTurn) || defendingPlayer.getName().equals(actualPlayerTurn.getName())) ) {
                target = actualUnitArena.getField(actualUnitTurn.getActualX(), actualUnitTurn.getActualY()-distance);
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn,target)
                        && !target.isUnitHere()) {
                    action = "Cmove";
                } else {
                    target = returnBestUnoccupiedField(target);
                    action = "Cmove2";
                }
                moveUnit(target);
            } else if ((distance+actualUnitTurn.getActualY())<=10 &&(Players.getHumanPlayers().contains(actualPlayerTurn) || attackingPlayer.getName().equals(actualPlayerTurn.getName()))) {
                target = actualUnitArena.getField(actualUnitTurn.getActualX(), actualUnitTurn.getActualY()+distance);
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn,target)
                        && !target.isUnitHere()) {
                    action = "Hmove";
                } else {
                    target = returnBestUnoccupiedField(target);
                    action = "Hmove2";
                }
                moveUnit(target);
            } else {
                action = "skipTurn";
                skipTurn();
            }
        } else {
            action = "Think more";
        }
    }

    private ArrayList<Unit> unitsInMovementRange(){
        ArrayList<Unit> unitsInRange = new ArrayList<>();
        for (int i=1;i<=Arena.height;i++){
            for (int j=1;j<=Arena.width;j++){
                if (actualUnitArena.getField(i,j).isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(actualUnitArena.getField(i,j).getUnit())
                        && CombatSystem.isFieldInUnitRange(actualUnitTurn,actualUnitArena.getField(i,j))){
                    unitsInRange.add(actualUnitArena.getField(i,j).getUnit());
                }
            }
        }
        return unitsInRange;
    }

    private Unit pickBestUnitInMovementRange(){
        Unit select = Units.emptyUnit;
        int importance=0;
        for (Unit unit:unitsInMovementRange()){
            if (unit.getATKRange()>1){
                select=unit;
                importance=2;
            } else if (importance<1
                    && unitsTurnOrder.indexOf(actualUnitTurn)+1 <unitsTurnOrder.size()
                    && unitsTurnOrder.get(unitsTurnOrder.indexOf(actualUnitTurn)+1).equals(unit) ) {
                select=unit;
            } else if (importance <1 && unitsTurnOrder.indexOf(actualUnitTurn)==unitsTurnOrder.size()
                    && unitsTurnOrder.get(0).equals(unit) ) {
                System.out.println("good");
                select=unit;
            } else if (importance<1){
                select=unit;
            }
        }
        return select;
    }

    private ArrayList<Unit> unitsInATKRange(){
        ArrayList<Unit> unitsInAttackRange = new ArrayList<>();
        for (int i=1;i<=Arena.height;i++){
            for (int j=1;j<=Arena.width;j++){
                if (actualUnitArena.getField(i,j).isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(actualUnitArena.getField(i,j).getUnit())
                        && CombatSystem.isInATKRange(actualUnitTurn,actualUnitArena.getField(i,j).getUnit())){
                    unitsInAttackRange.add(actualUnitArena.getField(i,j).getUnit());
                }
            }
        }
        return unitsInAttackRange;
    }

    private Unit pickBestUnitInATKRange(){
        Unit select = Units.emptyUnit;
        int importance=0;
        for (Unit unit:unitsInATKRange()){
            if (unit.getATKRange()>1){
                select=unit;
                importance=2;
            } else if (importance<1
                    && unitsTurnOrder.indexOf(actualUnitTurn)+1<unitsTurnOrder.size()
                    && unitsTurnOrder.get(unitsTurnOrder.indexOf(actualUnitTurn)+1).equals(unit) ) {
                select=unit;
            } else if (importance <1 &&unitsTurnOrder.indexOf(actualUnitTurn)==unitsTurnOrder.size()
                    && unitsTurnOrder.get(0).equals(unit) ) {
                select=unit;
            } else if (importance<1){
                select=unit;
            }
        }
        return select;
    }

    private void nextUnitTurn(){
//        System.out.println(action);
        if (isComputerMove){
            isComputerMove=false;
        }

        for (FractionGameData player:fightingPlayers){
            int counter=0;
            for (Unit unit:player.getPlayersArmy()){
                if (unit.getAmount()==0){
                    counter++;
                }
                if (counter==player.getPlayersArmy().size()){
                    looser = player;
                    gameEnded=true;
                    manageVictory();
                }
            }
        }


        int index = unitsTurnOrder.indexOf(actualUnitTurn);

        if (index<unitsTurnOrder.size()-1){
            actualUnitTurn=unitsTurnOrder.get(index+1);
        } else  {
            actualUnitTurn=unitsTurnOrder.get(0);
            turnCounter++;
        }
        manageInitiativeBar();

        for (Arena arena:arenasList){
            if (arena.getType().equals(actualUnitTurn.getType())){
                actualUnitArena = arena;
            }
        }
        for (FractionGameData player:fightingPlayers){
            if (player.getPlayersArmy().contains(actualUnitTurn)){
                actualPlayerTurn =player;
            }
        }
        for (int i=1;i<=Arena.height;i++){
            for (int j=1;j<=Arena.width;j++){
                if (actualUnitArena.getField(i,j).getUnit().equals(actualUnitTurn)){
                    actualUnitField = actualUnitArena.getField(i,j);
                }
            }
        }
        if (!gameEnded){
            showActualUnitMovementRange();

        }
//        System.out.println("Index: " + index);
//        System.out.println("Actual unit: " + actualUnitTurn.getName() + " initiative: " + actualUnitTurn.getInitiative()
//        + "  Amount: " + actualUnitTurn.getAmount() + "  Type: "  + actualUnitTurn.getType());

    }

    private void manageVictory() {
        for (FractionGameData player:fightingPlayers){
            if (!player.getName().equals(looser.getName())){
                winner = player;
            }
        }
        Sector sector = mapPanelController.attackedSector;
        sector.setOwner(winner);
        if (attackingPlayer==winner){
            Soundtracks.playMusic(Soundtracks.victoryMusic);
            showVictoryPanel(GraphicsStorage.vicGif);
        } else if (defendingPlayer==winner){
            Soundtracks.playMusic(Soundtracks.loseMusic);
            showVictoryPanel(GraphicsStorage.lostGif);
        }
    }

    private void showAvatars(boolean trueOrFalse){
        avatar1.setVisible(trueOrFalse);
        avatar2.setVisible(trueOrFalse);
        avatarBack1.setVisible(trueOrFalse);
        avatarBack2.setVisible(trueOrFalse);
    }

    private void showArenas(boolean trueOrFalse){
        for (Arena arena:arenasList){
            for (int i=1;i<=Arena.height;i++){
                for (int j=1;j<=Arena.width;j++){
                    arena.getField(i,j).getTile().setVisible(false);
                    if (arena.getField(i,j).isUnitHere()){
                        arena.getField(i,j).getUnit().getImage().setVisible(false);
                    }
                }
            }
        }
    }

    private void showVictoryPanel(String image){

        showArenas(false);
        showAvatars(false);
        showInitiativePanel(false);
        showActionButtons(false);
        victoryPanel.setVisible(true);
        victoryPanel.toFront();
        victoryPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
//        proceed.setGraphic(new ImageView(GraphicsStorage.select));
        proceed.setStyle(GraphicsStorage.begin+GraphicsStorage.select+GraphicsStorage.end);

        ImageView vicGif = new ImageView(image);
        vicGif.setStyle(GraphicsStorage.vicGifStyle);
        victoryGif.setGraphic(vicGif);

        winnerArmyBox.setHgap(10);
        winnerArmyBox.setVgap(0);
        looserArmyBox.setHgap(10);
        looserArmyBox.setVgap(0);
        winnerLostLabel.setText(attackingPlayer.getName() + " lost: ");
        looserLostLabel.setText(defendingPlayer.getName() + " lost: ");

        int lostCounter=0;
        for (FractionGameData player:fightingPlayers){
            ArrayList<JFXButton> buts = new ArrayList<>();
            ArrayList<JFXButton> labels = new ArrayList<>();
            ArrayList<Unit> lostUnits = new ArrayList<>();
            for (int i=0;i<attackersArmy.size();i++) {
                Unit unit = player.getPlayersArmy().get(i);
                JFXButton but = new JFXButton();
                JFXButton but2 = new JFXButton();
                buts.add(but);
                labels.add(but2);

                but.setPrefSize(100, 100);
                but.setMinSize(but.getPrefWidth(), but.getPrefHeight());
                but.setMaxSize(but.getPrefWidth(), but.getPrefHeight());
                but2.setPrefSize(100, 30);
                but2.setMinSize(but2.getPrefWidth(), but2.getPrefHeight());
                but2.setMaxSize(but2.getPrefWidth(), but2.getPrefHeight());
                but2.setStyle(GraphicsStorage.begin + GraphicsStorage.settingsBackground + GraphicsStorage.end+
                        GraphicsStorage.rectButt + GraphicsStorage.vicGifStyle
                        + "-fx-font-size: 10pt; -fx-text-fill: black;-fx-font-weight: bold;");
                but.setStyle(GraphicsStorage.begin + unit.getFractionBackground() + GraphicsStorage.end + GraphicsStorage.rectButt + GraphicsStorage.vicGifStyle);
                if (player.getName().equals(attackingPlayer.getName())){
                    winnerArmyBox.add(but, i, 0);
                    winnerArmyBox.add(but2, i, 1);
                } else {
                    looserArmyBox.add(but, i, 0);
                    looserArmyBox.add(but2, i, 1);
                }

                if (unit.getLostUnits() > 0) {
                    lostUnits.add(unit);
                    lostCounter++;
                }
            }

            for (int i=0;i<lostCounter;i++){
                Unit unit = lostUnits.get(i);
                JFXButton but = buts.get(7-i);
                JFXButton but2 = labels.get(7-i);

                if (lostUnits.contains(unit) && unit.getLostUnits() > 0) {
                    ImageView iv = new ImageView(unit.getIcon());
                    iv.setStyle(GraphicsStorage.victoryImageView);
                    but.setGraphic(iv);
                    but2.setText("" + unit.getLostUnits());
                    but2.setTextAlignment(TextAlignment.CENTER);

                }
            }
            lostCounter=0;
        }

    }

    private void showInitiativePanel(boolean trueOrFalse) {
        initBack.setVisible(trueOrFalse);
        initiativeTurn.setVisible(trueOrFalse);
        initiationText.setVisible(trueOrFalse);
        for (int i=0;i<3;i++){
            initiativeBackList.get(i).setVisible(trueOrFalse);
            initiativeLabels.get(i).setVisible(trueOrFalse);
        }
    }

    public void openMapPanel() {
        emptyRegister();
        Stage primaryStage = (Stage) battlefieldPanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../sample/mapPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flee(){
        if (Players.getHumanPlayers().contains(actualPlayerTurn)){
            looser=actualPlayerTurn;
            manageVictory();
        }
    }

    private void sortUnitsOrderList(){
        for (ArrayList<Unit> army:armiesList){
            for (Unit unit:army){
                if (unit.getAmount()>0){
                    unitsTurnOrder.add(unit);
                }
            }
        }
        double u1,u2;
        for (int i=0;i < unitsTurnOrder.size()-1;i++){
            for (int j=i+1;j<unitsTurnOrder.size();j++){
                u1=unitsTurnOrder.get(i).getInitiative();
                u2=unitsTurnOrder.get(j).getInitiative();

                if (u1 == u2){
                    if (attackersArmy.contains(unitsTurnOrder.get(i))){
                        unitsTurnOrder.set(i,unitsTurnOrder.get(i));
                        unitsTurnOrder.set(j,unitsTurnOrder.get(j));
                    } else if (attackersArmy.contains(unitsTurnOrder.get(j))){
                        Unit tempUnit = unitsTurnOrder.get(i);
                        unitsTurnOrder.set(i,unitsTurnOrder.get(j));
                        unitsTurnOrder.set(j,tempUnit);
                    }
                } else if (u1<u2){
                    Unit tempUnit = unitsTurnOrder.get(i);
                    unitsTurnOrder.set(i,unitsTurnOrder.get(j));
                    unitsTurnOrder.set(j,tempUnit);
                }



            }
        }
        actualUnitTurn=unitsTurnOrder.get(0);
        for (FractionGameData player:fightingPlayers){
            if (player.getPlayersArmy().contains(actualUnitTurn)){
                actualPlayerTurn =player;
            }
        }



        for (Arena arena:arenasList){
            for (int i=1;i<=Arena.height;i++){
                for (int j=1;j<=Arena.width;j++){
                    if (arena.getField(i,j).getUnit().equals(actualUnitTurn)){
                        actualUnitField=arena.getField(i,j);
                        actualUnitArena=arena;
                    }
                }
            }
        }

        resizeIndexArray();
        placeInitiativeBar();
        manageInitiativeBar();
    }

    private void placeAvatars() {
        avatarBack1=new SVGPath();
        avatarBack2=new SVGPath();
        avatar1=new ImageView(attackingPlayer.getAvatar());
        avatar2 = new ImageView(defendingPlayer.getAvatar());

        avatarBack1.setFill(attackingPlayer.getFraction().getFractionColor());
        avatarBack2.setFill(defendingPlayer.getFraction().getFractionColor());
        avatar1.setImage(new Image(attackingPlayer.getAvatar()));
        avatar2.setImage(new Image(defendingPlayer.getAvatar()));

        avatarBack1.setContent(ShapeStorage.avatarBack);
        avatarBack2.setContent(ShapeStorage.avatarBack);

        battlefieldPanel.getChildren().addAll(avatarBack1);
        battlefieldPanel.getChildren().addAll(avatarBack2);
        battlefieldPanel.getChildren().addAll(avatar1);
        battlefieldPanel.getChildren().addAll(avatar2);

        AnchorPane.setLeftAnchor(avatarBack1,50.0);
        AnchorPane.setLeftAnchor(avatar1,50.0);
        AnchorPane.setRightAnchor(avatarBack2,50.0);
        AnchorPane.setRightAnchor(avatar2,50.0);

        AnchorPane.setTopAnchor(avatarBack1,20.0);
        AnchorPane.setTopAnchor(avatarBack2,20.0);
        AnchorPane.setTopAnchor(avatar1,27.0);
        AnchorPane.setTopAnchor(avatar2,27.0);

        avatarBack1.setStroke(ColorsStorage.black);
        avatarBack1.setStrokeWidth(3);
        avatarBack2.setStroke(ColorsStorage.black);
        avatarBack2.setStrokeWidth(3);
    }

    private void placeInitiativeBar() {
        initiativeBack1=new SVGPath();
        initiativeBack1.setContent(ShapeStorage.initBackPath);
        initiativeBack2=new SVGPath();
        initiativeBack2.setContent(ShapeStorage.initBackPath);
        initiativeBack3=new SVGPath();
        initiativeBack3.setContent(ShapeStorage.initBackPath);
        initBack=new SVGPath();
        initBack.setContent(ShapeStorage.initMainBackPath);
        initiativeTurn.setContent(ShapeStorage.initTextPath);
        initiativeBackList.add(initiativeBack1);
        initiativeBackList.add(initiativeBack2);
        initiativeBackList.add(initiativeBack3);

        AnchorPane.setBottomAnchor(initiativeLabels.get(0),45.0);
        AnchorPane.setBottomAnchor(initiativeLabels.get(1),45.0);
        AnchorPane.setBottomAnchor(initiativeLabels.get(2),45.0);
        AnchorPane.setBottomAnchor(initiativeBackList.get(0),80.0);
        AnchorPane.setBottomAnchor(initiativeBackList.get(1),80.0);
        AnchorPane.setBottomAnchor(initiativeBackList.get(2),80.0);
        AnchorPane.setBottomAnchor(initiativeTurn,50.0);
        AnchorPane.setBottomAnchor(initiationText,50.0);
        AnchorPane.setBottomAnchor(initBack,45.0);

        AnchorPane.setRightAnchor(initiativeLabels.get(2),15.0);
        AnchorPane.setLeftAnchor(initiativeTurn,Constants.width-300);
        AnchorPane.setLeftAnchor(initiationText,Constants.width-290);
        AnchorPane.setRightAnchor(initiativeLabels.get(1),100.0);
        AnchorPane.setRightAnchor(initiativeLabels.get(0),185.0);
        AnchorPane.setRightAnchor(initiativeBackList.get(2),50.0);
        AnchorPane.setRightAnchor(initiativeBackList.get(1),135.0);
        AnchorPane.setRightAnchor(initiativeBackList.get(0),220.0);
        AnchorPane.setRightAnchor(initBack,40.0);


        initiativeLabels.get(0).setStyle(GraphicsStorage.initiativeImageView);
        initiativeLabels.get(1).setStyle(GraphicsStorage.initiativeImageView);
        initiativeLabels.get(2).setStyle(GraphicsStorage.initiativeImageView);
        initiativeTurn.setFill(ColorsStorage.brown);
        initiativeTurn.setStrokeWidth(3);
        initiativeTurn.setStroke(ColorsStorage.black);
        initiativeBackList.get(0).setStrokeWidth(3);
        initiativeBackList.get(1).setStrokeWidth(3);
        initiativeBackList.get(2).setStrokeWidth(3);
        initiativeBackList.get(0).setStroke(ColorsStorage.black);
        initiativeBackList.get(1).setStroke(ColorsStorage.black);
        initiativeBackList.get(2).setStroke(ColorsStorage.black);
        initBack.setFill(ColorsStorage.initBack);
        initBack.setStrokeWidth(3);
        initBack.setStroke(ColorsStorage.black);
        initiationText.setStyle("-fx-font-size: 12pt;-fx-text-fill: black;-fx-font-weight: bold;");
        battlefieldPanel.getChildren().add(initBack);
        battlefieldPanel.getChildren().add(initiativeTurn);
        battlefieldPanel.getChildren().addAll(initiativeBackList);
        battlefieldPanel.getChildren().addAll(initiativeLabels);
        battlefieldPanel.getChildren().addAll(initiationText);

    }

    private void resizeIndexArray(){
        int counter =0;
        indexArray = new int[unitsTurnOrder.size()*2];
        for (int i=0;i<unitsTurnOrder.size()*2;i++){
            if (counter<unitsTurnOrder.size()){
                indexArray[i]=i;
            } else  {
                indexArray[i]=i-unitsTurnOrder.size();
            }
            counter++;
        }
    }

    private void manageInitiativeBar(){
        int index = unitsTurnOrder.indexOf(actualUnitTurn);
        ImageView label;
        Unit unit;
        int startingIndex;

        if (index==0){
            startingIndex=0;
        } else if (index==1) {
            startingIndex = 1;
        } else {
            startingIndex = unitsTurnOrder.indexOf(actualUnitTurn);
        }

        int counter=0;
        boolean firstRun = true;
        if (unitsTurnOrder.size()==2){
            for (int i=startingIndex;i<startingIndex+3;i++) {
                if (counter == 0 && index==0) {
                    counter++;
                    index=1;
                } else if (counter==0 && index==1){
                    counter++;
                    index=0;
                } else  {
                    counter++;
                    if (index==1){
                        index=0;
                    } else  {
                        index=1;
                    }
                }
                unit = unitsTurnOrder.get(indexArray[index]);
                label = initiativeLabels.get(counter-1);
                label.setImage(null);
                initiativeBackList.get(counter-1).setFill(unit.getFractionColor());
                label.setStyle(GraphicsStorage.initiativeImageView);
                label.setImage(new ImageView(unit.getIcon()).getImage());
                if (firstRun) {
                    initiationText.setText("Turn: " + turnCounter + "  Next Unit: " + unit.getName());
                    firstRun = false;
                }
            }
        }
        else if (unitsTurnOrder.size()>2){
            for (int i=startingIndex;i<startingIndex+3;i++){
                unit = unitsTurnOrder.get(indexArray[i+1]);
                label=initiativeLabels.get(counter);
                label.setImage(null);
                initiativeBackList.get(counter).setFill(unit.getFractionColor());
                label.setStyle(GraphicsStorage.initiativeImageView);
                label.setImage(new ImageView(unit.getIcon()).getImage());

                if (firstRun){
                    initiationText.setText("Turn: "  + turnCounter  + "  Next Unit: " + unit.getName());
                    firstRun=false;
                }
                counter++;
            }
        }

    }

    private void addTilesMouseEvents(){
        SVGPath background;
        for (Arena arena:arenasList){
            for (int i=1;i<=Arena.height;i++){
                for (int j=1;j<=Arena.width;j++){
                    Field field= arena.getField(i,j);
                    background=field.getTile();
                    background.setOnMouseClicked((MouseEvent e) ->{
                        if ( !field.isUnitHere()
                                && CombatSystem.isFieldInUnitRange(actualUnitTurn, field)
                                && arena.getType().equals(actualUnitTurn.getType())
                                && !isAnimationOn) {
                            action = "move";
                            moveUnit(field);
                        }
                    });
                    background.setOnMouseEntered((MouseEvent e) ->{
                        if (!field.isUnitHere()
                                && !isAnimationOn){
                            field.getTile().setFill(ColorsStorage.select);
                        }
                    });
                    background.setOnMouseExited((MouseEvent e) ->{
                        if(!field.isUnitHere() &&CombatSystem.isFieldInUnitRange(actualUnitTurn,field)
                                && actualUnitTurn.getType().equals(arena.getType())
                                && !isAnimationOn){
                            field.getTile().setFill(actualUnitTurn.getFractionColor());
                        } else if (!field.isUnitHere()
                                && !isAnimationOn) {
                            field.getTile().setFill(field.getInitialTileColor());
                        }
                    });
                }
            }
        }
    }

    private void initialFieldsSettings() {

        for (Arena arena : arenasList) {
            ImageView unitIcon;
            SVGPath background;
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    for (ArrayList<Unit> army : armiesList) {
                        for (Unit unit : army) {
                            if (unit.getActualX() == i && unit.getActualY() == j && unit.getType().equals(arena.getType()) && unit.getAmount()>0) {
                                unitIcon=unit.getImage();
                                String tempStyle = unitIcon.getStyle();
                                unitIcon.setStyle(tempStyle + GraphicsStorage.imageViewSmall);
                                background=arena.getField(i,j).getTile();
                                background.setFill(unit.getFractionColor());
                                AnchorPane.setLeftAnchor(unitIcon,AnchorPane.getLeftAnchor(background)-42);
                                AnchorPane.setTopAnchor(unitIcon,AnchorPane.getTopAnchor(background)-48);
                                unitIcon.toFront();
                            }
                        }
                    }
                }
            }
        }
    }

    private void placeAttackersArmy() {
        for (Arena arena : arenasList) {
            for (Unit unit : attackersArmy) {
                if (arena.getType().equals(unit.getType()) && unit.isSet() && unit.getAmount() > 0) {
                    arena.getFields()[unit.getActualX() - 1][unit.getActualY() - 1].setUnit(unit);
                    unit.setImage(new ImageView(unit.getIcon()));
                    arena.getField(unit.getActualX(),unit.getActualY()).getLabel().setVisible(true);
                    arena.getField(unit.getActualX(),unit.getActualY()).getLabelBack().setVisible(true);
                    unit.getImage().setTranslateY(-20);
                }
            }
        }
    }

    private void placeDefendersArmy() {
        for (Arena arena : arenasList) {
            for (Unit unit : defendersArmy) {
                if (arena.getType().equals(unit.getType()) && unit.isSet() && unit.getAmount()>0) {
                    unit.setActualPosition(unit.getInitialX(), 11 - unit.getInitialY());
                    arena.getFields()[unit.getActualX() - 1][unit.getActualY() - 1].setUnit(unit);
                    ImageView unitIcon = new ImageView(unit.getIcon());
                    unitIcon.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    unit.setImage(unitIcon);
                    unit.getImage().setTranslateY(-20);
                    defendersIcons.add(unitIcon);
                    arena.getField(unit.getActualX(),unit.getActualY()).getLabel().setVisible(true);
                    arena.getField(unit.getActualX(),unit.getActualY()).getLabelBack().setVisible(true);
                }
            }
        }

    }

    private void fillArenasBackgrounds(){


        switch (Sectors.getSelectedSector().getActualFraction()){
            case "Jungle":
                airArenaBack = new ImageView(GraphicsStorage.combat_jng_air);
                landArenaBack = new ImageView(GraphicsStorage.combat_jng_land);
                waterArenaBack = new ImageView(GraphicsStorage.combat_jng_water);
                break;
            case "Atlantis":
                airArenaBack = new ImageView(GraphicsStorage.combat_atl_air);
                landArenaBack = new ImageView(GraphicsStorage.combat_atl_land);
                waterArenaBack = new ImageView(GraphicsStorage.combat_atl_water);
                break;
            case "Cyberpunk":
                airArenaBack = new ImageView(GraphicsStorage.combat_cbr_air);
                landArenaBack = new ImageView(GraphicsStorage.combat_cbr_land);
                waterArenaBack = new ImageView(GraphicsStorage.combat_cbr_water);
                break;
        }

        battlefieldPanel.getChildren().add(airArenaBack);
        battlefieldPanel.getChildren().add(landArenaBack);
        battlefieldPanel.getChildren().add(waterArenaBack);

        AnchorPane.setTopAnchor(landArenaBack,Constants.height/3);
        AnchorPane.setTopAnchor(waterArenaBack,Constants.height*2/3);


    }

    private void fillLists() {
        tilesList.add(airTiles);
        tilesList.add(landTiles);
        tilesList.add(waterTiles);
        arenasList.add(airArena);
        arenasList.add(landArena);
        arenasList.add(waterArena);
        fightingPlayers.add(attackingPlayer);
        fightingPlayers.add(defendingPlayer);
    }

    private void fillTilesColors() {
        Field field;
        Arena arena;
        SVGPath path,labelBack;
        Label label;

        for (int k=0;k<arenasList.size();k++){
            arena = arenasList.get(k);
            initiativeLabels.add(new ImageView());
            for (int i=1;i<=Arena.height;i++){
                for (int j=1;j<=Arena.width;j++){
                    field = arena.getField(i,j);
                    path = new SVGPath();
                    path.setContent(ShapeStorage.arenaTileSVGPath);
                    switch (arena.getType()){
                        case "Air":
                            path.setFill(ColorsStorage.airTile);
                            airTiles.add(path);
                            break;
                        case "Land":
                            path.setFill(ColorsStorage.landTile);
                            landTiles.add(path);
                            break;
                        case "Water":
                            path.setFill(ColorsStorage.waterTile);
                            waterTiles.add(path);
                            break;
                    }
                    field.setTile(path);
                    field.setInitialTileColor(path.getFill());
                    labelBack = field.getLabelBack();
                    labelBack.setContent(ShapeStorage.unitAmountLabel);
                    labelBack.setFill(ColorsStorage.brown);
                    labelBack.setStroke(ColorsStorage.black);
                    labelBack.setStrokeWidth(2);
                    label = field.getLabel();
                    label.setText("0");
                    label.setStyle(GraphicsStorage.smallLabel);

                    path.setStroke(ColorsStorage.black);
                    path.setStrokeWidth(1);
                    path.toFront();
                    label.setVisible(false);
                    labelBack.setVisible(false);


                    AnchorPane.setTopAnchor(path,  (i-1)*53.0 );
                    AnchorPane.setLeftAnchor(path, (i-1)*20 + (j-1)*45.0   );
                    AnchorPane.setTopAnchor(label,  (i-1)*53 +36.0);
                    AnchorPane.setLeftAnchor(label,  (i-1)*20 + (j-1)*45 + 36.0 );
                    AnchorPane.setTopAnchor(labelBack,  (i-1)*53 +34.0);
                    AnchorPane.setLeftAnchor(labelBack, (i-1)*20 + (j-1)*45 + 13.0 );
                    labelBack.toFront();
                    label.toFront();
                }
            }
        }
    }

    private void fillContainers(){
        airContainer.toFront();
        landContainer.toFront();
        waterContainer.toFront();
        for (Arena arena:arenasList){
            for (int i=1;i<=Arena.height;i++){
                for (int j=1;j<=Arena.width;j++){
                    if (arena.getType().equals("Air")){
                        airContainer.getChildren().add(arena.getField(i,j).getTile());
                        airContainer.getChildren().add(arena.getField(i,j).getLabel());
                        airContainer.getChildren().add(arena.getField(i,j).getLabelBack());
                    }
                    if (arena.getType().equals("Land")){
                        landContainer.getChildren().add(arena.getField(i,j).getTile());
                        landContainer.getChildren().add(arena.getField(i,j).getLabel());
                        landContainer.getChildren().add(arena.getField(i,j).getLabelBack());
                    }
                    if (arena.getType().equals("Water")){
                        waterContainer.getChildren().add(arena.getField(i,j).getTile());
                        waterContainer.getChildren().add(arena.getField(i,j).getLabel());
                        waterContainer.getChildren().add(arena.getField(i,j).getLabelBack());
                    }
                    arena.getField(i,j).getTile().toFront();
                    arena.getField(i,j).getLabel().toFront();
                    arena.getField(i,j).getLabelBack().toFront();
                }
            }
        }

        for (Unit unit:unitsTurnOrder){
            if (unit.getType().equals("Air")){
                airContainer.getChildren().add(unit.getImage());
            }
            if (unit.getType().equals("Land")){
                landContainer.getChildren().add(unit.getImage());

            }
            if (unit.getType().equals("Water")){
                waterContainer.getChildren().add(unit.getImage());

            }
            unit.getImage().toFront();
        }
//        airContainer.setStyle("-fx-background-color: orange;");
//        landContainer.setStyle("-fx-background-color: purple;");
//        waterContainer.setStyle("-fx-background-color: blue;");
    }

    private void scale(){


        switch (scaleCycle){
            case 1:
                scale1.setByX(-1.2);
                scale1.setByY(-1.2);
                landContainer.toFront();
                scale1.play();

                scale3.setByX(1.2);
                scale3.setByY(1.2);
                scale3.play();

                scaleCycle=2;
                break;
            case 2:
                scale2.setByX(1.2);
                scale2.setByY(1.2);
                scale2.play();

                scale3.setByX(-1.2);
                scale3.setByY(-1.2);
                scale3.play();
                scaleCycle=3;
                break;
            case 3:
                scale1.setByX(1.2);
                scale1.setByY(1.2);
                landContainer.toFront();
                scale1.play();

                scale2.setByX(-1.2);
                scale2.setByY(-1.2);
                scale2.play();

                scaleCycle=1;
                break;

        }
    }

    private void rotate(){


        switch (rotationCycle){
            case 1:
                transition1.setNode(landContainer);
                transition2.setNode(airContainer);
                transition3.setNode(waterContainer);

                waterContainer.toFront();
                transition1.play();
                transition2.play();
                transition3.play();

                rotationCycle=2;
                break;
            case 2:
                transition1.setNode(waterContainer);
                transition2.setNode(landContainer);
                transition3.setNode(airContainer);

                airContainer.toFront();
                transition1.play();
                transition2.play();
                transition3.play();

                rotationCycle=3;
                break;
            case 3:
                transition1.setNode(airContainer);
                transition2.setNode(waterContainer);
                transition3.setNode(landContainer);

                landContainer.toFront();
                transition1.play();
                transition2.play();
                transition3.play();

                rotationCycle=1;
                break;

        }
    }

    private void initialScaling(){
        isAnimationOn=true;
        scale1.setDuration(Duration.seconds(2));
        scale1.setNode(landContainer);
        scale1.setByX(0.8);
        scale1.setByY(0.8);
        landContainer.toFront();
        scale1.play();
        scale2.setDuration(Duration.seconds(2));
        scale2.setByX(-0.4);
        scale2.setByY(-0.4);
        scale2.setNode(airContainer);
        scale2.play();
        scale3.setDuration(Duration.seconds(2));
        scale3.setByX(-0.4);
        scale3.setByY(-0.4);
        scale3.setNode(waterContainer);
        scale3.play();
        scale1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
            }
        });
        scale2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
            }
        });
        scale3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isAnimationOn=false;
            }
        });

        transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(2));
        transition1.setByX(-730);
        transition1.setByY(-150);
        transition1.setNode(landContainer);

        transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(2));
        transition2.setByX(50);
        transition2.setByY(250);
        transition2.setNode(airContainer);

        transition3 = new TranslateTransition();
        transition3.setDuration(Duration.seconds(2));
        transition3.setByX(680);
        transition3.setByY(-100);
        transition3.setNode(waterContainer);

        attackerSequence.getChildren().add(attackerPhase1);
        attackerSequence.getChildren().add(attackerPhase2);
        defenderSequence.getChildren().add(defenderPhase1);
        defenderSequence.getChildren().add(defenderPhase2);
        defenderSequence.getChildren().add(defenderPhase3);
        parallel.getChildren().add(attackerSequence);
        parallel.getChildren().add(defenderSequence);

        moveList.add(move1);
        moveList.add(move2);
        moveList.add(move3);
        moveList.add(move4);
        moveList.add(move5);

        parallelMove.getChildren().add(moveSequence);

    }

    private void emptyRegister(){

        for (FractionGameData player:fightingPlayers){

            if (Players.getHumanPlayers().contains(player)) {
                for (Arena arena : player.getArenasList()) {
                    for (Unit unit : player.getPlayersArmy()) {
                        if (unit.getType().equals(arena.getType()) && unit.getAmount()>0) {
                            int x = unit.getActualX();
                            int y = unit.getActualY();
                            arena.setIsUnitHere(x, y, false);
                            unit.setActualPosition(unit.getInitialX(), unit.getInitialY());
                            arena.setIsUnitHere(unit.getActualX(), unit.getActualY(), true);
                        } else if (unit.getAmount()<=0){
                            unit.removeUnit();
                        }
                        unit.zeroLostUnits();
                    }
                }
            }
        }

        if (Players.getComputerPlayers().contains(defendingPlayer)){
            for (Arena arena:arenasList) {
                for (Unit unit : defendersArmy) {
                    for (int i = 1; i <= Arena.height; i++) {
                        for (int j = 1; j <= Arena.width; j++) {
                            if (unit.getActualX() == i && unit.getActualY() == j && unit.getType().equals(arena.getType())) {
                                arena.setIsUnitHere(i, j, false);
                                unit.setActualPosition(unit.getInitialX(), unit.getInitialY());
                                arena.setIsUnitHere(unit.getActualX(), unit.getActualY(), true);
                            }
                        }
                    }
                    unit.zeroLostUnits();
                }
            }
        }


    }

}