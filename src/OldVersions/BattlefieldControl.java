package OldVersions;

//<editor-fold desc="Imports">

import com.jfoenix.controls.JFXButton;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

public class BattlefieldControl {

    //<editor-fold desc="Declarations">
    public JFXButton
            air11, air21, air31, air41, air51, water11, water21, water31, water41, water51, land11, land21, land31, land41, land51,
            air12, air22, air32, air42, air52, water12, water22, water32, water42, water52, land12, land22, land32, land42, land52,
            air13, air23, air33, air43, air53, water13, water23, water33, water43, water53, land13, land23, land33, land43, land53,
            air14, air24, air34, air44, air54, water14, water24, water34, water44, water54, land14, land24, land34, land44, land54,
            air15, air25, air35, air45, air55, water15, water25, water35, water45, water55, land15, land25, land35, land45, land55,
            air16, air26, air36, air46, air56, water16, water26, water36, water46, water56, land16, land26, land36, land46, land56,
            air17, air27, air37, air47, air57, water17, water27, water37, water47, water57, land17, land27, land37, land47, land57,
            air18, air28, air38, air48, air58, water18, water28, water38, water48, water58, land18, land28, land38, land48, land58,
            air19, air29, air39, air49, air59, water19, water29, water39, water49, water59, land19, land29, land39, land49, land59,
            air20, air30, air40, air50, air60, water20, water30, water40, water50, water60, land20, land30, land40, land50, land60;
    public Label
            air11Label, air21Label, air31Label, air41Label, air51Label, water11Label, water21Label, water31Label, water41Label, water51Label, land11Label, land21Label, land31Label, land41Label, land51Label,
            air12Label, air22Label, air32Label, air42Label, air52Label, water12Label, water22Label, water32Label, water42Label, water52Label, land12Label, land22Label, land32Label, land42Label, land52Label,
            air13Label, air23Label, air33Label, air43Label, air53Label, water13Label, water23Label, water33Label, water43Label, water53Label, land13Label, land23Label, land33Label, land43Label, land53Label,
            air14Label, air24Label, air34Label, air44Label, air54Label, water14Label, water24Label, water34Label, water44Label, water54Label, land14Label, land24Label, land34Label, land44Label, land54Label,
            air15Label, air25Label, air35Label, air45Label, air55Label, water15Label, water25Label, water35Label, water45Label, water55Label, land15Label, land25Label, land35Label, land45Label, land55Label,
            air16Label, air26Label, air36Label, air46Label, air56Label, water16Label, water26Label, water36Label, water46Label, water56Label, land16Label, land26Label, land36Label, land46Label, land56Label,
            air17Label, air27Label, air37Label, air47Label, air57Label, water17Label, water27Label, water37Label, water47Label, water57Label, land17Label, land27Label, land37Label, land47Label, land57Label,
            air18Label, air28Label, air38Label, air48Label, air58Label, water18Label, water28Label, water38Label, water48Label, water58Label, land18Label, land28Label, land38Label, land48Label, land58Label,
            air19Label, air29Label, air39Label, air49Label, air59Label, water19Label, water29Label, water39Label, water49Label, water59Label, land19Label, land29Label, land39Label, land49Label, land59Label,
            air20Label, air30Label, air40Label, air50Label, air60Label, water20Label, water30Label, water40Label, water50Label, water60Label, land20Label, land30Label, land40Label, land50Label, land60Label;
    @FXML
    public JFXButton testtest;
    @FXML
    public AnchorPane attackingUnitStatInfoPanel,initiativePanel,victoryPanel,battlefieldPanel,airArenaBack, landArenaBack,
            waterArenaBack,airAnchor, landAnchor, waterAnchor;
    @FXML
    public GridPane airGrid, landGrid, waterGrid,waterRow1, waterRow2, waterRow3, waterRow4, waterRow5, landRow1, landRow2, landRow3, landRow4, landRow5,
            airRow1, airRow2, airRow3, airRow4, airRow5;
    @FXML
    public Label unitAtkStat,unitHpStat,unitRangeStat,unitMovementStat,selectedUnitActualPosition,unitInfo,unitInfoIcon,
            attackersAvatar,defendersAvatar,actualPlayerSignaller,initiativeTurn,victoryGif,winnerLostLabel,looserLostLabel;
    @FXML
    public JFXButton exitButton,initiative1,initiative2,initiative3,left,right,minimizeButton,proceed,maxAir, maxLand, maxWater,
            skipTurnButton,fleeButton,autoFight;
    @FXML
    public GridPane winnerArmyBox,looserArmyBox;

    private boolean isMaximized = false;
    private boolean isUnitSelected = false;
    private boolean wasActionPerformed = false;
    private boolean isComputerMove = true;

    private ArrayList<GridPane> gridsList = new ArrayList<>();
    private ArrayList<AnchorPane> anchorsList = new ArrayList<>();
    private ArrayList<JFXButton> maxButsList = new ArrayList<>();
    private ArrayList<GridPane> airGrids = new ArrayList<>();
    private ArrayList<GridPane> landGrids = new ArrayList<>();
    private ArrayList<GridPane> waterGrids = new ArrayList<>();
    private ArrayList<ArrayList<GridPane>> gridRows = new ArrayList<>();

    private GridPane actualMaximizedGrid;
    private AnchorPane actualMaximizedAnchor;

    private ArrayList<JFXButton> airButs = new ArrayList<>();
    private ArrayList<JFXButton> landButs = new ArrayList<>();
    private ArrayList<JFXButton> waterButs = new ArrayList<>();
    private ArrayList<ArrayList<JFXButton>> elementalButtonsList = new ArrayList<>();
    private ArrayList<Label> airLabels = new ArrayList<>();
    private ArrayList<Label> landLabels = new ArrayList<>();
    private ArrayList<Label> waterLabels = new ArrayList<>();
    private ArrayList<ArrayList<Label>> labelsList = new ArrayList<>();
    private JFXButton[][] airButtons = new JFXButton[Arena.height][Arena.width];
    private JFXButton[][] landButtons = new JFXButton[Arena.height][Arena.width];
    private JFXButton[][] waterButtons = new JFXButton[Arena.height][Arena.width];
    private ArrayList<JFXButton[][]> butsList = new ArrayList<>();
    public Arena airArena = new Arena("Air");
    public Arena landArena = new Arena("Land");
    public Arena waterArena = new Arena("Water");
    private ArrayList<Arena> arenasList = new ArrayList<>();
    private ArrayList<JFXButton> buts = new ArrayList<>();
    private ArrayList<Label> names = new ArrayList<>();
    private ArrayList<JFXButton> initiativeLabels = new ArrayList<>();
    private int[] indexArray;
    private ArrayList<Unit> attackersArmy = Players.getActualPlayer().getPlayersArmy();
    private ArrayList<Unit> defendersArmy = mapPanelController.attackedSector.getArmy();
    private ArrayList<Unit> actualArmyTurn;
    private ArrayList<ArrayList<Unit>> armiesList = new ArrayList<>();
    private FractionGameData attackingPlayer = Players.getActualPlayer();
    private FractionGameData defendingPlayer = mapPanelController.attackedSector.getOwner();
    private ArrayList<FractionGameData> fightingPlayers = new ArrayList<>();
    private String actualShowArenaSize = "small";
    private ArrayList<GridPane> actualMaximizedGridRows;

    private Unit actualUnitTurn;
    private List<Unit> unitsTurnOrder = new ArrayList<>();
    private Field actualUnitField;
    private Field targetedField;
    private int turnCounter=1;
    private JFXButton actualUnitButton;
    private JFXButton targetedButton;
    private Arena actualUnitArena;
    private FractionGameData actualPlayerTurn,looser,winner;
    private Arena actualMaximizedArena;
    private boolean wasAttackRanged  = false;
    private String action;
    private int actualAnimationDuration;
    //</editor-fold>

