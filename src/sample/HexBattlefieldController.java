package sample;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HexBattlefieldController {
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
    private boolean wasAttackRanged  = false;
    private String action;
    private int actualAnimationDuration;
    private int[] indexArray;
    private ArrayList<Unit> attackersArmy = Players.getActualPlayer().getPlayersArmy();
    private ArrayList<Unit> defendersArmy = MapController.attackedSector.getArmy();
    private ArrayList<Unit> actualArmyTurn;
    private ArrayList<ArrayList<Unit>> armiesList = new ArrayList<>();
    private ArrayList<FractionGameData> fightingPlayers = new ArrayList<>();
    private ArrayList<ImageView> initiativeLabels = new ArrayList<>();
    private FractionGameData attackingPlayer = Players.getActualPlayer();
    private FractionGameData defendingPlayer = MapController.attackedSector.getOwner();
    private ArrayList<ImageView> attackersIcons = new ArrayList<>();
    private ArrayList<ImageView> defendersIcons = new ArrayList<>();
    private ScaleTransition scale1 = new ScaleTransition();
    private ScaleTransition scale2 = new ScaleTransition();
    private ScaleTransition scale3 = new ScaleTransition();
    private int scaleCycle=1;
    private int rotationCycle=1;
    private int fadeCycle=1;
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
    private StringBuilder actualUnitMovementPath = new StringBuilder();
    private FadeTransition fade1 = new FadeTransition();
    private FadeTransition fade2 = new FadeTransition();
    private FadeTransition fade3 = new FadeTransition();

    @FXML
    public GridPane winnerArmyBox,looserArmyBox;

    private Field actualUnitField;
    private SVGPath initiativeTurn = new SVGPath();
    private String dx = "none";
    private String dy = "none";
    private boolean moveThenAttack = false;
    private boolean gameEnded =false;
    private int fieldsOnWay=0;
    private ArrayList<Field> shownFields = new ArrayList<>();

    private ImageView hexAirBack = new ImageView();
    private ImageView hexLandBack = new ImageView();
    private ImageView hexWaterBack = new ImageView();

    private ScaleTransition hexBackScale = new ScaleTransition();

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
        addUnitImagesMouseEvents();
        addTilesMouseEvents();
        fillContainers();
        initialScaling();
        hideUnwantedTiles();
        onFront();
        actualUnitMovementPath.append(" ");
//        actualUnitTurn.getImage().setImage(new Image(GraphicsStorage.gundamBase));
        hexAirBack.toBack();
        hexLandBack.toBack();
        hexWaterBack.toBack();


        addHexCordsToFields();
        displayHexCords();

        showActualUnitMovementRange();
    }

    private void onFront() {

        for (Arena arena:arenasList){
            for (int i=1;i<=Arena.height;i++){
                for (int j=1; j<=Arena.width;j++){
                    arena.getField(i,j).getTile().setFill(Color.TRANSPARENT);
                    arena.getField(i,j).getTile().toFront();
                    if (arena.getField(i,j).isUnitHere()){
                        arena.getField(i,j).getUnit().getImage().toFront();
                    }
                }
            }
        }

        flee.toFront();
        settings.toFront();
        skip.toFront();
        autoFight.toFront();

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

    private void showActionButtons(boolean trueOrFalse){
        skip.setVisible(trueOrFalse);
        autoFight.setVisible(trueOrFalse);
        flee.setVisible(trueOrFalse);
        settings.setVisible(trueOrFalse);
        skipBack.setVisible(trueOrFalse);
        autoFightBack.setVisible(trueOrFalse);
        fleeBack.setVisible(trueOrFalse);
        settingsBack.setVisible(trueOrFalse);
        if (trueOrFalse){
            skip.toFront();
            autoFight.toFront();
            flee.toFront();
            settings.toFront();
        }
    }

    private void openSettings() {
//        moveUnitAnimation(actualUnitArena.getField(1,2));
//        System.out.println(dx + ","+dy);
        isAnimationOn=true;
        rotate();
        scale();
        fade();
    }

    private void getFieldsInWay(Field target){
        ArrayList<Field> frontier = new ArrayList<>();
        ArrayList<Field> reached = new ArrayList<>();
        reached.add(actualUnitField);
        frontier.addAll(getFieldsAround(actualUnitField));
        Field current;
        ArrayList<Map> cameFrom = new ArrayList<>();
        Map map = new HashMap();
        map.putIfAbsent(actualUnitField,null);
        for (Field field:frontier){
            map.putIfAbsent(field,actualUnitField);
        }


        while (!frontier.isEmpty()){
            current=frontier.get(0);
            frontier.remove(current);
            if (current==target && !current.isUnitHere()){
                break;
            }
            if (current.getTile()!=null && current.getX()!=-1 && !current.isUnitHere()){
                for (Field field:getFieldsAround(current)){
                    if (!reached.contains(field) && field.getTile()!=null && field.getX()!=-1 && !field.isUnitHere()){
                        frontier.add(field);
                        reached.add(field);
                        map.putIfAbsent(field,current);
                    }
                }
            }
        }

        current = target;
        ArrayList<Field> path=  new ArrayList<>();
        while (current!=actualUnitField){
            path.add(current);
            current=(Field)map.get(current);
        }

        ArrayList<Field> reversePath = new ArrayList<>();
        for (int i=1;i<=path.size();i++){
            reversePath.add(path.get(path.size()-i));
        }

        StringBuilder pathString = new StringBuilder();

        saveX = (int)actualUnitTurn.getImage().getTranslateX() + wh;
        saveY = (int)actualUnitTurn.getImage().getTranslateY()+wh;
        pathString.append("m " + saveX + " " + saveY);
        Field temp = actualUnitField;

        for (Field field:reversePath){
            pathString.append(getSingleMoveFromOrientation(temp,field));
            temp=field;
        }
        unitPath=pathString.toString();
    }

    private ArrayList<Field> getFieldsInDistance(Field source){
        ArrayList<Field> fieldsInDistance= new ArrayList<>();
        for (int i=1;i<=Arena.height;i++){
            for (int j=1;j<=Arena.width;j++){
                if (distance(source,actualUnitArena.getField(i,j))<=actualUnitTurn.getMovementRange()){
                    fieldsInDistance.add(actualUnitArena.getField(i,j));
                }
            }
        }
        return fieldsInDistance;
    }

    private int distance(Field source, Field target){
        return (Math.abs(source.getHexX()-target.getHexX()) + Math.abs(source.getHexY()-target.getHexY()) + Math.abs(source.getHexZ()-target.getHexZ()))/2;
    }

    private void addHexCordsToFields(){
        for (Arena arena:arenasList){
            for (int i=1;i<=Arena.height;i++){
                if (i==1){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-1,1-j-1,i-1);
                    }
                }else if (i==2){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-1,1-j-2,i-1);
                    }
                } else if  (i==3){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-2,1-j-2,i-1);
                    }
                } else if  (i==4){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-2,1-j-3,i-1);
                    }
                } else if  (i==5){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-3,1-j-3,i-1);
                    }
                } else if  (i==6){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-3,1-j-4,i-1);
                    }
                } else if  (i==7){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-4,1-j-4,i-1);
                    }
                } else if  (i==8){
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-4,1-j-5,i-1);
                    }
                } else {
                    for (int j=1;j<=Arena.width;j++){
                        arena.getField(i,j).setHexCords(j+1-5,1-j-5,i-1);
                    }
                }
            }
        }
    }

    private void displayHexCords(){
        for (Arena arena:arenasList) {
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    Field field = arena.getField(i,j);
                    if (field.isUnitHere()){
                        field.getLabelBack().setVisible(true);
                        field.getLabel().setText("   " +field.getUnit().getAmount());
                        field.getLabel().setVisible(true);
                    }
                    AnchorPane.setTopAnchor(field.getLabelBack(),AnchorPane.getTopAnchor(field.getTile())+45);
                    AnchorPane.setLeftAnchor(field.getLabelBack(),AnchorPane.getLeftAnchor(field.getTile())+40);
                    AnchorPane.setTopAnchor(field.getLabel(),AnchorPane.getTopAnchor(field.getTile())+45);
                    AnchorPane.setLeftAnchor(field.getLabel(),AnchorPane.getLeftAnchor(field.getTile())+40);
                    field.getLabel().setStyle(field.getLabel().getStyle()+"-fx-text-fill: red;");
                    field.getLabelBack().toFront();
                    field.getLabel().toFront();
                }
            }
        }
    }

    private String getSingleMoveFromOrientation(Field source,Field target){
        if (getFieldsAround(source).contains(target)){
            switch (getFieldsAround(source).indexOf(target)){
                case 0:
                    return " l " + AnimationControls.hexOneRightX + " " + AnimationControls.hexOneRightY;
                case 1:
                    return " l " + AnimationControls.hexOneLeftX + " " + AnimationControls.hexOneLeftY;
                case 2:
                    return " l " + AnimationControls.hexOneDownRightX + " " + AnimationControls.hexOneDownRightY;
                case 3:
                    return " l " + AnimationControls.hexOneDownLeftX + " " + AnimationControls.hexOneDownLeftY;
                case 4:
                    return " l " + AnimationControls.hexOneUpRightX + " " + AnimationControls.hexOneUpRightY;
                case 5:
                    return " l " + AnimationControls.hexOneUpLeftX + " " + AnimationControls.hexOneUpLeftY;
            }
        }
        return " cant create single movement path";
    }

    private ArrayList<Field> getFieldsAround(Field target) {
        ArrayList<Field> fieldsAround = new ArrayList<>();
        for (int i=0;i<6;i++){
            fieldsAround.add(new Field(-1,-1,null));
        }
        if (target.getRow().equals("odd")){
            if (target.getY()+1<=Arena.width){
                fieldsAround.add(0,actualUnitArena.getField(target.getX(),target.getY()+1));
            }
            if (target.getY()-1>0){
                fieldsAround.add(1,actualUnitArena.getField(target.getX(),target.getY()-1));
            }
            if (target.getX()+1<=Arena.height){
                fieldsAround.add(2,actualUnitArena.getField(target.getX()+1,target.getY()));
            }
            if (target.getX()+1<=Arena.height && target.getY()-1>0){
                fieldsAround.add(3,actualUnitArena.getField(target.getX()+1,target.getY()-1));
            }
            if (target.getX()-1>0){
                fieldsAround.add(4,actualUnitArena.getField(target.getX()-1,target.getY()));
            }
            if (target.getX()-1>0 && target.getY()-1>0){
                fieldsAround.add(5,actualUnitArena.getField(target.getX()-1,target.getY()-1));
            }
        } else if (target.getRow().equals("even")){
            if (target.getY()+1<=Arena.width){
                fieldsAround.add(0,actualUnitArena.getField(target.getX(),target.getY()+1));
            }
            if (target.getY()-1>0){
                fieldsAround.add(1,actualUnitArena.getField(target.getX(),target.getY()-1));
            }
            if (target.getX()+1<=Arena.height && target.getY()+1<=Arena.width){
                fieldsAround.add(2,actualUnitArena.getField(target.getX()+1,target.getY()+1));
            }
            if (target.getX()+1<=Arena.height){
                fieldsAround.add(3,actualUnitArena.getField(target.getX()+1,target.getY()));
            }
            if (target.getX()-1>0 && target.getY()+1<=Arena.width){
                fieldsAround.add(4,actualUnitArena.getField(target.getX()-1,target.getY()+1));
            }
            if (target.getX()-1>0 ){
                fieldsAround.add(5,actualUnitArena.getField(target.getX()-1,target.getY()));
            }
        }
        return fieldsAround;
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
            ArrayList<Field> fieldsInDistance = getFieldsInDistance(actualUnitField);
            if (actualPlayerTurn.equals(attackingPlayer)){
                avatar2.setStyle(avatar2.getStyle()+GraphicsStorage.nullShadow);
                avatar1.setStyle(avatar1.getStyle()+GraphicsStorage.avatarShadowEffect);
            } else if (actualPlayerTurn.equals(defendingPlayer)){
                avatar1.setStyle(avatar1.getStyle()+GraphicsStorage.nullShadow);
                avatar2.setStyle(avatar2.getStyle()+GraphicsStorage.avatarShadowEffect);
            }
            for (Field field:fieldsInDistance){
                field.getTile().setFill(ColorsStorage.combatRange);

            }

            actualUnitField.getUnit().getImage().setStyle(actualUnitField.getUnit().getImage().getStyle()+GraphicsStorage.unitShadow);
            showUnitATKRange();
        }
        else {
            computerMove();
        }
    }

    private void attackLeftToRightAnimation(Field target){

        if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){

        } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)) {
            actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            target.getUnit().getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

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
                if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT) && defendersArmy.contains(actualUnitTurn)){
                    actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    target.getUnit().getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)) {

                }
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackRightToLeftAnimation(Field target){
        parallel.setCycleCount(1);

        if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){

        } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){
            actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            target.getUnit().getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }

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
                if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)&& attackersArmy.contains(actualUnitTurn)){
                    actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    target.getUnit().getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){

                }
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackToLeftUpAnimation(Field target){
        parallel.setCycleCount(1);

        if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){

        } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){
            actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            target.getUnit().getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByX(-40);
        attackerPhase1.setByY(-60);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByX(40);
        attackerPhase2.setByY(60);
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
                if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT) && defendersArmy.contains(actualUnitTurn)){
                    actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    target.getUnit().getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){

                }
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackToLeftDownAnimation(Field target){
        parallel.setCycleCount(1);

        if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){

        } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){
            actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            target.getUnit().getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByX(-40);
        attackerPhase1.setByY(60);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByX(40);
        attackerPhase2.setByY(-60);
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
                if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)&& defendersArmy.contains(actualUnitTurn)){
                    actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    target.getUnit().getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){

                }
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackToRightDownAnimation(Field target){
        parallel.setCycleCount(1);

        if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){
            actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            target.getUnit().getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){

        }

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByX(40);
        attackerPhase1.setByY(60);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByX(-40);
        attackerPhase2.setByY(-60);
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
                if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){

                } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT) && attackersArmy.contains(actualUnitTurn)){
                    actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    target.getUnit().getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                }
                isAnimationOn=false;
                humanPlayerAttack(target);
            }
        });
    }

    private void attackToRightUpAnimation(Field target){
        parallel.setCycleCount(1);

        if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){
            actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            target.getUnit().getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT)){

        }

        attackerSequence.setNode(actualUnitTurn.getImage());
        attackerSequence.setCycleCount(1);

        defenderSequence.setNode(actualUnitTurn.getImage());
        defenderSequence.setCycleCount(1);

        attackerPhase1.setByX(40);
        attackerPhase1.setByY(-60);
        attackerPhase1.setDuration(Duration.seconds(GlobalSettings.attackSpeed));
        attackerPhase1.setNode(actualUnitTurn.getImage());
        attackerPhase1.setCycleCount(1);

        attackerPhase2.setByX(-40);
        attackerPhase2.setByY(60);
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
                if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.RIGHT_TO_LEFT)){

                } else if (actualUnitTurn.getImage().getNodeOrientation().equals(NodeOrientation.LEFT_TO_RIGHT) && attackersArmy.contains(actualUnitTurn)){
                    actualUnitTurn.getImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    target.getUnit().getImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                }
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
        actualUnitField.getLabel().setText("  " + actualUnitField.getUnit().getAmount());
        actualUnitTurn.getImage().toFront();
        actualUnitField.getLabelBack().toFront();
        actualUnitField.getLabel().toFront();

    }

    private void moveUnit( Field target) {
        actualUnitField.getLabelBack().setVisible(false);
        actualUnitField.getLabel().setVisible(false);
        isAnimationOn=true;
        getFieldsInWay(target);
        PathTransition pt = AnimationControls.moveOne(actualUnitTurn,unitPath);
//           actualUnitTurn.getImage().setImage(new Image(GraphicsStorage.gundamMove));
//           pt.setDuration(Duration.seconds(3));
        pt.play();
        pt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                   actualUnitTurn.getImage().setImage(new Image(GraphicsStorage.gundamBase));
                commonMoveUnitMethod(target);
                nextUnitTurn();
            }
        });
        isAnimationOn=false;
    }

    private void moveUnit( Field freeTile,Field enemyUnit) {
        actualUnitField.getLabelBack().setVisible(false);
        actualUnitField.getLabel().setVisible(false);
        Field enemy = enemyUnit;
        isAnimationOn=true;
        getFieldsInWay(freeTile);
        PathTransition pt = AnimationControls.moveOne(actualUnitTurn,unitPath);
//           actualUnitTurn.getImage().setImage(new Image(GraphicsStorage.gundamMove));
//           pt.setDuration(Duration.seconds(3));
        pt.play();
        pt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                   actualUnitTurn.getImage().setImage(new Image(GraphicsStorage.gundamBase));
                commonMoveUnitMethod(freeTile);
                attackAnimationInCorrectDirection(enemy);
            }
        });
    }

    private void attackAnimationInCorrectDirection(Field target) {
        if (getOrientation(target).equals("toRight")){
            attackLeftToRightAnimation(target);
        } else if (getOrientation(target).equals("toLeft")){
            attackRightToLeftAnimation(target);
        } else if (getOrientation(target).equals("toRightDown")){
            attackToRightDownAnimation(target);
        } else if (getOrientation(target).equals("toLeftDown")){
            attackToLeftDownAnimation(target);
        } else if (getOrientation(target).equals("toRightUp")){
            attackToRightUpAnimation(target);
        } else if (getOrientation(target).equals("toLeftUp")){
            attackToLeftUpAnimation(target);
        }
    }

    private String getOrientation(Field target){
        int dx = target.getHexX()-actualUnitField.getHexX();
        int dy = target.getHexY()-actualUnitField.getHexY();
        int dz = target.getHexZ()-actualUnitField.getHexZ();


        if (dz==0 && dx==1 && dy==-1){
                return "toRight";
            } else if (dz==0 && dx==-1 && dy==1){
                return "toLeft";
            } else if (dz==1 && dx==0 && dy==-1){
                return "toRightDown";
            } else if (dz==1 && dx==-1 && dy==0){
                return "toLeftDown";
            } else if (dz==-1 && dx==1 && dy==0){
                return "toRightUp";
            } else if (dz==-1 && dx==0 && dy==1){
                return "toLeftUp";
            } else {
            System.out.println("wrong orientation");
        }



        return "none";
    }

    private String fieldPositions(Field source, Field target) {
        int dx = target.getHexX() - source.getHexX();
        int dy = target.getHexY() - source.getHexY();
        int dz = target.getHexZ() - source.getHexZ();

        if (dz == 0 && dx == -dy && dx > 0) {
            return "toRightUp";
        } else if (dz == 0 && dx == -dy && dx < 0) {
            return "toLeftDown";
        } else if (dx == 0 && dz == -dy && dz < 0) {
            return "toLeftUp";
        } else if (dx == 0 && dz == -dy && dz > 0) {
            return "toRightDown";
        } else if (dy == 0 && dz == -dx && dz > 0) {
            return "toLeft";
        } else if (dy == 0 && dz == -dx && dz < 0) {
            return "toRight";
        }
        return "wrong fieldPositions";
    }

    private Field returnBestUnoccupiedField(Field target){
        Field check = new Field(0,0,null);
        if (target.getY()-1>=1
                && !actualUnitArena.getField(target.getX(),target.getY()-1).isUnitHere()
                && fieldPositions(actualUnitField,target).equals("toLeft")){
            return actualUnitArena.getField(target.getX(),target.getY()-1);
        } else if (target.getY()+1<=Arena.width
                && !actualUnitArena.getField(target.getX(),target.getY()+1).isUnitHere()
                && fieldPositions(actualUnitField,target).equals("toRight")) {
            return actualUnitArena.getField(target.getX(),target.getY()+1);
        } else if (target.getHexX()-1>=0
                && target.getX()-1>=1
                && target.getHexZ()+1<=Arena.height
                && !actualUnitArena.getField(target.getX()-1,target.getY()).isUnitHere()
                && fieldPositions(actualUnitField,target).equals("toRightUp")) {
            return actualUnitArena.getField(target.getHexX()-1,target.getHexY(),target.getHexZ()+1);
        } else if (target.getHexY()+1<=Arena.width
                && target.getX()+1<=Arena.height
                && target.getHexZ()-1>=0
                && !actualUnitArena.getField(target.getX()+1,target.getY()).isUnitHere()
                && fieldPositions(actualUnitField,target).equals("toRightDown")) {
            return actualUnitArena.getField(target.getHexX(),target.getHexY()+1,target.getHexZ()-1);
        } else if (target.getHexY()-1>=0
                && target.getX()-1>=1
                && target.getHexZ()+1<=Arena.height
                && !actualUnitArena.getField(target.getX()-1,target.getY()).isUnitHere()
                && fieldPositions(actualUnitField,target).equals("toLeftUp")) {
            return actualUnitArena.getField(target.getHexX(),target.getHexY()-1,target.getHexZ()+1);
        } else if (target.getHexX()+1<=Arena.width
                && target.getHexZ()-1>=0
                && target.getX()+1<=Arena.height
                && !actualUnitArena.getField(target.getX()+1,target.getY()).isUnitHere()
                && fieldPositions(actualUnitField,target).equals("toLeftDown")) {
            return actualUnitArena.getField(target.getHexX()+1,target.getHexY(),target.getHexZ()-1);
        }
        for (int x= target.getX()-1;x<= target.getX()+1;x++){
            for (int y=target.getY()-1;y<=target.getY();y++){
                if (x>=1 && x<=Arena.height && y>=1  && y<=Arena.width && actualUnitArena.getField(x,y)!=null && !actualUnitArena.getField(x,y).equals(target)
                        && !actualUnitArena.getField(x,y).isUnitHere()){
//                    System.out.println(actualUnitArena.getField(x,y).getCords());
                    return actualUnitArena.getField(x,y);
                }
            }
        }
        System.out.println("Returning empty field");
        return check;


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
        for (Field field:getFieldsInDistance(actualUnitField)) {
            if (field.isUnitHere()
                    && !actualPlayerTurn.getPlayersArmy().contains(field.getUnit())
                    && CombatSystem.isFieldInUnitRange(actualUnitTurn, field)) {
                unitsInRange.add(field.getUnit());
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
        Sector sector = MapController.attackedSector;
        sector.setOwner(winner);
        sector.getMapButton().setFill(winner.getFraction().getFractionColor());
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
        hexLandBack.setVisible(trueOrFalse);
        hexAirBack.setVisible(trueOrFalse);
        hexWaterBack.setVisible(trueOrFalse);
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
            Parent root = FXMLLoader.load(getClass().getResource("mapPanel.fxml"));
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

        avatarBack1.setContent(ShapeStorage.hexAvatarBack);
        avatarBack2.setContent(ShapeStorage.hexAvatarBack);

        battlefieldPanel.getChildren().addAll(avatarBack1);
        battlefieldPanel.getChildren().addAll(avatarBack2);
        battlefieldPanel.getChildren().addAll(avatar1);
        battlefieldPanel.getChildren().addAll(avatar2);

        AnchorPane.setLeftAnchor(avatarBack1,50.0);
        AnchorPane.setLeftAnchor(avatar1,42.0);
        AnchorPane.setRightAnchor(avatarBack2,50.0);
        AnchorPane.setRightAnchor(avatar2,42.0);

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

        AnchorPane.setBottomAnchor(initiativeLabels.get(0),20.0);
        AnchorPane.setBottomAnchor(initiativeLabels.get(1),20.0);
        AnchorPane.setBottomAnchor(initiativeLabels.get(2),20.0);
        AnchorPane.setBottomAnchor(initiativeBackList.get(0),55.0);
        AnchorPane.setBottomAnchor(initiativeBackList.get(1),55.0);
        AnchorPane.setBottomAnchor(initiativeBackList.get(2),55.0);
        AnchorPane.setBottomAnchor(initiativeTurn,25.0);
        AnchorPane.setBottomAnchor(initiationText,25.0);
        AnchorPane.setBottomAnchor(initBack,20.0);

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
                                && getFieldsInDistance(actualUnitField).contains(field)
                                && arena.getType().equals(actualUnitTurn.getType())
                                && !isAnimationOn) {
                            action = "move";
                            moveUnit(field);
                            System.out.println(unitPath);
                        }
                    });
                    background.setOnMouseEntered((MouseEvent e) ->{
                        if (!field.isUnitHere()
                                && !isAnimationOn
                                && getFieldsInDistance(actualUnitField).contains(field)){
                            field.getTile().setFill(ColorsStorage.select);
                            field.getLabel().setText(distance(actualUnitField,field)+"");
                            field.getLabel().toFront();
                        }
                    });
                    background.setOnMouseExited((MouseEvent e) ->{
                        if(!field.isUnitHere()
                                && getFieldsInDistance(actualUnitField).contains(field)
                                && actualUnitTurn.getType().equals(arena.getType())
                                && !isAnimationOn){
                            field.getTile().setFill(ColorsStorage.combatRange);
                        } else if (!field.isUnitHere()
                                && !isAnimationOn) {
                            field.getTile().setFill(Color.TRANSPARENT);
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
                                unitIcon.setStyle(unitIcon.getStyle() + GraphicsStorage.imageViewSmall);
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
//                    arena.getField(unit.getActualX(),unit.getActualY()).getLabel().setVisible(true);
//                    arena.getField(unit.getActualX(),unit.getActualY()).getLabelBack().setVisible(true);
                    unit.getImage().setTranslateY(-30);
//                    unit.getImage().setTranslateX(10);

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
                    unit.getImage().setTranslateY(-30);
                    unit.getImage().setTranslateX(10);
//                    arena.getField(unit.getActualX(),unit.getActualY()).getLabel().setVisible(true);
//                    arena.getField(unit.getActualX(),unit.getActualY()).getLabelBack().setVisible(true);
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

        waterArenaBack.setVisible(false);
        airArenaBack.setVisible(false);


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
                    path.setContent(ShapeStorage.hexArenaTileSVGPath);
                    switch (arena.getType()){
                        case "Air":
                            airTiles.add(path);
                            break;
                        case "Land":
                            landTiles.add(path);
                            break;
                        case "Water":
                            waterTiles.add(path);
                            break;
                    }
                    path.setFill(Color.TRANSPARENT);
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
                    if (i==1 || i==3 || i==5 || i==7 || i==9){
                        field.setRow("odd");
                    } else {
                        field.setRow("even");
                    }

                    path.setStroke(ColorsStorage.black);
                    path.setStrokeWidth(1);
                    path.toFront();
                    label.setVisible(false);
                    labelBack.setVisible(false);

                    int g = 0;
                    if ((i-1)%2==0){
                        g=0;
                    } else {
                        g=1;
                    }

                    AnchorPane.setTopAnchor(path,  (i-1)*60.0 );
                    AnchorPane.setLeftAnchor(path, g*40 + (j-1)*80.0   );
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

    private void hideUnwantedTiles(){
        for (Arena arena: arenasList){

            arena.getField(1,1).getTile().setVisible(false);

            arena.getField(9,1).getTile().setVisible(false);

            arena.getField(6,11).getTile().setVisible(false);
            arena.getField(4,11).getTile().setVisible(false);
            arena.getField(9,11).getTile().setVisible(false);
            arena.getField(1,11).getTile().setVisible(false);
            arena.getField(8,11).getTile().setVisible(false);
            arena.getField(2,11).getTile().setVisible(false);

            for (int i=1; i<=Arena.height;i++){
                for (int j=1;j<=Arena.width;j++){
                    if (arena.getField(i,j).getTile().isVisible()){
                        shownFields.add(arena.getField(i,j));
                    }
                }
            }
        }
    }

    private void fillContainers(){
        airContainer.toFront();
        landContainer.toFront();
        waterContainer.toFront();
//        waterContainer.setVisible(false);
//        airContainer.setVisible(false);
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

    private void fade(){


        switch (fadeCycle){
            case 1:

                fade1.setNode(landArenaBack);
                fade2.setNode(waterArenaBack);

                fade1.play();
                fade2.play();

                fadeCycle=2;
                break;
            case 2:

                fade1.setNode(waterArenaBack);
                fade2.setNode(airArenaBack);

                fade1.play();
                fade2.play();

                fadeCycle=3;
                break;
            case 3:

                fade1.setNode(airArenaBack);
                fade2.setNode(landArenaBack);

                fade1.play();
                fade2.play();

                fadeCycle=1;
                break;
        }
    }

    private void scale(){


        switch (scaleCycle){
            case 1:
                scale1.setByX(-0.6);
                scale1.setByY(-0.6);
                scale1.play();

                scale3.setByX(0.6);
                scale3.setByY(0.6);
                scale3.play();

                scaleCycle=2;
                break;
            case 2:
                scale2.setByX(0.6);
                scale2.setByY(0.6);
                scale2.play();

                scale3.setByX(-0.6);
                scale3.setByY(-0.6);
                scale3.play();
                scaleCycle=3;
                break;
            case 3:
                scale1.setByX(0.6);
                scale1.setByY(0.6);
                scale1.play();

                scale2.setByX(-0.6);
                scale2.setByY(-0.6);
                scale2.play();

                scaleCycle=1;
                break;

        }
    }

    // TODO: 29.10.2020 add arrows and opossite direction rotation
    private void rotate(){


        switch (rotationCycle){
            case 1:
                transition1.setNode(landContainer);
                transition2.setNode(airContainer);
                transition3.setNode(waterContainer);

                airContainer.toFront();
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

                landContainer.toFront();
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

                waterContainer.toFront();
                landContainer.toFront();
                transition1.play();
                transition2.play();
                transition3.play();

                rotationCycle=1;
                break;

        }

        showActionButtons(true);

    }

    private void initialScaling(){
        hexAirBack.setImage(new Image(GraphicsStorage.airHexArenaShape));
        hexLandBack.setImage(new Image(GraphicsStorage.landHexArenaShape));
        hexWaterBack.setImage(new Image(GraphicsStorage.waterHexArenaShape));
        hexAirBack.setPickOnBounds(false);
        hexLandBack.setPickOnBounds(false);
        hexWaterBack.setPickOnBounds(false);


        ShapeStorage.scaleNode(hexAirBack,0.24,0.24);
        ShapeStorage.scaleNode(hexLandBack,0.24,0.24);
        ShapeStorage.scaleNode(hexWaterBack,0.24,0.24);

        airContainer.getChildren().add(hexAirBack);
        landContainer.getChildren().add(hexLandBack);
        waterContainer.getChildren().add(hexWaterBack);


        AnchorPane.setLeftAnchor(hexAirBack, -1392.0);
        AnchorPane.setTopAnchor(hexAirBack,-885.0);
        AnchorPane.setLeftAnchor(hexLandBack, -1392.0);
        AnchorPane.setTopAnchor(hexLandBack,-885.0);
        AnchorPane.setLeftAnchor(hexWaterBack, -1392.0);
        AnchorPane.setTopAnchor(hexWaterBack,-885.0);

//        isAnimationOn=true;
        scale1.setDuration(Duration.seconds(2));
        scale1.setNode(landContainer);
        scale1.setByX(0.8);
        scale1.setByY(0.8);
        landContainer.toFront();
//        scale1.play();
        ShapeStorage.scaleNode(airContainer,0.4,0.4);
        ShapeStorage.scaleNode(waterContainer,0.4,0.4);

        scale2.setDuration(Duration.seconds(2));
        scale2.setByX(-0.6);
        scale2.setByY(-0.6);
        scale2.setNode(airContainer);
//        scale2.play();
        scale3.setDuration(Duration.seconds(2));
        scale3.setByX(-0.6);
        scale3.setByY(-0.6);
        scale3.setNode(waterContainer);
//        scale3.play();
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
        transition1.setByX(-1120);
        transition1.setByY(-400);
        transition1.setNode(landContainer);

        transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(2));
        transition2.setByX(50);
        transition2.setByY(280);
        transition2.setNode(airContainer);

        transition3 = new TranslateTransition();
        transition3.setDuration(Duration.seconds(2));
        transition3.setByX(1070);
        transition3.setByY(120);
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

        fade1.setDuration(Duration.seconds(2));
        fade2.setDuration(Duration.seconds(2));

        fade1.setToValue(0);
        fade2.setToValue(1);

        fade1.setCycleCount(1);
        fade2.setCycleCount(1);

        fade3.setNode(airArenaBack);
        fade3.setCycleCount(1);
        fade3.setDuration(Duration.seconds(2));
        fade3.setToValue(0);
        fade1.setNode(waterArenaBack);

        fade1.play();
        fade3.play();
        fade1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                waterArenaBack.setVisible(true);
            }
        });

        fade3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                airArenaBack.setVisible(true);
            }
        });

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
