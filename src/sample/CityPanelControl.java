package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CityPanelControl {


    @FXML
    public AnchorPane cityPanel;
    @FXML
    public Label resT11Label1,resT12Label1,resT13Label1,resT21Label1,resT22Label1,resT3Label1,resGoldLabel1,resT11Label2,
    resT12Label2,resT13Label2,resT21Label2,resT22Label2,resT3Label2,resGoldLabel2,resT11Label3,resT12Label3,resT13Label3,
    resT21Label3,resT22Label3,resT3Label3,resGoldLabel3;
    @FXML
    public AnchorPane buildMinePanel;
    @FXML
    public Label workName,workPrice4Next,workPrice,workHouseAmountName,workHouseAmount,workTotalWorkers,workFreeWorkers,
            workSetToMine, workSetToGoldMine;
    @FXML
    public JFXButton buyHouse,addArmyWorker,addMineWorker,subArmyWorker,subMineWorker;
    @FXML
    public Label T11mineName,T11minePrice,T11mineBought,T12mineName,T12minePrice,T12mineBought,T13mineName,
    T13minePrice,T13mineBought,T21mineName,T21minePrice,T21mineBought,T22mineName,T22minePrice,T22mineBought,
    T3mineName,T3minePrice,T3mineBought;
    @FXML
    public JFXButton buyT11mine,buyT12mine,buyT13mine,buyT21mine,buyT22mine,buyT3mine;
    @FXML
    public Tooltip T11minePriceTooltip,T12minePriceTooltip,T13minePriceTooltip,T21minePriceTooltip,T22minePriceTooltip,T3minePriceTooltip;
    @FXML
    public AnchorPane buildArmyPanel,unitT11,armyBuildingT11,unitT12,unitT21,unitT22,unitT23,unitT31,unitT32,unitT4,
            armyBuildingT12,armyBuildingT21,armyBuildingT22,armyBuildingT23,armyBuildingT31,armyBuildingT32,
            armyBuildingT4;
    @FXML
    public AnchorPane fastRecruitPanel;
    @FXML
    public Label miniUnitT11Name,miniUnitT11Amount,miniUnitT11AmountPerWeek,miniUnitT11AmountAvailable,miniUnitT11BuyAmount;
    @FXML
    public Slider miniUnitT11Slider;
    @FXML
    public JFXButton miniUnitT11Buy;
    @FXML
    public Label miniUnitT12Name,miniUnitT12Amount,miniUnitT12AmountPerWeek,miniUnitT12AmountAvailable,miniUnitT12BuyAmount;
    @FXML
    public Slider miniUnitT12Slider;
    @FXML
    public JFXButton miniUnitT12Buy;
    @FXML
    public Label miniUnitT21Name,miniUnitT21Amount,miniUnitT21AmountPerWeek,miniUnitT21AmountAvailable,miniUnitT21BuyAmount;
    @FXML
    public Slider miniUnitT21Slider;
    @FXML
    public JFXButton miniUnitT21Buy;
    @FXML
    public Label miniUnitT22Name,miniUnitT22Amount,miniUnitT22AmountPerWeek,miniUnitT22AmountAvailable,miniUnitT22BuyAmount;
    @FXML
    public Slider miniUnitT22Slider;
    @FXML
    public JFXButton miniUnitT22Buy;
    @FXML
    public Label miniUnitT23Name,miniUnitT23Amount,miniUnitT23AmountPerWeek,miniUnitT23AmountAvailable,miniUnitT23BuyAmount;
    @FXML
    public Slider miniUnitT23Slider;
    @FXML
    public JFXButton miniUnitT23Buy;
    @FXML
    public Label miniUnitT31Name,miniUnitT31Amount,miniUnitT31AmountPerWeek,miniUnitT31AmountAvailable,miniUnitT31BuyAmount;
    @FXML
    public Slider miniUnitT31Slider;
    @FXML
    public JFXButton miniUnitT31Buy;
    @FXML
    public Label miniUnitT32Name,miniUnitT32Amount,miniUnitT32AmountPerWeek,miniUnitT32AmountAvailable,miniUnitT32BuyAmount;
    @FXML
    public Slider miniUnitT32Slider;
    @FXML
    public JFXButton miniUnitT32Buy;
    @FXML
    public Label miniUnitT4Name,miniUnitT4Amount,miniUnitT4AmountPerWeek,miniUnitT4AmountAvailable,miniUnitT4BuyAmount;
    @FXML
    public Slider miniUnitT4Slider;
    @FXML
    public JFXButton miniUnitT4Buy;
    @FXML
    public JFXButton miniCloseButton;
    @FXML
    public JFXButton close1,close2,close3;

    private ArrayList<ArrayList<Label>> miniLabelsList = new ArrayList<>();
    private ArrayList<Label> mini1 = new ArrayList<>();
    private ArrayList<Label> mini2 = new ArrayList<>();
    private ArrayList<Label> mini3 = new ArrayList<>();
    private ArrayList<Label> mini4 = new ArrayList<>();
    private ArrayList<Label> mini5 = new ArrayList<>();
    private ArrayList<Label> mini6 = new ArrayList<>();
    private ArrayList<Label> mini7 = new ArrayList<>();
    private ArrayList<Label> mini8 = new ArrayList<>();
    private ArrayList<Slider> miniSliders = new ArrayList<>();
    private ArrayList<JFXButton> miniButtons = new ArrayList<>();
    
    private void fillMiniArrays(){
        mini1.add(miniUnitT11Name);
        mini1.add(miniUnitT11Amount);
        mini1.add(miniUnitT11AmountPerWeek);
        mini1.add(miniUnitT11AmountAvailable);
        mini1.add(miniUnitT11BuyAmount);

        mini2.add(miniUnitT12Name);
        mini2.add(miniUnitT12Amount);
        mini2.add(miniUnitT12AmountPerWeek);
        mini2.add(miniUnitT12AmountAvailable);
        mini2.add(miniUnitT12BuyAmount);

        mini3.add(miniUnitT21Name);
        mini3.add(miniUnitT21Amount);
        mini3.add(miniUnitT21AmountPerWeek);
        mini3.add(miniUnitT21AmountAvailable);
        mini3.add(miniUnitT21BuyAmount);

        mini4.add(miniUnitT22Name);
        mini4.add(miniUnitT22Amount);
        mini4.add(miniUnitT22AmountPerWeek);
        mini4.add(miniUnitT22AmountAvailable);
        mini4.add(miniUnitT22BuyAmount);

        mini5.add(miniUnitT23Name);
        mini5.add(miniUnitT23Amount);
        mini5.add(miniUnitT23AmountPerWeek);
        mini5.add(miniUnitT23AmountAvailable);
        mini5.add(miniUnitT23BuyAmount);

        mini6.add(miniUnitT31Name);
        mini6.add(miniUnitT31Amount);
        mini6.add(miniUnitT31AmountPerWeek);
        mini6.add(miniUnitT31AmountAvailable);
        mini6.add(miniUnitT31BuyAmount);

        mini7.add(miniUnitT32Name);
        mini7.add(miniUnitT32Amount);
        mini7.add(miniUnitT32AmountPerWeek);
        mini7.add(miniUnitT32AmountAvailable);
        mini7.add(miniUnitT32BuyAmount);

        mini8.add(miniUnitT4Name);
        mini8.add(miniUnitT4Amount);
        mini8.add(miniUnitT4AmountPerWeek);
        mini8.add(miniUnitT4AmountAvailable);
        mini8.add(miniUnitT4BuyAmount);
        
        miniSliders.add(miniUnitT11Slider);
        miniSliders.add(miniUnitT12Slider);
        miniSliders.add(miniUnitT21Slider);
        miniSliders.add(miniUnitT22Slider);
        miniSliders.add(miniUnitT23Slider);
        miniSliders.add(miniUnitT31Slider);
        miniSliders.add(miniUnitT32Slider);
        miniSliders.add(miniUnitT4Slider);

        miniButtons.add(miniUnitT11Buy);
        miniButtons.add(miniUnitT12Buy);
        miniButtons.add(miniUnitT21Buy);
        miniButtons.add(miniUnitT22Buy);
        miniButtons.add(miniUnitT23Buy);
        miniButtons.add(miniUnitT31Buy);
        miniButtons.add(miniUnitT32Buy);
        miniButtons.add(miniUnitT4Buy);

        miniLabelsList.add(mini1);
        miniLabelsList.add(mini2);
        miniLabelsList.add(mini3);
        miniLabelsList.add(mini4);
        miniLabelsList.add(mini5);
        miniLabelsList.add(mini6);
        miniLabelsList.add(mini7);
        miniLabelsList.add(mini8);
        
    }

    private void miniArraysLabelsSet(){
        for (int i=0;i<miniLabelsList.size();i++){
            ArrayList<Label> label = miniLabelsList.get(i);
            label.get(0).setText(Players.getActualPlayer().getPlayersArmy().get(i).getName());
            label.get(1).setText(Players.getActualPlayer().getPlayersArmy().get(i).getAmount() +"");
            label.get(2).setText(Players.getActualPlayer().getPlayersArmy().get(i).getAmountPerWeek() +"");
            if (Players.getActualPlayer().getPlayerMilitaryBuildings().get(i).isBuilt()){
                label.get(3).setText(Players.getActualPlayer().getPlayerMilitaryBuildings().get(i).getAmountAvailable() +"");
            } else  {
                label.get(3).setText( 0 + "");

            }
            if (Players.getActualPlayer().getPlayerMilitaryBuildings().get(i).isBuilt()
                    && Players.getActualPlayer().getPlayerMilitaryBuildings().get(i).getAmountAvailable()>0){
                miniSliders.get(i).setVisible(true);
            } else {
                miniSliders.get(i).setVisible(false);
            }
            miniSliders.get(i).setMax(Players.getActualPlayer().getPlayerMilitaryBuildings().get(i).getAmountAvailable());
            label.get(4).setText((int) miniSliders.get(i).getValue() +"");


//            for (JFXButton button:miniButtons){
//
//            }
        }
    }
    
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Label resT11Label1manage,resT12Label1manage,resT13Label1manage,resT21Label1manage,resT22Label1manage,resT3Label1manage,
            resT11Label2manage,resT12Label2manage,resT13Label2manage,resT21Label2manage,resT22Label2manage,resT3Label2manage;
    @FXML
    private JFXButton sub1,sub2,sub3,sub4,sub5,sub6,add1,add2,add3,add4,add5,add6;

    @FXML
    public JFXButton buildingArmyT11buy,unitT11buy;
    @FXML
    public Label buildingArmyT11icon,buildingArmyT11name,unitT11name,unitT11icon,
            unitT11atkStat,unitT11hpStat,unitT11rangeStat,unitT11movementStat,unitT11type;
    @FXML
    public Tooltip buildingArmyT11descriptionTooltip,buildingArmyT11unitTooltip,buildingArmyT11priceTooltip,unitT11priceTooltip;

    @FXML
    public JFXButton buildingArmyT12buy,unitT12buy;
    @FXML
    public Label buildingArmyT12icon,buildingArmyT12name,unitT12name,unitT12icon,
            unitT12atkStat,unitT12hpStat,unitT12rangeStat,unitT12movementStat,unitT12type;
    @FXML
    public Tooltip buildingArmyT12descriptionTooltip,buildingArmyT12unitTooltip,buildingArmyT12priceTooltip,unitT12priceTooltip;

    @FXML
    public JFXButton buildingArmyT21buy,unitT21buy;
    @FXML
    public Label buildingArmyT21icon,buildingArmyT21name,unitT21name,unitT21icon,
            unitT21atkStat,unitT21hpStat,unitT21rangeStat,unitT21movementStat,unitT21type;
    @FXML
    public Tooltip buildingArmyT21descriptionTooltip,buildingArmyT21unitTooltip,buildingArmyT21priceTooltip,unitT21priceTooltip;

    @FXML
    public JFXButton buildingArmyT22buy,unitT22buy;
    @FXML
    public Label buildingArmyT22icon,buildingArmyT22name,unitT22name,unitT22icon,
            unitT22atkStat,unitT22hpStat,unitT22rangeStat,unitT22movementStat,unitT22type;
    @FXML
    public Tooltip buildingArmyT22descriptionTooltip,buildingArmyT22unitTooltip,buildingArmyT22priceTooltip,unitT22priceTooltip;

    @FXML
    public JFXButton buildingArmyT23buy,unitT23buy;
    @FXML
    public Label buildingArmyT23icon,buildingArmyT23name,unitT23name,unitT23icon,
            unitT23atkStat,unitT23hpStat,unitT23rangeStat,unitT23movementStat,unitT23type;
    @FXML
    public Tooltip buildingArmyT23descriptionTooltip,buildingArmyT23unitTooltip,buildingArmyT23priceTooltip,unitT23priceTooltip;

    @FXML
    public JFXButton buildingArmyT31buy,unitT31buy;
    @FXML
    public Label buildingArmyT31icon,buildingArmyT31name,unitT31name,unitT31icon,
            unitT31atkStat,unitT31hpStat,unitT31rangeStat,unitT31movementStat,unitT31type;
    @FXML
    public Tooltip buildingArmyT31descriptionTooltip,buildingArmyT31unitTooltip,buildingArmyT31priceTooltip,unitT31priceTooltip;

    @FXML
    public JFXButton buildingArmyT32buy,unitT32buy;
    @FXML
    public Label buildingArmyT32icon,buildingArmyT32name,unitT32name,unitT32icon,
            unitT32atkStat,unitT32hpStat,unitT32rangeStat,unitT32movementStat,unitT32type;
    @FXML
    public Tooltip buildingArmyT32descriptionTooltip,buildingArmyT32unitTooltip,buildingArmyT32priceTooltip,unitT32priceTooltip;

    @FXML
    public JFXButton buildingArmyT4buy,unitT4buy;
    @FXML
    public Label buildingArmyT4icon,buildingArmyT4name,unitT4name,unitT4icon,
            unitT4atkStat,unitT4hpStat,unitT4rangeStat,unitT4movementStat,unitT4type;
    @FXML
    public Tooltip buildingArmyT4descriptionTooltip,buildingArmyT4unitTooltip,buildingArmyT4priceTooltip,unitT4priceTooltip;

    @FXML
    public Label unitT11Label1, unitT12Label1, unitT21Label1, unitT22Label1, unitT23Label1, unitT31Label1, unitT32Label1,
    unitT4Label1,unitT11Label2, unitT12Label2, unitT21Label2, unitT22Label2, unitT23Label2, unitT31Label2, unitT32Label2,
            unitT4Label2,unitT11Label3, unitT12Label3, unitT21Label3, unitT22Label3, unitT23Label3, unitT31Label3, unitT32Label3,
            unitT4Label3;

    private  boolean firstOpenOfFastRecruit = true;


    @FXML
    public void  setCityBackground(){
        String jungleCityImageURL = GraphicsStorage.cityJng;
        String atlantisCityImageURL = GraphicsStorage.cityAtl;
        String cyberpunkCityImageURL = GraphicsStorage.cityCbr;
        Image img = null;

        if (Players.getActualPlayer().getFraction().getName().equals("Jungle")){
            img = new Image(jungleCityImageURL);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Atlantis")){
            img = new Image(atlantisCityImageURL);
        } else if (Players.getActualPlayer().getFraction().getName().equals("Cyberpunk")){
            img = new Image(cyberpunkCityImageURL);
        }
        backgroundImageView.setImage(img);

        backgroundImageView.fitWidthProperty().bind(cityPanel.widthProperty());
        backgroundImageView.fitHeightProperty().bind(cityPanel.heightProperty());
    }

    public void initialize(){

        setCityBackground();
        alwaysSameLabels();
        onMineBuyRefresh();
        onWorkersChangeRefresh();
        refreshBuiltMilitaryObjects();
        Players.playCityMusic();
        fillMissingGraphics();
    }

    private void fillMissingGraphics(){
        miniCloseButton.setStyle(GraphicsStorage.begin+GraphicsStorage.minimize+GraphicsStorage.end);
        close1.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        close2.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        close3.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);

    }


    public void alwaysSameLabels(){
        setArmyLabels();
        setLabelsText();
        setLabelsMineManage();
        setLabelWorkersManage();
    }

    public void onMineBuyRefresh(){
        resGoldLabel2.setText("     " +Players.getActualPlayer().getPlayerResources().get(0).getAmount()); //gold
        resT11Label2.setText("     " + Players.getActualPlayer().getPlayerResources().get(1).getAmount());//T11
        resT12Label2.setText("     " + Players.getActualPlayer().getPlayerResources().get(2).getAmount());
        resT13Label2.setText("     " + Players.getActualPlayer().getPlayerResources().get(3).getAmount());
        resT21Label2.setText("     " + Players.getActualPlayer().getPlayerResources().get(4).getAmount());
        resT22Label2.setText("     " + Players.getActualPlayer().getPlayerResources().get(5).getAmount());
        resT3Label2.setText("     " +  Players.getActualPlayer().getPlayerResources().get(6).getAmount());

        resGoldLabel3.setText(Players.getActualPlayer().getHouses().getGoldPerTurn() + "/turn"); //gold
        resT11Label3.setText( Players.getActualPlayer().getPlayerMines().get(0).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT11() +  "/turn");//T11
        resT12Label3.setText( Players.getActualPlayer().getPlayerMines().get(1).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT12() + "/turn");
        resT13Label3.setText( Players.getActualPlayer().getPlayerMines().get(2).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT13() +  "/turn");
        resT21Label3.setText( Players.getActualPlayer().getPlayerMines().get(3).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT21() + "/turn");
        resT22Label3.setText( Players.getActualPlayer().getPlayerMines().get(4).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT22() +"/turn");
        resT3Label3.setText(  Players.getActualPlayer().getPlayerMines().get(5).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT3() + "/turn");
 
        resT11Label2manage.setText("   " + Players.getActualPlayer().getPlayerWorkers().getaT11());//T11
        resT12Label2manage.setText("   " + Players.getActualPlayer().getPlayerWorkers().getaT12());
        resT13Label2manage.setText("   " + Players.getActualPlayer().getPlayerWorkers().getaT13());
        resT21Label2manage.setText("   " + Players.getActualPlayer().getPlayerWorkers().getaT21());
        resT22Label2manage.setText("   " + Players.getActualPlayer().getPlayerWorkers().getaT22());
        resT3Label2manage.setText("   " +  Players.getActualPlayer().getPlayerWorkers().getaT3());

        refreshBuiltMines();
    }

    public void onWorkersChangeRefresh(){
        workHouseAmount.setText("   " + Players.getActualPlayer().getHouses().getHouseAmount() + "");
        workTotalWorkers.setText("   " + Players.getActualPlayer().getPlayerWorkers().getTotal() + "");
        workFreeWorkers.setText("   " +  Players.getActualPlayer().getPlayerWorkers().getFree() + "");
        workSetToMine.setText("   " +    Players.getActualPlayer().getPlayerWorkers().getFreeToMine() + "");
        workSetToGoldMine.setText("   " +    Players.getActualPlayer().getPlayerWorkers().getGoldMine() + "");
        resGoldLabel3.setText(Players.getActualPlayer().getHouses().getGoldPerTurn() + "/turn");
    }



    @FXML
    public void exitButton(){
        Stage primaryStage = (Stage) cityPanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mapPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openArmyScreen(){
        Stage primaryStage = (Stage) cityPanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("armyPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLabelsText(){
        resGoldLabel1.setText(Players.getActualPlayer().getPlayerResources().get(0).getName() + ":   " ); //gold
         resT11Label1.setText(Players.getActualPlayer().getPlayerResources().get(1).getName() + ":   " );//T11
         resT12Label1.setText(Players.getActualPlayer().getPlayerResources().get(2).getName() + ":   " );
         resT13Label1.setText(Players.getActualPlayer().getPlayerResources().get(3).getName() + ":   " );
         resT21Label1.setText(Players.getActualPlayer().getPlayerResources().get(4).getName() + ":   ");
         resT22Label1.setText(Players.getActualPlayer().getPlayerResources().get(5).getName() + ":   " );
          resT3Label1.setText(Players.getActualPlayer().getPlayerResources().get(6).getName() + ":   ");




        // TODO: 29.09.2020 add labels for units and defense
    }

    public void setLabelsMineManage(){
//        resGoldLabel1manage.setText(Player.getPlayerResources().get(0).getName() + ": " ); //gold
        resT11Label1manage.setText(Players.getActualPlayer().getPlayerResources().get(1).getName() + ": " );//T11
        resT12Label1manage.setText(Players.getActualPlayer().getPlayerResources().get(2).getName() + ": " );
        resT13Label1manage.setText(Players.getActualPlayer().getPlayerResources().get(3).getName() + ": " );
        resT21Label1manage.setText(Players.getActualPlayer().getPlayerResources().get(4).getName() + ": ");
        resT22Label1manage.setText(Players.getActualPlayer().getPlayerResources().get(5).getName() + ": " );
         resT3Label1manage.setText(Players.getActualPlayer().getPlayerResources().get(6).getName() + ": ");

//        resGoldLabel2manage.setText("   " + Player.getPlayerResources().get(0).getAmount()); //gold


        T11mineName.setText(Players.getActualPlayer().getPlayerMines().get(0).getName());
        T12mineName.setText(Players.getActualPlayer().getPlayerMines().get(1).getName());
        T13mineName.setText(Players.getActualPlayer().getPlayerMines().get(2).getName());
        T21mineName.setText(Players.getActualPlayer().getPlayerMines().get(3).getName());
        T22mineName.setText(Players.getActualPlayer().getPlayerMines().get(4).getName());
         T3mineName.setText(Players.getActualPlayer().getPlayerMines().get(5).getName());
    }

    public void setLabelWorkersManage(){
//        workName,workPrice4Next,workPrice,workHouseAmountName,workHouseAmount,workTotalWorkers,workFreeWorkers,workSetToMine
        workName.setText(Players.getActualPlayer().getHouses().getName());
        workPrice4Next.setText("Price for next " + Players.getActualPlayer().getHouses().getName());
//        workPrice.setText(Player.getHouses().getCost().toString());
        workHouseAmountName.setText(Players.getActualPlayer().getHouses().getName() + " amount");

    }

    public void refreshBuiltMines(){
        if (Players.getActualPlayer().getPlayerMines().get(0).isBuilt()){
            buyT11mine.setVisible(false);
            T11mineBought.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMines().get(1).isBuilt()){
            buyT12mine.setVisible(false);
            T12mineBought.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMines().get(2).isBuilt()){
            buyT13mine.setVisible(false);
            T13mineBought.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMines().get(3).isBuilt()){
            buyT21mine.setVisible(false);
            T21mineBought.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMines().get(4).isBuilt()){
            buyT22mine.setVisible(false);
            T22mineBought.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMines().get(5).isBuilt()){
            buyT3mine.setVisible(false);
            T3mineBought.setVisible(true);
        }
    }
    
    public void refreshBuiltMilitaryObjects(){
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(0).isBuilt()){
            unitT11.setVisible(true);
            armyBuildingT11.setVisible(false);
            unitT11Label1.setText( Players.getActualPlayer().getPlayersArmy().get(0).getName());
            unitT11Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(0).getAmount() );
            unitT11Label1.setVisible(true);
            unitT11Label2.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(1).isBuilt()){
            unitT12.setVisible(true);
            armyBuildingT12.setVisible(false);
            unitT12Label1.setText( Players.getActualPlayer().getPlayersArmy().get(1).getName());
            unitT12Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(1).getAmount() );
            unitT12Label1.setVisible(true);
            unitT12Label2.setVisible(true);

        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(2).isBuilt()){
            unitT21.setVisible(true);
            armyBuildingT21.setVisible(false);
            unitT21Label1.setText( Players.getActualPlayer().getPlayersArmy().get(2).getName());
            unitT21Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(2).getAmount() );
            unitT21Label1.setVisible(true);
            unitT21Label2.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(3).isBuilt()){
            unitT22.setVisible(true);
            armyBuildingT22.setVisible(false);
            unitT22Label1.setText( Players.getActualPlayer().getPlayersArmy().get(3).getName());
            unitT22Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(3).getAmount() );
            unitT22Label1.setVisible(true);
            unitT22Label2.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(4).isBuilt()){
            unitT23.setVisible(true);
            armyBuildingT23.setVisible(false);
            unitT23Label1.setText( Players.getActualPlayer().getPlayersArmy().get(4).getName());
            unitT23Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(4).getAmount() );
            unitT23Label1.setVisible(true);
            unitT23Label2.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(5).isBuilt()){
            unitT31.setVisible(true);
            armyBuildingT31.setVisible(false);
            unitT31Label1.setText( Players.getActualPlayer().getPlayersArmy().get(5).getName());
            unitT31Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(5).getAmount() );
            unitT31Label1.setVisible(true);
            unitT31Label2.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(6).isBuilt()){
            unitT32.setVisible(true);
            armyBuildingT32.setVisible(false);
            unitT32Label1.setText( Players.getActualPlayer().getPlayersArmy().get(6).getName());
            unitT32Label2.setText( "   " +  Players.getActualPlayer().getPlayersArmy().get(6).getAmount() );
            unitT32Label1.setVisible(true);
            unitT32Label2.setVisible(true);
        }
        if ( Players.getActualPlayer().getPlayerMilitaryBuildings().get(7).isBuilt()){
            unitT4.setVisible(true);
            armyBuildingT4.setVisible(false);
            unitT4Label1.setText( Players.getActualPlayer().getPlayersArmy().get(7).getName());
            unitT4Label2.setText(  "   " +  Players.getActualPlayer().getPlayersArmy().get(7).getAmount()  );
            unitT4Label1.setVisible(true);
            unitT4Label2.setVisible(true);
        }


    }

    public void setArmyLabels(){
        buildingArmyT11icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(0).getIcon()));
        buildingArmyT11name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(0).getName());
        buildingArmyT11descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(0).getDescription());
        buildingArmyT11priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(0).getCost().toString());
        buildingArmyT11unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(0).getStats());
        unitT11icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(0).getIcon()));
        unitT11icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(0).getFractionBackground()+GraphicsStorage.end);
        unitT11atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT11hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT11rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT11movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT11atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(0).getATK() + "");
        unitT11hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(0).getHP() + "");
        unitT11rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(0).getATKRange() + "");
        unitT11movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(0).getMovementRange() + "");
        unitT11name.setText( Players.getActualPlayer().getPlayersArmy().get(0).getName());
        unitT11type.setText( Players.getActualPlayer().getPlayersArmy().get(0).getType());

        buildingArmyT12icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(1).getIcon()));
        buildingArmyT12name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(1).getName());
        buildingArmyT12descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(1).getDescription());
        buildingArmyT12priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(1).getCost().toString());
        buildingArmyT12unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(1).getStats());
        unitT12icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(1).getIcon()));
        unitT12icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(1).getFractionBackground()+GraphicsStorage.end);
        unitT12atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT12hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT12rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT12movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT12atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(1).getATK() + "");
        unitT12hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(1).getHP() + "");
        unitT12rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(1).getATKRange() + "");
        unitT12movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(1).getMovementRange() + "");
        unitT12name.setText( Players.getActualPlayer().getPlayersArmy().get(1).getName());
        unitT12type.setText( Players.getActualPlayer().getPlayersArmy().get(1).getType());

        buildingArmyT21icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(2).getIcon()));
        buildingArmyT21name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(2).getName());
        buildingArmyT21descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(2).getDescription());
        buildingArmyT21priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(2).getCost().toString());
        buildingArmyT21unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(2).getStats());
        unitT21icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(2).getIcon()));
        unitT21icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(2).getFractionBackground()+GraphicsStorage.end);
        unitT21atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT21hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT21rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT21movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT21atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(2).getATK() + "");
        unitT21hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(2).getHP() + "");
        unitT21rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(2).getATKRange() + "");
        unitT21movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(2).getMovementRange() + "");
        unitT21name.setText( Players.getActualPlayer().getPlayersArmy().get(2).getName());
        unitT21type.setText( Players.getActualPlayer().getPlayersArmy().get(2).getType());

        buildingArmyT22icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(3).getIcon()));
        buildingArmyT22name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(3).getName());
        buildingArmyT22descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(3).getDescription());
        buildingArmyT22priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(3).getCost().toString());
        buildingArmyT22unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(3).getStats());
        unitT22icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(3).getIcon()));
        unitT22icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(3).getFractionBackground()+GraphicsStorage.end);
        unitT22atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT22hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT22rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT22movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT22atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(3).getATK() + "");
        unitT22hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(3).getHP() + "");
        unitT22rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(3).getATKRange() + "");
        unitT22movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(3).getMovementRange() + "");
        unitT22name.setText( Players.getActualPlayer().getPlayersArmy().get(3).getName());
        unitT22type.setText( Players.getActualPlayer().getPlayersArmy().get(3).getType());

        buildingArmyT23icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(4).getIcon()));
        buildingArmyT23name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(4).getName());
        buildingArmyT23descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(4).getDescription());
        buildingArmyT23priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(4).getCost().toString());
        buildingArmyT23unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(4).getStats());
        unitT23icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(4).getIcon()));
        unitT23icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(4).getFractionBackground()+GraphicsStorage.end);
        unitT23atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT23hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT23rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT23movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT23atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(4).getATK() + "");
        unitT23hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(4).getHP() + "");
        unitT23rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(4).getATKRange() + "");
        unitT23movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(4).getMovementRange() + "");
        unitT23name.setText( Players.getActualPlayer().getPlayersArmy().get(4).getName());
        unitT23type.setText( Players.getActualPlayer().getPlayersArmy().get(4).getType());

        buildingArmyT31icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(5).getIcon()));
        buildingArmyT31name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(5).getName());
        buildingArmyT31descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(5).getDescription());
        buildingArmyT31priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(5).getCost().toString());
        buildingArmyT31unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(5).getStats());
        unitT31icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(5).getIcon()));
        unitT31icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(5).getFractionBackground()+GraphicsStorage.end);
        unitT31atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT31hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT31rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT31movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT31atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(5).getATK() + "");
        unitT31hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(5).getHP() + "");
        unitT31rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(5).getATKRange() + "");
        unitT31movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(5).getMovementRange() + "");
        unitT31name.setText( Players.getActualPlayer().getPlayersArmy().get(5).getName());
        unitT31type.setText( Players.getActualPlayer().getPlayersArmy().get(5).getType());

        buildingArmyT32icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(6).getIcon()));
        buildingArmyT32name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(6).getName());
        buildingArmyT32descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(6).getDescription());
        buildingArmyT32priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(6).getCost().toString());
        buildingArmyT32unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(6).getStats());
        unitT32icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(6).getFractionBackground()+GraphicsStorage.end);
        unitT32icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(6).getIcon()));
        unitT32atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT32hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT32rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT32movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT32atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(6).getATK() + "");
        unitT32hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(6).getHP() + "");
        unitT32rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(6).getATKRange() + "");
        unitT32movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(6).getMovementRange() + "");
        unitT32name.setText( Players.getActualPlayer().getPlayersArmy().get(6).getName());
        unitT32type.setText( Players.getActualPlayer().getPlayersArmy().get(6).getType());

        buildingArmyT4icon.setGraphic(new ImageView(  Players.getActualPlayer().getPlayerMilitaryBuildings().get(7).getIcon()));
        buildingArmyT4name.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(7).getName());
        buildingArmyT4descriptionTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(7).getDescription());
        buildingArmyT4priceTooltip.setText( Players.getActualPlayer().getPlayerMilitaryBuildings().get(7).getCost().toString());
        buildingArmyT4unitTooltip.setText( Players.getActualPlayer().getPlayersArmy().get(7).getStats());
        unitT4icon.setStyle(GraphicsStorage.begin+ Players.getActualPlayer().getPlayersArmy().get(7).getFractionBackground()+GraphicsStorage.end);
        unitT4icon.setGraphic(new ImageView( Players.getActualPlayer().getPlayersArmy().get(7).getIcon()));
        unitT4atkStat.setGraphic(new ImageView(GraphicsStorage.atk));
        unitT4hpStat.setGraphic(new ImageView(GraphicsStorage.hp));
        unitT4rangeStat.setGraphic(new ImageView(GraphicsStorage.range));
        unitT4movementStat.setGraphic(new ImageView(GraphicsStorage.movement));
        unitT4atkStat.setText( Players.getActualPlayer().getPlayersArmy().get(7).getATK() + "");
        unitT4hpStat.setText( Players.getActualPlayer().getPlayersArmy().get(7).getHP() + "");
        unitT4rangeStat.setText( Players.getActualPlayer().getPlayersArmy().get(7).getATKRange() + "");
        unitT4movementStat.setText( Players.getActualPlayer().getPlayersArmy().get(7).getMovementRange() + "");
        unitT4name.setText( Players.getActualPlayer().getPlayersArmy().get(7).getName());
        unitT4type.setText( Players.getActualPlayer().getPlayersArmy().get(7).getType());
    }

    public void closeAll(){
        closeArmyBuildingsPanel();
        closeMineBuildingsPanel();
        closeFastRecruitPanel();
    }

    public void openMineBuildingsPanel(){
        closeAll();
        buildMinePanel.setVisible(true);
    }

    public void closeMineBuildingsPanel(){
        buildMinePanel.setVisible(false);
    }

    public void openArmyBuildingsPanel(){
        closeAll();
        buildArmyPanel.setVisible(true);
    }

    public void closeArmyBuildingsPanel(){
        buildArmyPanel.setVisible(false);
    }

    @FXML
    public void manageWorkers(ActionEvent e){
        if (e.getSource().equals(buyHouse)){
             Players.getActualPlayer().getHouses().buyHouse();
        } else if (e.getSource().equals(subMineWorker) &&  Players.getActualPlayer().getPlayerWorkers().getFreeToMine()>0){
             Players.getActualPlayer().getPlayerWorkers().subWorkerFromMine();
        } else if (e.getSource().equals(subArmyWorker) &&  Players.getActualPlayer().getPlayerWorkers().getGoldMine() >0){
             Players.getActualPlayer().getPlayerWorkers().subWorkerFromArmy();
        } else if (e.getSource().equals(addMineWorker) &&  Players.getActualPlayer().getPlayerWorkers().getFree()>0){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToMine();
        } else if (e.getSource().equals(addArmyWorker) &&  Players.getActualPlayer().getPlayerWorkers().getFree() >0){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToArmy();
        }

        onWorkersChangeRefresh();
    }

    @FXML
    public void manageMineWorkers(ActionEvent e){

        if (e.getSource().equals(add1) &&  Players.getActualPlayer().getPlayerMines().get(0).isBuilt() ){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToAT11();
        } else if (e.getSource().equals(add2) &&  Players.getActualPlayer().getPlayerMines().get(1).isBuilt() ){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToAT12();
        } else if (e.getSource().equals(add3) &&  Players.getActualPlayer().getPlayerMines().get(2).isBuilt() ){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToAT13();
        } else if (e.getSource().equals(add4) &&  Players.getActualPlayer().getPlayerMines().get(3).isBuilt() ){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToAT21();
        } else if (e.getSource().equals(add5) &&  Players.getActualPlayer().getPlayerMines().get(4).isBuilt() ){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToAT22();
        } else if (e.getSource().equals(add6) &&  Players.getActualPlayer().getPlayerMines().get(5).isBuilt() ){
             Players.getActualPlayer().getPlayerWorkers().addWorkerToAT3();
        }

        if ( Players.getActualPlayer().getPlayerWorkers().getaMine()>0){
            if (e.getSource().equals(sub1) &&  Players.getActualPlayer().getPlayerWorkers().getaT11()>0){
                 Players.getActualPlayer().getPlayerWorkers().subWorkerFromAT11();
            } else if (e.getSource().equals(sub2) &&  Players.getActualPlayer().getPlayerWorkers().getaT12()>0){
                 Players.getActualPlayer().getPlayerWorkers().subWorkerFromAT12();
            } else if (e.getSource().equals(sub3) &&  Players.getActualPlayer().getPlayerWorkers().getaT13()>0){
                 Players.getActualPlayer().getPlayerWorkers().subWorkerFromAT13();
            } else if (e.getSource().equals(sub4) &&  Players.getActualPlayer().getPlayerWorkers().getaT21()>0){
                 Players.getActualPlayer().getPlayerWorkers().subWorkerFromAT21();
            } else if (e.getSource().equals(sub5) &&  Players.getActualPlayer().getPlayerWorkers().getaT22()>0){
                 Players.getActualPlayer().getPlayerWorkers().subWorkerFromAT22();
            } else if (e.getSource().equals(sub6) &&  Players.getActualPlayer().getPlayerWorkers().getaT3()>0){
                 Players.getActualPlayer().getPlayerWorkers().subWorkerFromAT3();
            }
        }

        onMineBuyRefresh();
        onWorkersChangeRefresh();
    }

    @FXML 
    public void buyMine(ActionEvent e){

        if (e.getSource().equals(buyT11mine)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMines().get(0));
        } else if (e.getSource().equals(buyT12mine)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMines().get(1));
        } else if (e.getSource().equals(buyT13mine)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMines().get(2));
        } else if (e.getSource().equals(buyT21mine)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMines().get(3));
        } else if (e.getSource().equals(buyT22mine)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMines().get(4));
        } else if (e.getSource().equals(buyT3mine)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMines().get(5));
        }

        onMineBuyRefresh();
        
    }

    @FXML
    public void buyArmyBuilding(ActionEvent e){
        
        if (e.getSource().equals(buildingArmyT11buy)){
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(0));
        } else if (e.getSource().equals(buildingArmyT12buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(1));
        } else if (e.getSource().equals(buildingArmyT21buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(2));
        } else if (e.getSource().equals(buildingArmyT22buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(3));
        } else if (e.getSource().equals(buildingArmyT23buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(4));
        } else if (e.getSource().equals(buildingArmyT31buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(5));
        } else if (e.getSource().equals(buildingArmyT32buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(6));
        } else if (e.getSource().equals(buildingArmyT4buy)) {
            Building.buyBuilding( Players.getActualPlayer().getPlayerMilitaryBuildings().get(7));
        }

        refreshBuiltMilitaryObjects();
    }