    public void initialize() {
        fillMissingGraphics();
        Sectors.playCombatMusic();
        fillArenasBackgrounds();
        fillElementalButsAndLabels();
        fillArenas();
        fillLabels();
        fillBaseArenaColor();
        fillRowsArray();
        fillLists();
        airArena.setButtonsToFields(airButtons);
        landArena.setButtonsToFields(landButtons);
        waterArena.setButtonsToFields(waterButtons);
        if (Players.getHumanPlayers().contains(defendingPlayer)) {
            defendersArmy = defendingPlayer.getPlayersArmy();
        } else {
            defendingPlayer.setPlayersArmy(defendersArmy);
        }


        defaultSizeOfButs();
        armiesList.add(attackersArmy);
        armiesList.add(defendersArmy);
        placeAttackersArmy();
        placeDefendersArmy();
        initialFieldsSettings();
        fillInitialFieldsColors();
        actualArmyTurn = attackersArmy;
        addAndPositionLabelsOnTheScreen();

        sortUnitsOrderList();
        showActualUnitMovementRange();

        // TODO: 22.10.2020 tylko do testow, usun jak skonczysz
        int counter=0;
        for (Unit unit:defendersArmy){
            if (unit.getAmount()==0){
                counter++;
            }
            if (counter==8){
                looser=defendingPlayer;
                manageVictory();
            }
        }

        String testS =" M 59.00,21.00 C 59.13,19.15 59.05,17.41 60.29,15.86 62.96,12.52 70.09,12.01 74.00,12.00 81.41,11.99 90.35,11.65 91.00,21.00 91.00,21.00 94.00,21.00 94.00,21.00 94.00,21.00 94.00,30.00 94.00,30.00 90.53,31.90 88.61,34.42 87.00,38.00 87.00,38.00 103.00,42.00 103.00,42.00 103.00,42.00 103.00,45.00 103.00,45.00 113.50,45.73 117.96,58.91 127.00,63.00 128.16,71.52 133.61,82.23 120.00,84.00 120.00,84.00 120.00,87.00 120.00,87.00 120.00,87.00 110.00,87.00 110.00,87.00 110.00,87.00 108.06,105.00 108.06,105.00 108.06,105.00 108.06,119.96 108.06,119.96 108.06,119.96 110.00,129.00 110.00,129.00 113.59,130.02 113.98,130.41 115.00,134.00 125.52,134.73 122.07,142.77 115.71,144.78 115.71,144.78 89.00,144.78 89.00,144.78 89.00,144.78 87.00,138.00 87.00,138.00 87.00,138.00 84.00,138.00 84.00,138.00 82.40,143.73 79.64,144.91 74.00,145.00 70.20,145.05 57.30,145.49 54.51,144.19 52.96,143.47 50.27,141.01 49.05,139.72 45.90,136.37 45.31,135.54 45.00,131.00 45.00,131.00 42.00,131.00 42.00,131.00 42.00,124.52 42.64,101.97 41.27,97.28 39.55,91.38 39.99,88.03 33.00,87.00 33.00,87.00 30.00,84.00 30.00,84.00 30.00,84.00 30.00,70.00 30.00,70.00 30.00,70.00 33.00,70.00 33.00,70.00 33.00,70.00 35.00,61.00 35.00,61.00 39.41,58.95 40.83,56.64 42.00,52.00 42.00,52.00 45.00,52.00 45.00,52.00 45.00,52.00 47.00,42.00 47.00,42.00 47.00,42.00 63.00,38.00 63.00,38.00 61.39,34.42 59.47,31.90 56.00,30.00 56.00,30.00 56.00,21.00 56.00,21.00 56.00,21.00 59.00,21.00 59.00,21.00 Z";
        SVGPath shape = new SVGPath();
        shape.setContent(testS);

        ImageView img = new ImageView(GraphicsStorage.waterTile);
        img.setPickOnBounds(false);

        img.setStyle(ShapeStorage.arenaTile);
        img.setOnMouseClicked((MouseEvent e) -> {
            String ts = testtest.getStyle();
            testtest.setStyle(ts + "-fx-border-color: green;");
        });
        img.setOnMouseEntered((MouseEvent e) -> {
            String actualStyle = img.getStyle();
            img.setStyle(actualStyle + "-fx-effect: dropshadow(one-pass-box, yellow, 10, 0.2, 0, 0);");
        });
        img.setOnMouseExited((MouseEvent e) -> {
            img.setStyle("-fx-shape: \"" + testS + "\";" + "-fx-scale-x: 0.6; -fx-scale-y: 0.6;");
        });
        battlefieldPanel.getChildren().add(img);
        AnchorPane.setTopAnchor(img,500.0);
        AnchorPane.setLeftAnchor(img,100.0);
        img.toFront();


//        testtest.setPickOnBounds(false);
//        testtest.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//        testtest.setOnMouseClicked(this::testMouse);

//        ImageView iv = new ImageView(GraphicsStorage.jngT4armyUnit);
//        iv.setStyle(" -fx-background-size: cover; -fx-background-repeat: stretch; -fx-background-position: left top;");
//        testtest.setGraphic(iv);

        // TODO: 22.10.2020 tylko do testow, usun jak skonczysz

    }

