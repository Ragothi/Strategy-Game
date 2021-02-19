package sample;

//<editor-fold desc="Imports">

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
//</editor-fold>

public class mapPanelController {

    //<editor-fold desc="Declarations">
    @FXML
    public Slider volume;
    @FXML
    public HBox row1,row2,row3,row4,row5,row6,row7;
    private static boolean firstEncounter = true;

    @FXML
    public AnchorPane newGamePanel;
    @FXML
    public AnchorPane settingsPanel;
    @FXML
    public JFXCheckBox fullScreenCheckBox;
    @FXML
    public JFXCheckBox tooltipsOn;
    @FXML
    public AnchorPane sectorPanel;
    @FXML
    public Label sectorDescriptionLabel;
    @FXML
    public Label sectorTitleLabel;
    @FXML
    public ImageView backgroundImageView;
    @FXML
    public Label dateLabel;
    @FXML
    public JFXButton exit_button;
    @FXML
    private  JFXButton b11,b12,b13,b14,b15,b16,b17,b18,b19,b21,b22,b23,b24,b25,b26,b27,b28,b29,b33,b32,b31,b34,
            b35,b36,b37,b38,b39,b41,b42,b43,b44,b45,b46,b47,b48,b49,b51,b52,b53,b54,b55,b56,b57,
            b58,b59,b61,b62,b63,b64,b65,b66,b67,b68,b69,b71,b72,b73,b74,b75,b76,b77,b78,b79,attackSectorButton;

    public static Sector attackedSector;

    private final ArrayList<JFXButton> buts = new ArrayList<>();
    private Sector[][] sectorMatrix = new Sector[7][9];

    private  boolean firstRun = true;
    //</editor-fold>

    public void initialize(){
        if (firstRun){
            fillArrayList();
            fillMissingGraphics();
            fillButMatrix();
            addNeighbours();
        }
        String bckImageURL = Players.getActualPlayer().getFraction().getBackgroundImageURL();
        Image img = new Image(bckImageURL);
        backgroundImageView.setImage(img);
        backgroundImageView.fitWidthProperty().bind(newGamePanel.widthProperty());
        backgroundImageView.fitHeightProperty().bind(newGamePanel.heightProperty());
        displayDate();
        Players.playMapMusic();
        firstRunButtonsConfig();
        volumeSliderPosition();
    }

