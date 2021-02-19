package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ArmyPanelControl {

    @FXML
    public AnchorPane armyPanel;
    @FXML
    public Label landName1,landName2,landName3,landName4,landName5,landName6,landAmount1,landAmount2,landAmount3,landAmount4,
            landAmount5,landAmount6,
            airName1,airName2,airName3,airAmount1,airAmount2,airAmount3,
            waterName1,waterName2,waterName3,waterAmount1,waterAmount2,waterAmount3,unitInfoIcon;
    @FXML
    public JFXButton landSet1,landSet2,landSet3,landSet4,landSet5,landSet6,airSet1,airSet2,airSet3,waterSet1,waterSet2,waterSet3;
    @FXML
    public Label unitInfo,unitAtkStat,unitHpStat,unitRangeStat,unitMovementStat,unitType,selectedUnitActualPosition;
    @FXML
    public AnchorPane statInfoPanel;
    @FXML
    public JFXButton 
    air11,air21,air31,air41,air51,water11,water21,water31,water41,water51,land11,land21,land31,land41,land51,
    air12,air22,air32,air42,air52,water12,water22,water32,water42,water52,land12,land22,land32,land42,land52,
    air13,air23,air33,air43,air53,water13,water23,water33,water43,water53,land13,land23,land33,land43,land53,
    air14,air24,air34,air44,air54,water14,water24,water34,water44,water54,land14,land24,land34,land44,land54,
    air15,air25,air35,air45,air55,water15,water25,water35,water45,water55,land15,land25,land35,land45,land55,
    air16,air26,air36,air46,air56,water16,water26,water36,water46,water56,land16,land26,land36,land46,land56,
    air17,air27,air37,air47,air57,water17,water27,water37,water47,water57,land17,land27,land37,land47,land57,
    air18,air28,air38,air48,air58,water18,water28,water38,water48,water58,land18,land28,land38,land48,land58,
    air19,air29,air39,air49,air59,water19,water29,water39,water49,water59,land19,land29,land39,land49,land59,
    air20,air30,air40,air50,air60,water20,water30,water40,water50,water60,land20,land30,land40,land50,land60;

    public String selectedUnit,selectedUnitType, selectedFieldType;
    public boolean isSetButtonActive=false,isRemoveButtonActive=false;
    @FXML
    public JFXButton isRemoveActive;
    @FXML
    public AnchorPane airArenaBack,landArenaBack,waterArenaBack;
    @FXML
    public AnchorPane airUnits,landUnits,waterUnits;
    @FXML
    public JFXButton exitToCity,exitToMap;

    private ArrayList<JFXButton> airButs = new ArrayList<>();
    private ArrayList<JFXButton> landButs = new ArrayList<>();
    private ArrayList<JFXButton> waterButs = new ArrayList<>();

    private JFXButton[][] airField = new JFXButton[Arena.height][Arena.width];
    private JFXButton[][] landField = new JFXButton[Arena.height][Arena.width];
    private JFXButton[][] waterField = new JFXButton[Arena.height][Arena.width];

    private ArrayList<ArrayList<JFXButton>> butsList = new ArrayList<>();
    
    private Arena airArena   = Players.getActualPlayer().getArenasList().get(0);
    private Arena landArena  = Players.getActualPlayer().getArenasList().get(1);
    private Arena waterArena = Players.getActualPlayer().getArenasList().get(2);

    private ArrayList<Arena> arenasList = new ArrayList<>();

    
    private ArrayList<JFXButton> buts = new ArrayList<>();
    private ArrayList<Label> names = new ArrayList<>();

    @FXML
    public void initialize(){
        fillMissingGraphics();
        fillArenasBackgrounds();
        setLabels();
        fillArray();
        fillElementalButs();
        airArena.setButtonsToFields(airField);
        landArena.setButtonsToFields(landField);
        waterArena.setButtonsToFields(waterField);
        fillArenas();
        fillArenasList();
        updateButtonLabels();
        fillButsList();
        styliseButtonsPrefDimensions();
    }

    private void fillMissingGraphics(){
        statInfoPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        airUnits.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        landUnits.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        waterUnits.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        airSet1.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        airSet2.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        airSet3.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        landSet1.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        landSet2.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        landSet3.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        landSet4.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        landSet5.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        landSet6.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        waterSet1.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        waterSet2.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        waterSet3.setStyle(GraphicsStorage.begin+GraphicsStorage.set+GraphicsStorage.end);
        exitToCity.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        exitToMap.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);


    }

    private void fillArenasList() {
        arenasList.add(airArena);
        arenasList.add(landArena);
        arenasList.add(waterArena);
    }

    private void styliseButtonsPrefDimensions(){
        for (ArrayList<JFXButton> buts:butsList){
            for (JFXButton but:buts){
                but.setPrefSize(60,47.2);
                but.setMinSize(60,47.2);
                but.setMaxSize(60,47.2);

            }
        }
    }

    private void fillButsList(){
        butsList.add(airButs);
        butsList.add(landButs);
        butsList.add(waterButs);

    }

    private void updateButtonLabels(){
        for (int i = 1; i<=Arena.height; i++){
            for (int j = 1; j<=2; j++){
            for (Arena arena:arenasList) {
                if ( arena.getIsUnitHere(i,j)) {
                    Unit unit = Players.getActualPlayer().getUnit(i,j);
                    JFXButton button;
                    switch (unit.getType()) {
                                case "Air":
                                    button = airField[i-1][j-1];
                                    break;
                                case "Land":
                                    button=landField[i-1][j-1];
                                    break;
                                case "Water":
                                    button=waterField[i-1][j-1];
                                    break;
                        default:
                            button = new JFXButton();
                            System.out.println("Sth went wrong");
                            break;
                    }
                        button.setStyle(GraphicsStorage.begin+Players.getActualPlayer().getUnit(i,j).getFractionBackground() + GraphicsStorage.end);
                        String unitImageURL = unit.getIcon();
                        ImageView unitImageView = new ImageView(unitImageURL);
                        button.setGraphic(unitImageView);
                        unitImageView.setStyle(GraphicsStorage.imageViewSmall);
                    }

                    }
                }
            }

    }

    private void fillArenasBackgrounds(){
        airArenaBack.setPrefWidth(Constants.width);
        landArenaBack.setPrefWidth(Constants.width);
        waterArenaBack.setPrefWidth(Constants.width);

        airArenaBack.setPrefHeight(Constants.height/3);
        landArenaBack.setPrefHeight(Constants.height/3);
        waterArenaBack.setPrefHeight(Constants.height/3);

        switch (Players.getActualPlayer().getFraction().getName()){
            case "Jungle":
                airArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_jng_air+GraphicsStorage.end);
                landArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_jng_land+GraphicsStorage.end);
                waterArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_jng_water+GraphicsStorage.end);
                break;
            case "Atlantis":
                airArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_atl_air+GraphicsStorage.end);
                landArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_atl_land+GraphicsStorage.end);
                waterArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_atl_water+GraphicsStorage.end);
                break;
            case "Cyberpunk":
                airArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_cbr_air+GraphicsStorage.end);
                landArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_cbr_land+GraphicsStorage.end);
                waterArenaBack.setStyle(GraphicsStorage.begin+GraphicsStorage.combat_cbr_water+GraphicsStorage.end);
                break;
        }
        
    }

    public void fillElementalButs(){
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
        
        
    }
    
    public void fillArenas(){
        int counter =0;
        for (int i = 1; i<=Arena.height; i++){
            for (int j = 1; j<=Arena.width; j++){
                airField[i-1][j-1]= airButs.get(counter);
                landField[i-1][j-1]= landButs.get(counter);
                waterField[i-1][j-1]= waterButs.get(counter);
                counter++;
            }
        }

    }
    
    private void fillArray(){
        buts.add(landSet1);
        buts.add(landSet2);
        buts.add(landSet3);
        buts.add(landSet4);
        buts.add(landSet5);
        buts.add(landSet6);
        buts.add(airSet1);
        buts.add(airSet2);
        buts.add(airSet3);
        buts.add(waterSet1);
        buts.add(waterSet2);
        buts.add(waterSet3);

        names.add(landName1);
        names.add(landName2);
        names.add(landName3);
        names.add(landName4);
        names.add(landName5);
        names.add(landName6);
        names.add(airName1);
        names.add(airName2);
        names.add(airName3);
        names.add(waterName1);
        names.add(waterName2);
        names.add(waterName3);
    }

    @FXML
    public void activateRemoveButton(){
        isRemoveActive.setStyle("-fx-background-color: red");
        isRemoveButtonActive=true;
        isSetButtonActive=false;
    }

    public void activateSetButton(){
        isRemoveActive.setStyle("-fx-background-color: white");
        isRemoveButtonActive=false;
        isSetButtonActive=true;
    }

    @FXML
    public void setLabels(){

        landName1.setText( Players.getActualPlayer().getLandType().get(0).getName());
        landName2.setText( Players.getActualPlayer().getLandType().get(1).getName());
        landName3.setText( Players.getActualPlayer().getLandType().get(2).getName());
        landName4.setText( Players.getActualPlayer().getLandType().get(3).getName());
        landAmount1.setText( Players.getActualPlayer().getLandType().get(0).getAmount() + "");
        landAmount2.setText( Players.getActualPlayer().getLandType().get(1).getAmount() + "");
        landAmount3.setText( Players.getActualPlayer().getLandType().get(2).getAmount() + "");
        landAmount4.setText( Players.getActualPlayer().getLandType().get(3).getAmount() + "");
        waterName1.setText( Players.getActualPlayer().getWaterType().get(0).getName());
        waterAmount1.setText( Players.getActualPlayer().getWaterType().get(0).getAmount() + "");
        airName1.setText( Players.getActualPlayer().getAirType().get(0).getName());
        airAmount1.setText( Players.getActualPlayer().getAirType().get(0).getAmount() + "");




        if ( Players.getActualPlayer().getFraction().getName().equals("Jungle")){
            landName5.setText( Players.getActualPlayer().getLandType().get(4).getName());
            landAmount5.setText( Players.getActualPlayer().getLandType().get(4).getAmount() + "");
            landName6.setText( Players.getActualPlayer().getLandType().get(5).getName());
            landAmount6.setText( Players.getActualPlayer().getLandType().get(5).getAmount() + "");
            waterName2.setText("");
            waterName3.setText("");
            airAmount2.setText("");
            airAmount3.setText("");
            waterAmount2.setText("");
            waterAmount3.setText("");
            airName2.setText("");
            airName3.setText("");
            waterSet2.setVisible(false);
            waterSet3.setVisible(false);
            airSet2.setVisible(false);
            airSet3.setVisible(false);

        } else if ( Players.getActualPlayer().getFraction().getName().equals("Atlantis")){
            landName5.setText("");
            landAmount5.setText( "");
            landName6.setText("");
            landAmount6.setText("");
            waterName2.setText( Players.getActualPlayer().getWaterType().get(1).getName());
            waterName3.setText( Players.getActualPlayer().getWaterType().get(2).getName());
            waterAmount2.setText( Players.getActualPlayer().getWaterType().get(1).getAmount() + "");
            waterAmount3.setText( Players.getActualPlayer().getWaterType().get(2).getAmount() + "");
            airAmount2.setText("");
            airAmount3.setText("");
            airName2.setText("");
            airName3.setText("");
            airSet2.setVisible(false);
            airSet3.setVisible(false);
            landSet5.setVisible(false);
            landSet6.setVisible(false);

        } else if ( Players.getActualPlayer().getFraction().getName().equals("Cyberpunk")){
            landName5.setText("");
            landAmount5.setText( "");
            landName6.setText("");
            landAmount6.setText( "");
            waterName2.setText("");
            waterName3.setText("");
            airAmount2.setText( Players.getActualPlayer().getAirType().get(1).getAmount() + "");
            airAmount3.setText( Players.getActualPlayer().getAirType().get(2).getAmount() + "");
            airName2.setText( Players.getActualPlayer().getAirType().get(1).getName());
            airName3.setText( Players.getActualPlayer().getAirType().get(2).getName());
            waterAmount2.setText("");
            waterAmount3.setText("");
            waterSet2.setVisible(false);
            waterSet3.setVisible(false);
            landSet5.setVisible(false);
            landSet6.setVisible(false);
        }




    }

    @FXML
    public void openCityPanel(){

        Stage primaryStage = (Stage) armyPanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CityPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openMapPanel(){
        Stage primaryStage = (Stage) armyPanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mapPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showUnitInfo(ActionEvent e){


        for (int k=0; k<buts.size();k++){
            if (e.getSource().equals(buts.get(k))){
                selectedUnit = names.get(k).getText();


                for (int i=0; i<  Players.getActualPlayer().getPlayersArmy().size();i++){
                    if (selectedUnit.equals( Players.getActualPlayer().getPlayersArmy().get(i).getName())){
                        unitAtkStat.setText( Players.getActualPlayer().getPlayersArmy().get(i).getATK() + "");
                        unitHpStat.setText( Players.getActualPlayer().getPlayersArmy().get(i).getHP() + "");
                        unitRangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(i).getATKRange() + "");
                        unitMovementStat.setText( Players.getActualPlayer().getPlayersArmy().get(i).getMovementRange() + "");
                        selectedUnitType= Players.getActualPlayer().getPlayersArmy().get(i).getType() + "";
                        unitType.setText(selectedUnitType);
                        unitInfo.setText( Players.getActualPlayer().getPlayersArmy().get(i).getName());
                        unitInfoIcon.setStyle(GraphicsStorage.begin+Players.getActualPlayer().getPlayersArmy().get(i).getFractionBackground()+GraphicsStorage.end);
                        unitInfoIcon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(i).getIcon()));
                        statInfoPanel.setVisible(true);
                        selectedUnitActualPosition.setText("(" +  Players.getActualPlayer().getPlayersArmy().get(i).getActualX() + ","
                                +  Players.getActualPlayer().getPlayersArmy().get(i).getActualY() + ")");
                        activateSetButton();
                    }
                }

            }

        }


    }

    @FXML
    public void setSelectedUnitActualPosition(ActionEvent e){

        for (int i = 1; i<=Arena.height; i++){
            for (int j = 1; j<=2; j++){

                if (isSetButtonActive){
                    if ((selectedUnitType.equals("Air") && e.getSource().equals(airField[i-1][j-1]) &&
                            !airArena.getIsUnitHere(i,j)) ||
                            ( selectedUnitType.equals("Land") && e.getSource().equals(landField[i-1][j-1]) &&
                                    !landArena.getIsUnitHere(i,j))
                            || ( selectedUnitType.equals("Water")) &&  e.getSource().equals(waterField[i-1][j-1]) &&
                            !waterArena.getIsUnitHere(i,j)){

                        if (Players.getActualPlayer().getUnit(selectedUnit).getAmount()>0 &&
                                ! Players.getActualPlayer().getUnit(selectedUnit).isSet()){

                            JFXButton fields;
                            Unit unit = Players.getActualPlayer().getUnit(selectedUnit);


                            switch(selectedUnitType){
                                case "Air":
                                    airArena.setIsUnitHere(i,j,true);
                                     unit.setInitialX(i);
                                     unit.setInitialY(j);
                                     unit.setSet(true);
                                    fields=airField[i-1][j-1];

                                    break;
                                case "Land":
                                    landArena.setIsUnitHere(i,j,true);
                                     unit.setInitialX(i);
                                     unit.setInitialY(j);
                                     unit.setSet(true);
                                    fields=landField[i-1][j-1];
                                    break;
                                case "Water":
                                    waterArena.setIsUnitHere(i,j,true);
                                     unit.setInitialX(i);
                                     unit.setInitialY(j);
                                     unit.setSet(true);
                                    fields=waterField[i-1][j-1];
                                    break;
                                default:
                                    fields = new JFXButton("Sth went wrong");
                                    break;
                            }
                            fields.setStyle(GraphicsStorage.begin+Players.getActualPlayer().getUnit(i,j).getFractionBackground() + GraphicsStorage.end);
                            String unitImageURL = unit.getIcon();
                            ImageView unitImageView = new ImageView(unitImageURL);
                            fields.setGraphic(unitImageView);
                            unitImageView.setStyle(GraphicsStorage.imageViewSmall);
                            selectedUnitActualPosition.setText("(" +  Players.getActualPlayer().getUnit(selectedUnit).getActualX() + ","
                                    +  Players.getActualPlayer().getUnit(selectedUnit).getActualY() + ")");

                        }

                    }
                } else if (isRemoveButtonActive){
                    for (int counter=0;counter< Players.getActualPlayer().getPlayersArmy().size();counter++){
                        if ( Players.getActualPlayer().getPlayersArmy().get(counter).getActualX()==i &&
                                 Players.getActualPlayer().getPlayersArmy().get(counter).getActualY()==j){


                            if (e.getSource().equals(airField[i-1][j-1]) && airArena.getIsUnitHere(i,j)){
                                airArena.setIsUnitHere(i,j,false);
                                airField[i-1][j-1].setGraphic(null);
                                airField[i-1][j-1].setStyle("-fx-background-color: yellow");
                                airField[i-1][j-1].setText("");
                                String tempName =  Players.getActualPlayer().getPlayersArmy().get(counter).getName();
                                 Players.getActualPlayer().getUnit(tempName).removeUnit();
                                selectedUnitPositionLabel(tempName);


                            }else if ( e.getSource().equals(landField[i-1][j-1]) && landArena.getIsUnitHere(i,j)){
                                landArena.setIsUnitHere(i,j,false);
                                landField[i-1][j-1].setGraphic(null);
                                landField[i-1][j-1].setStyle("-fx-background-color: lime");
                                landField[i-1][j-1].setText("");
                                String tempName =  Players.getActualPlayer().getPlayersArmy().get(counter).getName();
                                 Players.getActualPlayer().getUnit(tempName).removeUnit();
                                selectedUnitPositionLabel(tempName);


                            } else if (e.getSource().equals(waterField[i-1][j-1]) && waterArena.getIsUnitHere(i,j)){
                                waterArena.setIsUnitHere(i,j,false);
                                waterField[i-1][j-1].setGraphic(null);
                                waterField[i-1][j-1].setStyle("-fx-background-color: cyan");
                                waterField[i-1][j-1].setText("");
                                String tempName =  Players.getActualPlayer().getPlayersArmy().get(counter).getName();
                                 Players.getActualPlayer().getUnit(tempName).removeUnit();
                                 selectedUnitPositionLabel(tempName);
                            }



                        }

                    }

                }



            }
        }




    }

    private void selectedUnitPositionLabel(String name){
        selectedUnitActualPosition.setText("(" +
                Players.getActualPlayer().getUnit(name).getActualX() + ","
                +  Players.getActualPlayer().getUnit(name).getActualY() + ")");
    }
}