    private void testMouse(MouseEvent mouseEvent) {

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
                        actualUnitButton=actualUnitField.getButton();
                        actualUnitArena=arena;
                    }
                }
            }
        }

        resizeIndexArray();
        manageInitiativeBar();
    }

    private void showUnitATKRange(){
        for (Unit unit:unitsTurnOrder){
            if (CombatSystem.isInATKRange(actualUnitTurn,unit)
                    && !actualPlayerTurn.getPlayersArmy().contains(unit)
                    && unit.getType().equals(actualUnitArena.getType())){
                Field field = actualUnitArena.getField(unit.getActualX(),unit.getActualY());
                String buttonBackground = GraphicsStorage.attackBackground;
                changeButtonBackground(field.getButton(), buttonBackground);
            }
        }
    }

    private void showActualUnitMovementRange() {
        if (Players.getHumanPlayers().contains(actualPlayerTurn)){
//            System.out.println("Show unit movement range");
            Unit unit = actualUnitTurn;
            changeButtonBackground(actualUnitButton,GraphicsStorage.actualBackground);
            Arena arena = actualUnitArena;
            if (isMaximized ) {
                updateUnitInfoPanel();
            }
            if (actualPlayerTurn.equals(attackingPlayer)){
                AnchorPane.setLeftAnchor(actualPlayerSignaller,50.0);
            } else if (actualPlayerTurn.equals(defendingPlayer)){
                AnchorPane.setLeftAnchor(actualPlayerSignaller,Constants.width-200);
            }
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    Field field = arena.getFields()[i - 1][j - 1];
                    if (CombatSystem.isFieldInUnitRange(unit, field)
                            && !field.equals(actualUnitField)
                            && !arena.getIsUnitHere(i, j)) {
                        String buttonBackground = unit.getFractionBackground();
                        changeButtonBackground(field.getButton(), buttonBackground);
                    } else if (CombatSystem.isFieldInUnitRange(unit, field)
                            && !field.equals(actualUnitField)
                            && arena.getIsUnitHere(i, j)
                            && !actualPlayerTurn.getPlayersArmy().contains(field.getUnit())) {
                        String buttonBackground = GraphicsStorage.attackBackground;
                        changeButtonBackground(field.getButton(), buttonBackground);
                    }
                }
            }
            showUnitATKRange();
        }
        else {
            computerMove();
        }
    }

    private void hideActualUnitATKRange(){
        if (actualUnitTurn.getATKRange()>1){
            for (Unit unit:unitsTurnOrder){
                if (CombatSystem.isInATKRange(actualUnitTurn,unit)
                        && !actualPlayerTurn.getPlayersArmy().contains(unit)
                        && unit.getType().equals(actualUnitArena.getType())){
                    Field field = actualUnitArena.getField(unit.getActualX(),unit.getActualY());
                    changeButtonBackground(field.getButton(), unit.getFractionBackground());
                }
            }
            changeButtonBackground(actualUnitButton,actualUnitTurn.getFractionBackground());
        }
    }

    private void humanPlayerAttack(){
        if (CombatSystem.dealDamageIsKilled(actualUnitTurn, targetedField.getUnit())){
            changeButtonStyleAndImage(targetedButton,targetedField.getInitialBackgroundURL(),
                    "null",actualShowArenaSize);
            unitsTurnOrder.remove(targetedField.getUnit());
            targetedField.emptyUnit();
            targetedField.getLabel().setVisible(false);
            resizeIndexArray();
        } else  {
            targetedField.getLabel().setText("    " + targetedField.getUnit().getAmount());
        }
        hideActualUnitMovementRange();
        hideActualUnitATKRange();
        hideActualAndTarget();
        nextUnitTurn();
    }

    private void hideActualAndTarget(){
        if (!wasAttackRanged){
            changeButtonStyleAndImage(actualUnitButton,actualUnitField.getInitialBackgroundURL(),"null",actualShowArenaSize);
            if (targetedField.isUnitHere()){
                changeButtonStyleAndImage(targetedButton,targetedField.getUnit().getFractionBackground(),targetedField.getUnit().getIcon(),actualShowArenaSize);
            } else {
                changeButtonStyleAndImage(targetedButton,targetedField.getInitialBackgroundURL(),"null",actualShowArenaSize);
            }
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
                    changeButtonStyleAndImage(field.getButton(), field.getInitialBackgroundURL(), "null", actualShowArenaSize);
                } else if (CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && !field.equals(arena.getFields()[actualUnitField.getX() - 1][actualUnitField.getY() - 1])//field is not actual unit position
                        && field.isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(field.getUnit())) {             //field  contain unit
                    changeButtonBackground(field.getButton(), field.getUnit().getFractionBackground());
                }
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && k * n == 50) {
                    attackingUnitStatInfoPanel.setVisible(false);
                    changeButtonBackground(actualUnitButton,actualUnitTurn.getFractionBackground());
                }
            }
        }
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

    private void skipComputerPlayerMove(){
        hideActualUnitMovementRange();
        hideActualUnitATKRange();
        nextUnitTurn();
    }

    private void moveHumanUnit(boolean skip){
        Field field;
        Arena arena = actualUnitArena;
        hideActualUnitATKRange();
        for (int k = 1; k <= Arena.height; k++) {
            for (int n = 1; n <= Arena.width; n++) {
                field = actualUnitArena.getField(k,n);
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn, targetedField)
                        && CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && !field.equals(arena.getFields()[actualUnitField.getX() - 1][actualUnitField.getY() - 1])//field is not actual unit position
                        && !field.equals(arena.getFields()[targetedField.getX() - 1][targetedField.getY() - 1])  //field is not target unit position
                        && !field.isUnitHere()) {    //field doesn't contain unit
                    changeButtonStyleAndImage(field.getButton(), field.getInitialBackgroundURL(), "null", actualShowArenaSize);
                } else if (CombatSystem.isFieldInUnitRange(actualUnitTurn, targetedField)
                        && CombatSystem.isFieldInUnitRange(actualUnitTurn, field)  //field is in unit's range
                        && !field.equals(arena.getFields()[actualUnitField.getX() - 1][actualUnitField.getY() - 1])//field is not actual unit position
                        && !field.equals(arena.getFields()[targetedField.getX() - 1][targetedField.getY() - 1])  //field is not target unit position
                        && field.isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(field.getUnit())) {             //field  contain unit
                    changeButtonBackground(field.getButton(), field.getUnit().getFractionBackground());
                }
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn, targetedField)  //field is in unit's range
                        && k * n == 50) {              //is last iteration so we can move unit data
                    attackingUnitStatInfoPanel.setVisible(false);

                    changeButtonStyleAndImage(actualUnitButton, actualUnitField.getInitialBackgroundURL(),
                            "null", actualShowArenaSize); //source button style
                    changeButtonStyleAndImage(targetedButton, actualUnitTurn.getFractionBackground(),
                            actualUnitTurn.getIcon(), actualShowArenaSize); //target button style
                    actualUnitField.getLabel().setVisible(false);
                    targetedField.getLabel().setVisible(true);
                    targetedField.getLabel().setText(actualUnitField.getLabel().getText());

                    actualUnitTurn.setActualPosition(targetedField.getX(), targetedField.getY());
                    targetedField.setUnit(actualUnitTurn);
                    actualUnitField.emptyUnit();
                    Field temp = targetedField;
                    targetedField = null;
                    actualUnitField = temp;

                    if (skip){
                        nextUnitTurn();
                    }
                }
            }
        }
    }

    private void moveUnitToSelectedField(Field field){

        actualUnitField.getLabel().setVisible(false);
        hideActualUnitATKRange();
        hideActualUnitMovementRange();
        changeButtonImage(actualUnitButton,"null", actualShowArenaSize); //source button s
        actualUnitField.restoreButtonInitialStyle();
        changeButtonStyleAndImage(field.getButton(), actualUnitTurn.getFractionBackground(),
                actualUnitTurn.getIcon(), actualShowArenaSize);
        actualUnitTurn.setActualPosition(field.getX(), field.getY());
        field.setUnit(actualUnitTurn);
        actualUnitField.emptyUnit();
        actualUnitField = field;
        actualUnitField.getLabel().setVisible(true);
    }

    private Field returnBestUnoccupiedField(Field field){
        Field check = new Field(0,0,null);
        if (field.getY()-1>=1
                && !actualUnitArena.getField(field.getX(),field.getY()-1).isUnitHere()
                && fieldPositions(actualUnitTurn,targetedField).equals("left")){
            return actualUnitArena.getField(field.getX(),field.getY()-1);
        } else if (field.getY()+1<=10
                && !actualUnitArena.getField(field.getX(),field.getY()+1).isUnitHere()
                && fieldPositions(actualUnitTurn,targetedField).equals("right")) {
            return actualUnitArena.getField(field.getX(),field.getY()+1);
        } else if (field.getX()-1>=1
                && !actualUnitArena.getField(field.getX()-1,field.getY()).isUnitHere()
                && fieldPositions(actualUnitTurn,targetedField).equals("up")) {
            return actualUnitArena.getField(field.getX()-1,field.getY());
        } else if (field.getX()+1<=10
                && !actualUnitArena.getField(field.getX()+1,field.getY()).isUnitHere()
                && fieldPositions(actualUnitTurn,targetedField).equals("down")) {
            return actualUnitArena.getField(field.getX()+1,field.getY());
        }
        for (int x= field.getX()-1;x<= field.getX()+1;x++){
            for (int y=field.getY()-1;y<=field.getY();y++){
                if (x>=1 && x<=5 && y>=1  && y<=10 && actualUnitArena.getField(x,y)!=null && !actualUnitArena.getField(x,y).equals(field)
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
        if (actualUnitTurn.getActualY()- target.getY()<0){
            return "left";
        } else if (actualUnitTurn.getActualY()- target.getY()>0){
            return "right";
        } else if (actualUnitTurn.getActualX()- target.getX()<0){
            return "up";
        } else if (actualUnitTurn.getActualX()- target.getX()>0){
            return "down";
        }
        return "error";
    }

    // TODO: 22.10.2020 create this methods

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

    //first target archers
    //second and third target next unit in initiative bar (second option for next, third if actual unit is last in order)
    @FXML
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
    @FXML
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

    private void showActionButtons(boolean trueOrFalse){
        skipTurnButton.setVisible(trueOrFalse);
        autoFight.setVisible(trueOrFalse);
        fleeButton.setVisible(trueOrFalse);
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

    private StringBuilder createPath(){

        // TODO: 25.10.2020 make it work
        ArrayList<Field> fields = getFieldsOnWay();
        int counter=0;
        StringBuilder path= new StringBuilder();
        for (Field field:fields){
            if (!field.isUnitHere()){
                counter++;
            }
        }
        if (counter==fields.size()){
            for (int i=1; i<=fields.size();i++){
                String temp = " l " + AnimationControls.oneRightX + " " + AnimationControls.oneRightY;
                path.append(temp);
            }
        }
        actualAnimationDuration=fields.size();
        return path;
    }
    
    private ArrayList<Field> getFieldsOnWay(){
        ArrayList<Field> fields = new ArrayList<>();
        
        int ax = actualUnitField.getX();
        int ay = actualUnitField.getY();
        int tx = targetedField.getX();
        int ty = targetedField.getY();
        int dx = tx-ax;
        int dy = ty-ay;

        // TODO: 25.10.2020 make it work, add detector for other fields 

        if (upOrDown(dx).equals("mid") && leftOrRight(dy).equals("right")){
            for (int i=1;i<=dy;i++){
                fields.add(actualUnitArena.getField(ax,ay+i));
            }
        } else if (upOrDown(dx).equals("mid") && leftOrRight(dy).equals("left")){

        } else if (upOrDown(dx).equals("down") && leftOrRight(dy).equals("mid")){

        } else if (upOrDown(dx).equals("up") && leftOrRight(dy).equals("mid")){

        } else if (upOrDown(dx).equals("down") && leftOrRight(dy).equals("left")){

        } else if (upOrDown(dx).equals("down") && leftOrRight(dy).equals("right")){

        } else if (upOrDown(dx).equals("up") && leftOrRight(dy).equals("left")){

        } else if (upOrDown(dx).equals("up") && leftOrRight(dy).equals("right")){

        } 
        
        
        return fields;
    }

    @FXML
    private void moveUnitAnimation(){
        SVGPath buttonPathShape = new SVGPath();
        SVGPath labelPathShape = new SVGPath();
        int duration = actualAnimationDuration;
        
        String unitPath ="m 75 75" + createPath();
        String labelPath ="m 22.5 6.5" + createPath();
        
        // start graphic xy =75   75
        //start label xy = 22.5   6.5
        // one field horizontally x=45
        //one field down y=50  x=20
        //one field up y=-50 x=-20

        buttonPathShape.setContent(unitPath);
        PathTransition transition1 = new PathTransition(Duration.seconds(duration),buttonPathShape ,actualUnitButton.getGraphic());
        labelPathShape.setContent(labelPath);
        PathTransition transition2 = new PathTransition(Duration.seconds(duration),labelPathShape,actualUnitField.getLabel());


        actualUnitButton.getParent().toFront();
        actualUnitButton.toFront();
        actualUnitField.getLabel().toFront();

        transition1.setCycleCount(1);
        transition1.play();
        transition2.setCycleCount(1);
        transition2.play();
    }


    //archers
    //standing nearby
    //move then attack
    //just move
    @FXML
    private void computerMove(){
        isComputerMove=true;
        if (actualUnitTurn.getATKRange()>1
                && !pickBestUnitInATKRange().getName().equals("error404 unit")) {
            Soundtracks.playSound(Soundtracks.archerSound);
            targetedField = actualUnitArena.getField(pickBestUnitInATKRange().getActualX(),pickBestUnitInATKRange().getActualY());
            targetedButton=targetedField.getButton();
            action = "archer:  " + targetedField.getUnit().getName();
            wasAttackRanged=true;
            humanPlayerAttack();
        } else if (actualUnitTurn.getATKRange()==1
                && !pickBestUnitInMovementRange().getName().equals("error404 unit")) {
            targetedField = actualUnitArena.getField(pickBestUnitInMovementRange().getActualX(),pickBestUnitInMovementRange().getActualY());
            targetedButton=targetedField.getButton();
            Soundtracks.playSound(Soundtracks.swordSound);
            if (isActualUnitNearbyTarget()) {
                action = "close";
                humanPlayerAttack();
            } else {
                action = "move then attack";
                Field tempF = targetedField;
                JFXButton tempB = targetedButton;
                targetedField=returnBestUnoccupiedField(targetedField);
                targetedButton=targetedField.getButton();
                moveHumanUnit(false);
                targetedField = tempF;
                targetedButton = tempB;
                humanPlayerAttack();
            }
        } else if (pickBestUnitInMovementRange().getName().equals("error404 unit")) {
            int distance = actualUnitTurn.getMovementRange();
            if (distance+actualUnitTurn.getActualY()>=1 && Players.getComputerPlayers().contains(actualPlayerTurn) || defendingPlayer.getName().equals(actualPlayerTurn.getName()) ) {
                targetedField = actualUnitArena.getField(actualUnitTurn.getActualX(), actualUnitTurn.getActualY()-distance);
                targetedButton = targetedField.getButton();
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn,targetedField)
                        && !targetedField.isUnitHere()) {
                    action = "Cmove";
                } else {
                    targetedField = returnBestUnoccupiedField(targetedField);
                    targetedButton=targetedField.getButton();
                    action = "Cmove2";
                }
                moveHumanUnit(true);
            } else if ((distance+actualUnitTurn.getActualY())<=10 && Players.getHumanPlayers().contains(actualPlayerTurn) || attackingPlayer.getName().equals(actualPlayerTurn.getName())) {
                targetedField = actualUnitArena.getField(actualUnitTurn.getActualX(), actualUnitTurn.getActualY()+distance);
                targetedButton = targetedField.getButton();
                if (CombatSystem.isFieldInUnitRange(actualUnitTurn,targetedField)
                        && !targetedField.isUnitHere()) {
                    action = "Hmove";
                } else {
                    targetedField = returnBestUnoccupiedField(targetedField);
                    targetedButton=targetedField.getButton();
                    action = "Hmove2";
                }
                moveHumanUnit(true);
            } else {
                action = "skipTurn";
                skipTurn();
            }
        } else {
            action = "Think more";
        }
    }

    private void computerAttack(){

    }

    // TODO: 22.10.2020 create this methods

    public void flee(){
        looser=actualPlayerTurn;
        manageVictory();
    }

    public void skipTurn(){
        hideActualUnitATKRange();
        hideActualUnitMovementRange();
        changeButtonBackground(actualUnitButton,actualUnitTurn.getFractionBackground());
        nextUnitTurn();
    }

    private void moveHumanPlayerUnit(ActionEvent e){

        for (int i = 1; i <= Arena.height; i++) {
            for (int j = 1; j <= Arena.width; j++) {
                Arena arena = actualUnitArena;
                targetedField = actualUnitArena.getField(i, j);
                targetedButton = targetedField.getButton();
                if (e.getSource().equals(actualUnitArena.getField(i, j).getButton()) && !targetedField.isUnitHere()
                        && CombatSystem.isFieldInUnitRange(actualUnitTurn, targetedField)) {
                    action = "move";
//                    moveUnitAnimation();
                    moveHumanUnit(true);
                } else if (e.getSource().equals(actualUnitArena.getField(i, j).getButton()) //distance attack, or attack standing next to a unit
                        && targetedField.isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(targetedField.getUnit())
                        && actualUnitArena.getType().equals(targetedField.getUnit().getType())
                        && CombatSystem.isInATKRange(actualUnitTurn, targetedField.getUnit())
                        && actualUnitTurn.getATKRange() > 1) {
                    Soundtracks.playSound(Soundtracks.archerSound);
                    action = "archer: " + targetedField.getUnit().getName();
                    wasAttackRanged=true;
                    humanPlayerAttack();
                } else if (e.getSource().equals(actualUnitArena.getField(i, j).getButton()) //distance attack, or attack standing next to a unit
                        && targetedField.isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(targetedField.getUnit())
                        && actualUnitArena.getType().equals(targetedField.getUnit().getType())
                        && CombatSystem.isFieldInUnitRange(actualUnitTurn, targetedField)
                        && actualUnitTurn.getATKRange() == 1
                        && isActualUnitNearbyTarget()) {
                    Soundtracks.playSound(Soundtracks.swordSound);
                    action = "close combat";
                    humanPlayerAttack();
                } else if (e.getSource().equals(actualUnitArena.getField(i, j).getButton()) //distance attack, or attack standing next to a unit
                        && targetedField.isUnitHere()
                        && !actualPlayerTurn.getPlayersArmy().contains(targetedField.getUnit())
                        && actualUnitArena.getType().equals(targetedField.getUnit().getType())
                        && CombatSystem.isFieldInUnitRange(actualUnitTurn, targetedField)
                        && actualUnitTurn.getATKRange() == 1) {
                    Soundtracks.playSound(Soundtracks.swordSound);
                    action = "move then attack";
                    Field temp = targetedField;
                    targetedField = returnBestUnoccupiedField(targetedField);
                    targetedButton = targetedField.getButton();
                    moveHumanUnit(false);
                    targetedField = temp;
                    targetedButton = temp.getButton();
                    humanPlayerAttack();
                }
            }
        }
    }

    public boolean isActualUnitNearbyTarget(){
        Field field;
        for (int x = targetedField.getX()-1;x< targetedField.getX()+3;x++){
            for (int y= targetedField.getY()-1;y< targetedField.getY()+3;y++){
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

    private void manageInitiativeBar(){
        int index = unitsTurnOrder.indexOf(actualUnitTurn);
        JFXButton label;
        Unit unit;
        ImageView iv;
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
                label.setGraphic(null);
                iv = new ImageView(unit.getIcon());
                iv.setStyle(GraphicsStorage.initiativeImageView);
                label.setStyle(GraphicsStorage.begin + unit.getFractionBackground() + GraphicsStorage.end);
                label.setGraphic(iv);
                label.setWrapText(true);
                if (firstRun) {
                    initiativeTurn.setText("Turn: " + turnCounter + "  Next Unit: " + unit.getName());
                    firstRun = false;
                }
            }
        }
        else if (unitsTurnOrder.size()>2){
            for (int i=startingIndex;i<startingIndex+3;i++){
                unit = unitsTurnOrder.get(indexArray[i+1]);
                label=initiativeLabels.get(counter);
                label.setGraphic(null);
                iv = new ImageView(unit.getIcon());
                iv.setStyle(GraphicsStorage.initiativeImageView);
                label.setStyle(GraphicsStorage.begin+unit.getFractionBackground()+GraphicsStorage.end);
                label.setGraphic(iv);
                label.setWrapText(true);


                if (firstRun){
                    initiativeTurn.setText("Turn: "  + turnCounter  + "  Next Unit: " + unit.getName());
                    firstRun=false;
                }
                counter++;
            }
        }

    }

    private void nextUnitTurn(){
        System.out.println(action);
        if (isComputerMove){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                    actualUnitButton = actualUnitField.getButton();
                }
            }
        }
        showActualUnitMovementRange();
//        System.out.println("Index: " + index);
//        System.out.println("Actual unit: " + actualUnitTurn.getName() + " initiative: " + actualUnitTurn.getInitiative()
//        + "  Amount: " + actualUnitTurn.getAmount() + "  Type: "  + actualUnitTurn.getType());

    }

    private void manageVictory(){
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

    private void showVictoryPanel(String image){
        try {
            Thread.sleep(300);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        if (isMaximized){
            minimize();
        }
        hideGrids();
        hideAnchorPanes();
        hideMinMaxBut();
        hideAdditionalPanels();
        hideRows();
        showActionButtons(false);
        victoryPanel.setVisible(true);
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
        winnerLostLabel.setText(winner.getName() + " lost: ");
        looserLostLabel.setText(looser.getName() + " lost: ");

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
                    but2.setText("   " + unit.getLostUnits());
                    but2.setTextAlignment(TextAlignment.CENTER);

                }
            }
            lostCounter=0;
        }

    }

    // TODO: 21.10.2020 make window for minimized unit stats
    private void updateUnitInfoPanel(){
        if (actualMaximizedArena.getType().equals(actualUnitTurn.getType())){
            attackingUnitStatInfoPanel.setVisible(true);
        }

        unitAtkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitHpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitRangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitMovementStat.setGraphic(new ImageView(GraphicsStorage.movement));

        unitAtkStat.setText(actualUnitTurn.getATK()+"");
        unitHpStat.setText(actualUnitTurn.getHP()+"");
        unitRangeStat.setText(actualUnitTurn.getATKRange()+"");
        unitMovementStat.setText(actualUnitTurn.getMovementRange()+"");
//        selectedUnitActualPosition.setText("(" +selectedUnit.getActualX() + "," +  selectedUnit.getActualY() + ")");
        unitInfo.setText(actualUnitTurn.getName());
        unitInfoIcon.setGraphic(new ImageView(actualUnitTurn.getIcon()));

    }

    private void fillMissingGraphics() {
        exitButton.setStyle(GraphicsStorage.begin + GraphicsStorage.mimiButton + GraphicsStorage.end);
        maxAir.setStyle(GraphicsStorage.begin + GraphicsStorage.maximize + GraphicsStorage.end);
        maxLand.setStyle(GraphicsStorage.begin + GraphicsStorage.maximize + GraphicsStorage.end);
        maxWater.setStyle(GraphicsStorage.begin + GraphicsStorage.maximize + GraphicsStorage.end);
        minimizeButton.setStyle(GraphicsStorage.begin + GraphicsStorage.minimize + GraphicsStorage.end);
        attackersAvatar.setStyle(GraphicsStorage.begin + GraphicsStorage.settingsBackground + GraphicsStorage.end);
        defendersAvatar.setStyle(GraphicsStorage.begin + GraphicsStorage.settingsBackground + GraphicsStorage.end);
        attackersAvatar.setGraphic(new ImageView(attackingPlayer.getAvatar()));
        defendersAvatar.setGraphic(new ImageView(defendingPlayer.getAvatar()));
        initiativePanel.setStyle(GraphicsStorage.begin + GraphicsStorage.settingsBackground + GraphicsStorage.end);
        initiativeTurn.setStyle("-fx-background-color: orange;");
        ImageView iv = new ImageView(GraphicsStorage.time);
        skipTurnButton.setStyle(GraphicsStorage.vicGifStyle+"-fx-background-color: #3b1d0d;"+GraphicsStorage.rectButt+
                "-fx-border-width: 3pt; -fx-border-color: black;");
        skipTurnButton.setGraphic(iv);
        iv.setStyle("-fx-scale-x: 1.5; -fx-scale-y: 1.5;");
        iv= new ImageView(GraphicsStorage.flee);
        fleeButton.setGraphic(iv);
        fleeButton.setStyle(GraphicsStorage.vicGifStyle+"-fx-background-color: #3b1d0d;"+GraphicsStorage.rectButt+
                "-fx-border-width: 3pt; -fx-border-color: black;");
        iv = new ImageView(GraphicsStorage.autoFight);
        autoFight.setStyle(GraphicsStorage.vicGifStyle+"-fx-background-color: #3b1d0d;"+GraphicsStorage.rectButt+
                "-fx-border-width: 3pt; -fx-border-color: black;");
        autoFight.setGraphic(iv);

    }

    public void moveOrAttack(ActionEvent e) {}

    private void addAndPositionLabelsOnTheScreen() {
        for (int k = 0; k < 3; k++) {
            Field field;
            Label label;
            JFXButton but;
            ArrayList<GridPane> rows = gridRows.get(k);
            GridPane row;
            Arena arena = arenasList.get(k);
            GridPane panel = gridsList.get(k);
            panel.setVgap(3);
            for (int i = 1; i <= Arena.height; i++) {
                row=rows.get(i-1);
                GridPane.setRowIndex(row,i-1);
                GridPane.setColumnIndex(row,0);
                for (int j = 1; j <= Arena.width; j++) {
                        field = arena.getFields()[i - 1][j - 1];
                        but = butsList.get(k)[i - 1][j - 1];
                        but.setOnAction(this::moveHumanPlayerUnit);
                        label = field.getLabel();
                        row.setHgap(-15);
                        row.add(label, j - 1, 0);
                        row.setPadding(new Insets(0,0,0,20*i-20));
                        label.setTextAlignment(TextAlignment.CENTER);
                        GridPane.setColumnIndex(but, j - 1);
                        GridPane.setValignment(label, VPos.BOTTOM);
                        GridPane.setHalignment(label, HPos.RIGHT);
                        if (!field.isUnitHere()) {
                            label.setVisible(false);
                        }
                }
            }

        }
    }

    private void fillLabels() {
        int counter = 0;
        for (int k = 0; k < labelsList.size(); k++) {
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    arenasList.get(k).getFields()[i - 1][j - 1].setLabel(labelsList.get(k).get(counter));
                    counter++;
                }
            }
        }
    }

    private void fillInitialFieldsColors() {
        String initialBack = null;
        for (Arena arena : arenasList) {
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    switch (arena.getType()) {
                        case "Air":
                            initialBack = "-fx-background-color: yellow;";
                            break;
                        case "Land":
                            initialBack = "-fx-background-color: lime;";
                            break;
                        case "Water":
                            initialBack = "-fx-background-color: cyan;";
                            break;
                        default:
                            initialBack = "ups...check BattlefieldControl fillInitialFieldsColors()";
                            break;

                    }
                    arena.getFields()[i - 1][j - 1].setInitialBackgroundURL(initialBack);
                }
            }
        }
    }

    private void placeAttackersArmy() {
        for (Arena arena : arenasList) {
            for (Unit unit : attackersArmy) {
                if (arena.getType().equals(unit.getType()) && unit.isSet() && unit.getAmount()>0) {
                    arena.getFields()[unit.getActualX() - 1][unit.getActualY() - 1].setUnit(unit);
//                              System.out.println("unit: " + unit.getName() +
//                              " X: " + unit.getActualX() + " Y:" + unit.getActualY()+ " Arena: " + arena.getType());
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
                }
            }
        }

    }

    private void defaultSizeOfButs() {
        elementalButtonsList.add(airButs);
        elementalButtonsList.add(landButs);
        elementalButtonsList.add(waterButs);
        for (ArrayList<JFXButton> list : elementalButtonsList) {
            for (JFXButton but : list) {
                but.setPrefSize(60, 47.2);
                but.setMinSize(60, 47.2);
                but.setMaxSize(60, 47.2);
            }
        }
    }

    private void initialFieldsSettings() {

        for (Arena arena : arenasList) {
            Field[][] fields = arena.getFields();
            Field field;
            JFXButton but;
            for (int i = 1; i <= Arena.height; i++) {
                for (int j = 1; j <= Arena.width; j++) {
                    for (ArrayList<Unit> army : armiesList) {
                        for (Unit unit : army) {
                            if (unit.getActualX() == i && unit.getActualY() == j && unit.getType().equals(arena.getType()) && unit.getAmount()>0) {
                                field = fields[i - 1][j - 1];
                                but = field.getButton();
                                field.setActualBackgroundURL(unit.getFractionBackground());
                                String unitImageURL = unit.getIcon();
                                String backgroundStyle = field.getActualBackgroundURL();
                                changeButtonStyleAndImage(but, backgroundStyle, unitImageURL, "small");
                            }
                        }
                    }
                }
            }
        }
    }

    private void fillLists() {
        arenasList.add(airArena);
        arenasList.add(landArena);
        arenasList.add(waterArena);
        labelsList.add(airLabels);
        labelsList.add(landLabels);
        labelsList.add(waterLabels);
        gridsList.add(airGrid);
        gridsList.add(landGrid);
        gridsList.add(waterGrid);
        butsList.add(airButtons);
        butsList.add(landButtons);
        butsList.add(waterButtons);
        maxButsList.add(maxAir);
        maxButsList.add(maxLand);
        maxButsList.add(maxWater);
        anchorsList.add(airAnchor);
        anchorsList.add(landAnchor);
        anchorsList.add(waterAnchor);
        fightingPlayers.add(attackingPlayer);
        fightingPlayers.add(defendingPlayer);
        initiativeLabels.add(initiative1);
        initiativeLabels.add(initiative2);
        initiativeLabels.add(initiative3);

    }

    private void hideMinMaxBut() {
        minimizeButton.setVisible(false);
        maxAir.setVisible(false);
        maxLand.setVisible(false);
        maxWater.setVisible(false);
    }

    public void fillRowsArray() {
        airGrids.add(airRow1);
        airGrids.add(airRow2);
        airGrids.add(airRow3);
        airGrids.add(airRow4);
        airGrids.add(airRow5);

        landGrids.add(landRow1);
        landGrids.add(landRow2);
        landGrids.add(landRow3);
        landGrids.add(landRow4);
        landGrids.add(landRow5);

        waterGrids.add(waterRow1);
        waterGrids.add(waterRow2);
        waterGrids.add(waterRow3);
        waterGrids.add(waterRow4);
        waterGrids.add(waterRow5);

        gridRows.add(airGrids);
        gridRows.add(landGrids);
        gridRows.add(waterGrids);
    }

    private void hideRows(){
        for (ArrayList<GridPane> list: gridRows){
            for (GridPane row:list){
                row.setVisible(false);
            }
        }
    }

    private void hideGrids(){
        for (GridPane pane:gridsList){
            pane.setVisible(false);
        }
    }

    private void hideAnchorPanes() {
        airAnchor.setVisible(false);
        landAnchor.setVisible(false);
        waterAnchor.setVisible(false);
    }

    private void hideAdditionalPanels(){
        attackersAvatar.setVisible(false);
        defendersAvatar.setVisible(false);
        actualPlayerSignaller.setVisible(false);
        initiativePanel.setVisible(false);
    }

    private void showAdditionalPanels(){
        attackersAvatar.setVisible(true);
        defendersAvatar.setVisible(true);
        actualPlayerSignaller.setVisible(true);
        initiativePanel.setVisible(true);
    }

    @FXML
    public void maximizePanel(ActionEvent e) {
        for (JFXButton maxBut : maxButsList) {
            if (e.getSource().equals(maxBut)) {
                GridPane panel = new GridPane();
                AnchorPane anchor = new AnchorPane();
                ArrayList<GridPane> grids = new ArrayList<>();
                if (maxBut.equals(maxAir)) {
                    panel = airGrid;
                    anchor = airAnchor;
                    grids=airGrids;
                    actualMaximizedArena=airArena;
                } else if (maxBut.equals(maxLand)) {
                    panel = landGrid;
                    anchor = landAnchor;
                    grids=landGrids;
                    actualMaximizedArena=landArena;
                } else if (maxBut.equals(maxWater)) {
                    panel = waterGrid;
                    anchor = waterAnchor;
                    grids=waterGrids;
                    actualMaximizedArena=waterArena;
                }
                maximize(panel,anchor,grids);
            }


        }

    }

    private void maximize(GridPane panel,AnchorPane anchor,ArrayList<GridPane> grids){
        showActionButtons(false);
        hideMinMaxBut();
        minimizeButton.setVisible(true);
        left.setVisible(true);
        right.setVisible(true);
        hideAdditionalPanels();
        updateUnitInfoPanel();
        actualMaximizedGrid = panel;
        actualMaximizedAnchor = anchor;
        actualMaximizedGridRows = grids;
        actualShowArenaSize = "big";
        hideAnchorPanes();
        anchor.setVisible(true);
        AnchorPane.setTopAnchor(anchor, 20.0);
        AnchorPane.setLeftAnchor(anchor, 20.0);
        anchor.setPrefSize(1490, 830);
        anchor.setStyle(GraphicsStorage.begin + GraphicsStorage.settingsBackground + GraphicsStorage.end +
                "-fx-background-size: 100% 100%;");
        AnchorPane.setTopAnchor(panel, 40.0);
        AnchorPane.setLeftAnchor(panel, 40.0);
        panel.setVgap(13.0);
        isMaximized = true;
        for (int k=0;k<5;k++){
            grids.get(k).setHgap(-40);
            grids.get(k).setPadding(new Insets(0,0,0,k*50));
        }
        for (ArrayList<JFXButton> eleButList : elementalButtonsList) {
            for (JFXButton but : eleButList) {
                but.setPrefSize(150, 140);
                but.setMinSize(but.getPrefWidth(), but.getPrefHeight());
                but.setMaxSize(but.getPrefWidth(), but.getPrefHeight());
            }
        }
        for (Arena arena : arenasList) {
            for (ArrayList<Unit> army : armiesList) {
                for (int i = 1; i <= Arena.height; i++) {
                    for (int j = 1; j <= Arena.width; j++) {
                        if (arena.getIsUnitHere(i, j)) {
                            for (Unit unit : army) {
                                if (unit.getActualX() == i && unit.getActualY() == j && unit.getType().equals(arena.getType())) {
                                    arena.getFields()[i - 1][j - 1].getButton().setGraphic(null);
                                    ImageView unitImageView = new ImageView(unit.getIcon());
                                    unitImageView.setStyle(GraphicsStorage.imageViewBig);
                                    arena.getFields()[i - 1][j - 1].getButton().setGraphic(unitImageView);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void minimizePanel(ActionEvent e){
            if (e.getSource().equals(minimizeButton)) {
                minimize();
            }
    }

    private void minimize(){
        showActionButtons(true);
        attackingUnitStatInfoPanel.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);
        showAdditionalPanels();
        GridPane panel = actualMaximizedGrid;
        AnchorPane anchor = actualMaximizedAnchor;
        ArrayList<GridPane> grids = actualMaximizedGridRows;
        actualShowArenaSize="small";
        hideMinMaxBut();
        isMaximized=false;
        for (int k=0;k<3;k++) {
            anchorsList.get(k).setVisible(true);
            maxButsList.get(k).setVisible(true);
        }
        AnchorPane.setTopAnchor(anchor,restoreDefaultTop(anchor));
        AnchorPane.setLeftAnchor(anchor,restoreDefaultLeft(anchor));
        anchor.setPrefSize(-1,-1);
        anchor.setStyle("-fx-background-image: null;");
        AnchorPane.setTopAnchor(panel, 0.0);
        AnchorPane.setLeftAnchor(panel, 0.0);
        panel.setVgap(3.0);
        for (int k=0;k<5;k++){
            grids.get(k).setHgap(-15);
            grids.get(k).setPadding(new Insets(0,0,0,k*20));
        }
        for (ArrayList<JFXButton> list:elementalButtonsList){
            for (JFXButton but:list){
                but.setPrefSize(60,47.2);
                but.setMinSize(but.getPrefWidth(),but.getPrefHeight());
                but.setMaxSize(but.getPrefWidth(),but.getPrefHeight());
            }
        }
        for (Arena arena:arenasList){
            for (ArrayList<Unit> army:armiesList){
                for (int i=1;i<=Arena.height;i++){
                    for (int j=1;j<=Arena.width;j++){
                        if (arena.getIsUnitHere(i,j) ){
                            for (Unit unit:army){
                                if (unit.getActualX()==i && unit.getActualY()==j && unit.getType().equals(arena.getType())){
                                    arena.getFields()[i-1][j-1].getButton().setGraphic(null);
                                    ImageView unitImageView = new ImageView(unit.getIcon());
                                    unitImageView.setStyle(GraphicsStorage.imageViewSmall);
                                    arena.getFields()[i-1][j-1].getButton().setGraphic(unitImageView);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private Double restoreDefaultTop(AnchorPane anchor) {
        if (anchor.equals(airAnchor)){
            return Constants.topAir;
        } else if (anchor.equals(landAnchor)){
             return Constants.topLand;
        } else if (anchor.equals(waterAnchor)){
             return Constants.topWater;
        }

        return -1.0;
    }

    private Double restoreDefaultLeft(AnchorPane anchor) {
        if (anchor.equals(airAnchor)){
            return Constants.leftAir;
        } else if (anchor.equals(landAnchor)){
            return Constants.leftLand;
        } else if (anchor.equals(waterAnchor)){
            return Constants.leftWater;
        }

        return -1.0;
    }

    public void fillElementalButsAndLabels(){
        airButs.add(air11);
        airButs.add(air12);
        airButs.add(air13);
        airButs.add(air14);
        airButs.add(air15);
        airButs.add(air16);
        airButs.add(air17);
        airButs.add(air18);
        airButs.add(air19);
        airButs.add(air20);

        airButs.add(air21);
        airButs.add(air22);
        airButs.add(air23);
        airButs.add(air24);
        airButs.add(air25);
        airButs.add(air26);
        airButs.add(air27);
        airButs.add(air28);
        airButs.add(air29);
        airButs.add(air30);

        airButs.add(air31);
        airButs.add(air32);
        airButs.add(air33);
        airButs.add(air34);
        airButs.add(air35);
        airButs.add(air36);
        airButs.add(air37);
        airButs.add(air38);
        airButs.add(air39);
        airButs.add(air40);

        airButs.add(air41);
        airButs.add(air42);
        airButs.add(air43);
        airButs.add(air44);
        airButs.add(air45);
        airButs.add(air46);
        airButs.add(air47);
        airButs.add(air48);
        airButs.add(air49);
        airButs.add(air50);

        airButs.add(air51);
        airButs.add(air52);
        airButs.add(air53);
        airButs.add(air54);
        airButs.add(air55);
        airButs.add(air56);
        airButs.add(air57);
        airButs.add(air58);
        airButs.add(air59);
        airButs.add(air60);

        waterButs.add(water11);
        waterButs.add(water12);
        waterButs.add(water13);
        waterButs.add(water14);
        waterButs.add(water15);
        waterButs.add(water16);
        waterButs.add(water17);
        waterButs.add(water18);
        waterButs.add(water19);
        waterButs.add(water20);
        waterButs.add(water21);
        waterButs.add(water22);
        waterButs.add(water23);
        waterButs.add(water24);
        waterButs.add(water25);
        waterButs.add(water26);
        waterButs.add(water27);
        waterButs.add(water28);
        waterButs.add(water29);
        waterButs.add(water30);
        waterButs.add(water31);
        waterButs.add(water32);
        waterButs.add(water33);
        waterButs.add(water34);
        waterButs.add(water35);
        waterButs.add(water36);
        waterButs.add(water37);
        waterButs.add(water38);
        waterButs.add(water39);
        waterButs.add(water40);
        waterButs.add(water41);
        waterButs.add(water42);
        waterButs.add(water43);
        waterButs.add(water44);
        waterButs.add(water45);
        waterButs.add(water46);
        waterButs.add(water47);
        waterButs.add(water48);
        waterButs.add(water49);
        waterButs.add(water50);
        waterButs.add(water51);
        waterButs.add(water52);
        waterButs.add(water53);
        waterButs.add(water54);
        waterButs.add(water55);
        waterButs.add(water56);
        waterButs.add(water57);
        waterButs.add(water58);
        waterButs.add(water59);
        waterButs.add(water60);

        landButs.add(land11);
        landButs.add(land12);
        landButs.add(land13);
        landButs.add(land14);
        landButs.add(land15);
        landButs.add(land16);
        landButs.add(land17);
        landButs.add(land18);
        landButs.add(land19);
        landButs.add(land20);
        landButs.add(land21);
        landButs.add(land22);
        landButs.add(land23);
        landButs.add(land24);
        landButs.add(land25);
        landButs.add(land26);
        landButs.add(land27);
        landButs.add(land28);
        landButs.add(land29);
        landButs.add(land30);
        landButs.add(land31);
        landButs.add(land32);
        landButs.add(land33);
        landButs.add(land34);
        landButs.add(land35);
        landButs.add(land36);
        landButs.add(land37);
        landButs.add(land38);
        landButs.add(land39);
        landButs.add(land40);
        landButs.add(land41);
        landButs.add(land42);
        landButs.add(land43);
        landButs.add(land44);
        landButs.add(land45);
        landButs.add(land46);
        landButs.add(land47);
        landButs.add(land48);
        landButs.add(land49);
        landButs.add(land50);
        landButs.add(land51);
        landButs.add(land52);
        landButs.add(land53);
        landButs.add(land54);
        landButs.add(land55);
        landButs.add(land56);
        landButs.add(land57);
        landButs.add(land58);
        landButs.add(land59);
        landButs.add(land60);
        
        airLabels.add(air11Label);
        airLabels.add(air12Label);
        airLabels.add(air13Label);
        airLabels.add(air14Label);
        airLabels.add(air15Label);
        airLabels.add(air16Label);
        airLabels.add(air17Label);
        airLabels.add(air18Label);
        airLabels.add(air19Label);
        airLabels.add(air20Label);
        airLabels.add(air21Label);
        airLabels.add(air22Label);
        airLabels.add(air23Label);
        airLabels.add(air24Label);
        airLabels.add(air25Label);
        airLabels.add(air26Label);
        airLabels.add(air27Label);
        airLabels.add(air28Label);
        airLabels.add(air29Label);
        airLabels.add(air30Label);
        airLabels.add(air31Label);
        airLabels.add(air32Label);
        airLabels.add(air33Label);
        airLabels.add(air34Label);
        airLabels.add(air35Label);
        airLabels.add(air36Label);
        airLabels.add(air37Label);
        airLabels.add(air38Label);
        airLabels.add(air39Label);
        airLabels.add(air40Label);
        airLabels.add(air41Label);
        airLabels.add(air42Label);
        airLabels.add(air43Label);
        airLabels.add(air44Label);
        airLabels.add(air45Label);
        airLabels.add(air46Label);
        airLabels.add(air47Label);
        airLabels.add(air48Label);
        airLabels.add(air49Label);
        airLabels.add(air50Label);
        airLabels.add(air51Label);
        airLabels.add(air52Label);
        airLabels.add(air53Label);
        airLabels.add(air54Label);
        airLabels.add(air55Label);
        airLabels.add(air56Label);
        airLabels.add(air57Label);
        airLabels.add(air58Label);
        airLabels.add(air59Label);
        airLabels.add(air60Label);
        waterLabels.add(water11Label);
        waterLabels.add(water12Label);
        waterLabels.add(water13Label);
        waterLabels.add(water14Label);
        waterLabels.add(water15Label);
        waterLabels.add(water16Label);
        waterLabels.add(water17Label);
        waterLabels.add(water18Label);
        waterLabels.add(water19Label);
        waterLabels.add(water20Label);
        waterLabels.add(water21Label);
        waterLabels.add(water22Label);
        waterLabels.add(water23Label);
        waterLabels.add(water24Label);
        waterLabels.add(water25Label);
        waterLabels.add(water26Label);
        waterLabels.add(water27Label);
        waterLabels.add(water28Label);
        waterLabels.add(water29Label);
        waterLabels.add(water30Label);
        waterLabels.add(water31Label);
        waterLabels.add(water32Label);
        waterLabels.add(water33Label);
        waterLabels.add(water34Label);
        waterLabels.add(water35Label);
        waterLabels.add(water36Label);
        waterLabels.add(water37Label);
        waterLabels.add(water38Label);
        waterLabels.add(water39Label);
        waterLabels.add(water40Label);
        waterLabels.add(water41Label);
        waterLabels.add(water42Label);
        waterLabels.add(water43Label);
        waterLabels.add(water44Label);
        waterLabels.add(water45Label);
        waterLabels.add(water46Label);
        waterLabels.add(water47Label);
        waterLabels.add(water48Label);
        waterLabels.add(water49Label);
        waterLabels.add(water50Label);
        waterLabels.add(water51Label);
        waterLabels.add(water52Label);
        waterLabels.add(water53Label);
        waterLabels.add(water54Label);
        waterLabels.add(water55Label);
        waterLabels.add(water56Label);
        waterLabels.add(water57Label);
        waterLabels.add(water58Label);
        waterLabels.add(water59Label);
        waterLabels.add(water60Label);
        landLabels.add(land11Label);
        landLabels.add(land12Label);
        landLabels.add(land13Label);
        landLabels.add(land14Label);
        landLabels.add(land15Label);
        landLabels.add(land16Label);
        landLabels.add(land17Label);
        landLabels.add(land18Label);
        landLabels.add(land19Label);
        landLabels.add(land20Label);
        landLabels.add(land21Label);
        landLabels.add(land22Label);
        landLabels.add(land23Label);
        landLabels.add(land24Label);
        landLabels.add(land25Label);
        landLabels.add(land26Label);
        landLabels.add(land27Label);
        landLabels.add(land28Label);
        landLabels.add(land29Label);
        landLabels.add(land30Label);
        landLabels.add(land31Label);
        landLabels.add(land32Label);
        landLabels.add(land33Label);
        landLabels.add(land34Label);
        landLabels.add(land35Label);
        landLabels.add(land36Label);
        landLabels.add(land37Label);
        landLabels.add(land38Label);
        landLabels.add(land39Label);
        landLabels.add(land40Label);
        landLabels.add(land41Label);
        landLabels.add(land42Label);
        landLabels.add(land43Label);
        landLabels.add(land44Label);
        landLabels.add(land45Label);
        landLabels.add(land46Label);
        landLabels.add(land47Label);
        landLabels.add(land48Label);
        landLabels.add(land49Label);
        landLabels.add(land50Label);
        landLabels.add(land51Label);
        landLabels.add(land52Label);
        landLabels.add(land53Label);
        landLabels.add(land54Label);
        landLabels.add(land55Label);
        landLabels.add(land56Label);
        landLabels.add(land57Label);
        landLabels.add(land58Label);
        landLabels.add(land59Label);
        landLabels.add(land60Label);

    }

    public void fillArenas(){
        int counter =0;
        for (int i = 1; i<=Arena.height; i++){
            for (int j = 1; j<=Arena.width; j++){
                airButtons[i-1][j-1]= airButs.get(counter);
                landButtons[i-1][j-1]= landButs.get(counter);
                waterButtons[i-1][j-1]= waterButs.get(counter);
                counter++;
            }
        }

    }

    public void fillBaseArenaColor(){
        for (JFXButton button:airButs){
            button.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color: yellow;");
        }
        for (JFXButton button:landButs){
            button.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color: lime;");
        }
        for (JFXButton button:waterButs){
            button.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-background-color: cyan;");
        }
    }

    private void fillArenasBackgrounds(){
        airArenaBack.setPrefWidth(Constants.width);
        landArenaBack.setPrefWidth(Constants.width);
        waterArenaBack.setPrefWidth(Constants.width);

        airArenaBack.setPrefHeight(Constants.height/3);
        landArenaBack.setPrefHeight(Constants.height/3);
        waterArenaBack.setPrefHeight(Constants.height/3);


            switch (Sectors.getSelectedSector().getActualFraction()){
                case "Jungle":
                    airArenaBack.setStyle(GraphicsStorage.combat_jng_air);
                    landArenaBack.setStyle(GraphicsStorage.combat_jng_land);
                    waterArenaBack.setStyle(GraphicsStorage.combat_jng_water);
                    break;
                case "Atlantis":
                    airArenaBack.setStyle(GraphicsStorage.combat_atl_air);
                    landArenaBack.setStyle(GraphicsStorage.combat_atl_land);
                    waterArenaBack.setStyle(GraphicsStorage.combat_atl_water);
                    break;
                case "Cyberpunk":
                    airArenaBack.setStyle(GraphicsStorage.combat_cbr_air);
                    landArenaBack.setStyle(GraphicsStorage.combat_cbr_land);
                    waterArenaBack.setStyle(GraphicsStorage.combat_cbr_water);
                    break;
            }



    }

    private void changeButtonStyleAndImage(JFXButton button, String backgroundURLorFxStyle,String imageURL,String bigOrSmall){
        changeButtonImage(button,imageURL,bigOrSmall);
        changeButtonBackground(button,backgroundURLorFxStyle);
    }

    private void changeButtonBackground(JFXButton button,String backgroundURLorFxStyle){
        String sub = backgroundURLorFxStyle.substring(0,4);
        if (sub.equals("file")){
            button.setStyle(GraphicsStorage.begin+backgroundURLorFxStyle+GraphicsStorage.end);
        } else if (sub.equals("-fx-")){
            button.setStyle(backgroundURLorFxStyle);
        } else if (sub.equals("ups.")){
            System.out.println(backgroundURLorFxStyle + " and also changeButtonBackground() method");
        }
    }

    private void changeButtonImage(JFXButton button,String imageURL,String bigOrSmall){
        button.setGraphic(null);
        ImageView imageView;
        if (!imageURL.equals("null")){
            imageView = new ImageView(imageURL);
            button.setGraphic(imageView);
            if (bigOrSmall.toLowerCase().equals("big")){
                imageView.setStyle(GraphicsStorage.imageViewBig);
            } else if (bigOrSmall.toLowerCase().equals("small")){
                imageView.setStyle(GraphicsStorage.imageViewSmall);
            }
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

    public void swipeLeft(){
        minimize();
        GridPane panel = new GridPane();
        AnchorPane anchor = new AnchorPane();
        ArrayList<GridPane> grids = new ArrayList<>();
        if (actualMaximizedArena.getType().equals("Land")) {
            panel = airGrid;
            anchor = airAnchor;
            grids=airGrids;
            actualMaximizedArena=airArena;
        } else if (actualMaximizedArena.getType().equals("Water")) {
            panel = landGrid;
            anchor = landAnchor;
            grids=landGrids;
            actualMaximizedArena=landArena;
        } else if (actualMaximizedArena.getType().equals("Air")) {
            panel = waterGrid;
            anchor = waterAnchor;
            grids=waterGrids;
            actualMaximizedArena=waterArena;
        }
        maximize(panel,anchor,grids);

    }

    public void swipeRight(){
        minimize();
        GridPane panel = new GridPane();
        AnchorPane anchor = new AnchorPane();
        ArrayList<GridPane> grids = new ArrayList<>();
        if (actualMaximizedArena.getType().equals("Water")) {
            panel = airGrid;
            anchor = airAnchor;
            grids=airGrids;
            actualMaximizedArena=airArena;
        } else if (actualMaximizedArena.getType().equals("Air")) {
            panel = landGrid;
            anchor = landAnchor;
            grids=landGrids;
            actualMaximizedArena=landArena;
        } else if (actualMaximizedArena.getType().equals("Land")) {
            panel = waterGrid;
            anchor = waterAnchor;
            grids=waterGrids;
            actualMaximizedArena=waterArena;
        }
        maximize(panel,anchor,grids);
    }

    public void swipeArenas(ActionEvent e) {

        if (e.getSource().equals(left)){
            swipeLeft();
        } else if (e.getSource().equals(right)){
            swipeRight();
        }


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

    // TODO: 21.10.2020 add  AI combat and movement,
}