    private void fillMissingGraphics(){
        exit_button.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        dateLabel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        settingsPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        sectorPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);

    }

    private void volumeSliderPosition(){
        volume.setValue(Soundtracks.musicVolume);
    }

    private void firstRunButtonsConfig(){
        if (firstRun){
            firstRun=false;
            for (int i=0;i< buts.size();i++){
                JFXButton but = buts.get(i);
                but.setPrefSize(130,150);
                but.setMinSize(but.getPrefWidth(),but.getPrefHeight());
                but.setMaxSize(but.getPrefWidth(),but.getPrefHeight());
                but.setPickOnBounds(false);
                String sectorOwnerName = Sectors.sectorsList.get(i).getOwner().getName();
                for (FractionGameData player:Players.getHumanPlayers()){
                    if (sectorOwnerName.equals(player.getName()) && Sectors.sectorsList.get(i).isCityHere()){
                        switch (Sectors.sectorsList.get(i).getOwner().getFraction().getName()){
                            case "Jungle":
                                buts.get(i).setGraphic(new ImageView(GraphicsStorage.mapCityJng));
                                Sectors.sectorsList.get(i).setImageURL(GraphicsStorage.mapBackPlayerJng);
                                buts.get(i).setStyle(GraphicsStorage.begin+GraphicsStorage.mapBackPlayerJng+GraphicsStorage.end);
                                break;
                            case "Atlantis":
                                buts.get(i).setGraphic(new ImageView(GraphicsStorage.mapCityAtl));
                                Sectors.sectorsList.get(i).setImageURL(GraphicsStorage.mapBackPlayerAtl);
                                buts.get(i).setStyle(GraphicsStorage.begin+GraphicsStorage.mapBackPlayerAtl+GraphicsStorage.end);
                                break;
                            case "Cyberpunk":
                                buts.get(i).setGraphic(new ImageView(GraphicsStorage.mapCityCbr));
                                Sectors.sectorsList.get(i).setImageURL(GraphicsStorage.mapBackPlayerCbr);
                                buts.get(i).setStyle(GraphicsStorage.begin+GraphicsStorage.mapBackPlayerCbr+GraphicsStorage.end);
                                break;
                        }

                    } else if (sectorOwnerName.equals(player.getName()) && !Sectors.sectorsList.get(i).isCityHere()){
                        switch (Sectors.sectorsList.get(i).getOwner().getFraction().getName()){
                            case "Jungle":
                                Sectors.sectorsList.get(i).setImageURL(GraphicsStorage.mapBackPlayerJng);
                                buts.get(i).setStyle(GraphicsStorage.begin+GraphicsStorage.mapBackPlayerJng+GraphicsStorage.end);
                                break;
                            case "Atlantis":
                                Sectors.sectorsList.get(i).setImageURL(GraphicsStorage.mapBackPlayerAtl);
                                buts.get(i).setStyle(GraphicsStorage.begin+GraphicsStorage.mapBackPlayerAtl+GraphicsStorage.end);
                                break;
                            case "Cyberpunk":
                                Sectors.sectorsList.get(i).setImageURL(GraphicsStorage.mapBackPlayerCbr);
                                buts.get(i).setStyle(GraphicsStorage.begin+GraphicsStorage.mapBackPlayerCbr+GraphicsStorage.end);
                                break;
                        }
                    }
                    if (!sectorOwnerName.equals(player.getName())) {
                        buts.get(i).setStyle("-fx-background-image: url(" + Sectors.sectorsList.get(i).getImageURL() + ");" +
                                " -fx-effect: dropshadow(three-pass-box, black, 10,0,0,0);" +
                                " -fx-background-repeat: stretch;" +
                                " -fx-background-size: 100% 100%;" +
                                " -fx-background-position: center center;");

                    }
                }
            }
        }
    }

    private void fillButMatrix(){
        int counter =0;
        for (int i=0;i<7;i++){
            for (int j=0;j<9;j++){
                sectorMatrix[i][j]=Sectors.sectorsList.get(counter);
                counter++;
            }
        }
    }

    private void addNeighbours(){
       Sector scan;
       int row;

       for (Sector sector:Sectors.sectorsList){
           for (int i=0;i<7;i++) {
               for (int j = 0; j < 9; j++) {
                   scan = sectorMatrix[i][j];
                   if (i==0 || i==2 || i==4 || i==6){
                       row=0;
                   } else {
                       row=1;
                   }
                   if (row==0){
                       if (sector.getName().equals(scan.getName())){
                           if (i>0 && j>0 && sectorMatrix[i-1][j-1]!=null  ){
                               sector.getNeighbours().add(sectorMatrix[i-1][j-1]);
                           }
                           if (i>0 && sectorMatrix[i-1][j]!=null){
                               sector.getNeighbours().add(sectorMatrix[i-1][j]);
                           }
                           if (j>0 && sectorMatrix[i][j-1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i][j-1]);
                           }
                           if (j<8 &&sectorMatrix[i][j+1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i][j+1]);
                           }
                           if (i<6 && j>0 &&sectorMatrix[i+1][j-1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i+1][j-1]);
                           }
                           if (i<6 && sectorMatrix[i+1][j]!=null){
                               sector.getNeighbours().add(sectorMatrix[i+1][j]);
                           }
                       }
                   } else if (row==1){
                       if (sector.getName().equals(scan.getName())){
                           if (i>0  && sectorMatrix[i-1][j]!=null  ){
                               sector.getNeighbours().add(sectorMatrix[i-1][j]);
                           }
                           if (i>0 && j<8 && sectorMatrix[i-1][j+1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i-1][j+1]);
                           }
                           if (j>0 && sectorMatrix[i][j-1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i][j-1]);
                           }
                           if (j<8 &&sectorMatrix[i][j+1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i][j+1]);
                           }
                           if (i<6 && j<8 &&sectorMatrix[i+1][j+1]!=null){
                               sector.getNeighbours().add(sectorMatrix[i+1][j+1]);
                           }
                           if (i<6 && sectorMatrix[i+1][j]!=null){
                               sector.getNeighbours().add(sectorMatrix[i+1][j]);
                           }
                       }
                   }
               }
           }
       }
    }

    @FXML
    public void changeVolume(){
        double vol = volume.getValue();
        Soundtracks.changeVolume(vol);
    }

    public void setActualPlayer(int i){
        MainMenuController.selectedPlayer=i;
        Players.setActualPlayer(i);
    }

    @FXML
    public void hideToolTips(){
        if (b11.getTooltip().getShowDuration() != Duration.ZERO){
            for (int i=0; i<63;i++){
                buts.get(i).getTooltip().setShowDuration(Duration.ZERO);
            }
        } else {
            for (int i=0; i<63;i++){
                buts.get(i).getTooltip().setShowDuration(Duration.millis(5000));
            }
        }


    }

    public void fillArrayList(){
        buts.add(b11);
        buts.add(b12);
        buts.add(b13);
        buts.add(b14);
        buts.add(b15);
        buts.add(b16);
        buts.add(b17);
        buts.add(b18);
        buts.add(b19);
        buts.add(b21);
        buts.add(b22);
        buts.add(b23);
        buts.add(b24);
        buts.add(b25);
        buts.add(b26);
        buts.add(b27);
        buts.add(b28);
        buts.add(b29);
        buts.add(b31);
        buts.add(b32);
        buts.add(b33);
        buts.add(b34);
        buts.add(b35);
        buts.add(b36);
        buts.add(b37);
        buts.add(b38);
        buts.add(b39);
        buts.add(b41);
        buts.add(b42);
        buts.add(b43);
        buts.add(b44);
        buts.add(b45);
        buts.add(b46);
        buts.add(b47);
        buts.add(b48);
        buts.add(b49);
        buts.add(b51);
        buts.add(b52);
        buts.add(b53);
        buts.add(b54);
        buts.add(b55);
        buts.add(b56);
        buts.add(b57);
        buts.add(b58);
        buts.add(b59);
        buts.add(b61);
        buts.add(b62);
        buts.add(b63);
        buts.add(b64);
        buts.add(b65);
        buts.add(b66);
        buts.add(b67);
        buts.add(b68);
        buts.add(b69);
        buts.add(b71);
        buts.add(b72);
        buts.add(b73);
        buts.add(b74);
        buts.add(b75);
        buts.add(b76);
        buts.add(b77);
        buts.add(b78);
        buts.add(b79);
    }

    @FXML
    public void sectorPanelOff(){
        sectorPanel.setVisible(false);
    }

    @FXML
    public void sectorPanelOn(){
        sectorPanel.setVisible(true);
    }

    @FXML
    public void exitButton(){
        Platform.exit();
    }

    @FXML
    public void fullScreenOn(){
        if (((Stage)b11.getScene().getWindow()).isFullScreen()){
            ((Stage)b11.getScene().getWindow()).setFullScreen(false);
        } else{
            ((Stage)b11.getScene().getWindow()).setFullScreen(true);
        }
    }

    @FXML
    public void settingsOn(){
        settingsPanel.setVisible(true);
    }

    @FXML
    public void settingsOff(){
        settingsPanel.setVisible(false);
    }

    @FXML
    public void openSectorPanel(ActionEvent e){

        for (int i=0; i<buts.size();i++){
            if (e.getSource().equals(buts.get(i))) {

                sectorPanelOn();
                // TODO: 13.10.2020 add sector description, fill army, manage owner
                attackSectorButton.setVisible(false);
                sectorTitleLabel.setText(Sectors.sectorsList.get(i).getName());
                sectorDescriptionLabel.setText(Sectors.sectorsList.get(i).getDescription() + "\n"
                + Sectors.sectorsList.get(i).getDescriptionOfArmySize());
                Sectors.setSelectedSector(Sectors.sectorsList.get(i));
                attackedSector=Sectors.sectorsList.get(i);
                attackedSector.setAttackSectorButton(attackSectorButton);
                for (int k=0;k<attackedSector.getNeighbours().size();k++){
                    if (attackedSector.getNeighbours().get(k).getOwner().getName().equals(Players.getActualPlayer().getName())) {
                        attackedSector.getAttackSectorButton().setVisible(true);
                    }
                }
                if (attackedSector.getOwner().getName().equals(Players.getActualPlayer().getName())){
                    attackedSector.getAttackSectorButton().setVisible(false);
                }

            }
        }
    }

    @FXML
    public void openCityPanel(){

        Stage primaryStage = (Stage) newGamePanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CityPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openArmyScreen(){
        Stage primaryStage = (Stage) newGamePanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("armyPanel.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void MainMenu(){
        Stage primaryStage = (Stage) newGamePanel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayDate(){
        dateLabel.setText("     D: " + DateSystem.day + "  W: " + DateSystem.week + "  M: " + DateSystem.month);
    }

    public void changeDate(){
        if (firstEncounter){
            setActualPlayer(1);
            firstEncounter=false;
        }

    boolean roundEnd = false;

        Players.getActualPlayer().getPlayerResources().get(0).addAmount(Players.getActualPlayer().getHouses().getGoldPerTurn());
        Players.getActualPlayer().getPlayerResources().get(1).addAmount(Players.getActualPlayer().getPlayerMines().get(0).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT11());
        Players.getActualPlayer().getPlayerResources().get(2).addAmount(Players.getActualPlayer().getPlayerMines().get(1).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT12());
        Players.getActualPlayer().getPlayerResources().get(3).addAmount(Players.getActualPlayer().getPlayerMines().get(2).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT13());
        Players.getActualPlayer().getPlayerResources().get(4).addAmount(Players.getActualPlayer().getPlayerMines().get(3).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT21());
        Players.getActualPlayer().getPlayerResources().get(5).addAmount(Players.getActualPlayer().getPlayerMines().get(4).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT22());
        Players.getActualPlayer().getPlayerResources().get(6).addAmount(Players.getActualPlayer().getPlayerMines().get(5).getAmountPerTurn()*Players.getActualPlayer().getPlayerWorkers().getaT3());

        if (MainMenuController.selectedPlayer== MainMenuController.numberOfPlayers){
            if (DateSystem.day<7){
                DateSystem.day++;
            } else if (DateSystem.day==7 && DateSystem.week<4){
                DateSystem.day=1;
                DateSystem.week++;
                increaseAvailableUnits();
            } else if (DateSystem.day==7 && DateSystem.week==4){
                DateSystem.day=1;
                DateSystem.week=1;
                DateSystem.month++;
                increaseAvailableUnits();
            }
            displayDate();
            if (DateSystem.month==12){
                dateLabel.setText("You broke the Calendar!");
            }
            setActualPlayer(1);
            roundEnd=true;
        }

        if (!roundEnd){
            MainMenuController.selectedPlayer++;
            setActualPlayer(MainMenuController.selectedPlayer);
        }
        initialize();
    }

    private void increaseAvailableUnits() {
        for (FractionGameData player:Players.getHumanPlayers()){
            for (MilitaryBuilding mb:player.getPlayerMilitaryBuildings()){
                if (mb.isBuilt()){
                    mb.increaseAmountAvailable();
                }
            }
        }
    }

    public void attackSector(ActionEvent actionEvent) {
        int counter=0;
        for (Unit unit:Players.getActualPlayer().getPlayersArmy()){
            if (unit.getAmount()>0){
                counter++;
            }
        }
        if (counter >0){
            Stage primaryStage = (Stage) newGamePanel.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("hexBattlefield.fxml"));
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