//    @FXML
//    public void buyUnit(ActionEvent e){
//        if (e.getSource().equals(unitT11buy)){
//           Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(0));
//        } else if (e.getSource().equals(unitT12buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(1));
//        } else if (e.getSource().equals(unitT21buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(2));
//        } else if (e.getSource().equals(unitT22buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(3));
//        } else if (e.getSource().equals(unitT23buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(4));
//        } else if (e.getSource().equals(unitT31buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(5));
//        } else if (e.getSource().equals(unitT32buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(6));
//        } else if (e.getSource().equals(unitT4buy)) {
//            Unit.buyUnit( Players.getActualPlayer().getPlayersArmy().get(7));
//        }
//
//        refreshBuiltMilitaryObjects();
//
//
//    }

    @FXML
    public void openFastRecruitPanel() {
        if (firstOpenOfFastRecruit){
            fillMiniArrays();
            firstOpenOfFastRecruit=false;
            for (JFXButton miniBuy:miniButtons){
                miniBuy.setStyle(GraphicsStorage.begin+GraphicsStorage.plus+GraphicsStorage.end);
            }
        }
        miniArraysLabelsSet();
        closeAll();
        fastRecruitPanel.setVisible(true);
    }

    @FXML
    public void closeFastRecruitPanel() {
        fastRecruitPanel.setVisible(false);
    }


    public void miniBuyAmountActualize(MouseEvent e) {
        Slider slider;
        for (int i=0;i<miniSliders.size();i++){
            slider=miniSliders.get(i);
            if (e.getSource().equals(slider)){
                int buyAmount = (int) slider.getValue();
                miniLabelsList.get(i).get(4).setText(buyAmount+"");
            }
        }

    }

    public void miniBuyUnit(ActionEvent e) {
        for (int i=0;i<miniButtons.size();i++){
            if (e.getSource().equals(miniButtons.get(i))){
                int buyAmount = Integer.parseInt(miniLabelsList.get(i).get(4).getText());
                int successfullyBought=0;
                Unit unit;
                for (int j=0;j<buyAmount;j++){
                    unit = Players.getActualPlayer().getPlayersArmy().get(i);
                    if (Prices.canBuy(unit.getCost())){
                        Unit.buyUnit(unit);
                        successfullyBought++;
                    }
                }
                MilitaryBuilding mb = Players.getActualPlayer().getPlayerMilitaryBuildings().get(i);
                mb.setAmountAvailable(mb.getAmountAvailable()-successfullyBought);
            }
        }
        miniArraysLabelsSet();
        refreshBuiltMilitaryObjects();

    }
}
