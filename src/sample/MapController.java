package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MapController {
    @FXML
    public Slider volume;
    private static boolean firstEncounter = true;
    private boolean isSectorPanelOpen =false;
    @FXML
    public AnchorPane newGamePanel,settingsPanel,sectorPanel;
    @FXML
    public JFXCheckBox fullScreenCheckBox,tooltipsOn;
    @FXML
    public Label sectorTitleLabel,dateLabel,sectorDescriptionLabel;
    @FXML
    public ImageView backgroundImageView;
    @FXML
    public JFXButton exit_button,attackSectorButton,sectorClose;
    public static Sector attackedSector;
    private final ArrayList<ImageView> buts = new ArrayList<>();
    private Sector[][] sectorMatrix = new Sector[7][9];
    private  boolean firstRun = true;
    private boolean isSettingOn=false;


    public void initialize(){
        if (firstRun){
            fillMissingGraphics();
            fillButMatrix();
            addNeighbours();
            firstRun=false;
        }
        String bckImageURL = Players.getActualPlayer().getFraction().getBackgroundImageURL();
        Image img = new Image(bckImageURL);
        backgroundImageView.setImage(img);
        backgroundImageView.fitWidthProperty().bind(newGamePanel.widthProperty());
        backgroundImageView.fitHeightProperty().bind(newGamePanel.heightProperty());
        displayDate();
        Players.playMapMusic();
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

    private void fillButMatrix(){
        int counter =0;
        for (int i=0;i<7;i++){
            for (int j=0;j<9;j++){
                Sector sector=Sectors.sectorsList.get(counter);
                sectorMatrix[i][j]=sector;

                for (FractionGameData player:Players.getHumanPlayers()){
                    if (sector.getOwner().getName().equals(player.getName()) && sector.isCityHere()){
                        switch (sector.getOwner().getFraction().getName()){
                            case "Jungle":
                                sector.setItemOnMap(new ImageView(GraphicsStorage.mapCityJng));
                                sector.getMapButton().setFill(ColorsStorage.jungle);
                                break;
                            case "Atlantis":
                                sector.setItemOnMap(new ImageView(GraphicsStorage.mapCityAtl));
                                sector.getMapButton().setFill(ColorsStorage.atlantis);
                                break;
                            case "Cyberpunk":
                                sector.setItemOnMap(new ImageView(GraphicsStorage.mapCityCbr));
                                sector.getMapButton().setFill(ColorsStorage.cyberpunk);
                                break;
                        }
                    } else if (sector.getOwner().getName().equals(player.getName())){
                        switch (sector.getOwner().getFraction().getName()){
                            case "Jungle":
                                sector.getMapButton().setFill(ColorsStorage.jungle);
                                break;
                            case "Atlantis":
                                sector.getMapButton().setFill(ColorsStorage.atlantis);
                                break;
                            case "Cyberpunk":
                                sector.getMapButton().setFill(ColorsStorage.cyberpunk);
                                break;
                        }
                    }
                }
                newGamePanel.getChildren().add(sector.getMapButton());
                AnchorPane.setTopAnchor(sector.getMapButton(),30.0+i*112.5);
                if (i==0 || i==2 || i==4 || i==6){
                    AnchorPane.setLeftAnchor(sector.getMapButton(),30.0+j*130);
                } else {
                    AnchorPane.setLeftAnchor(sector.getMapButton(),95.0+j*130);
                }
                sector.getMapButton().toFront();
                sector.getMapButton().setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!isSectorPanelOpen && !isSettingOn){
                            sector.getMapButton().toFront();
                            if (sector.getItemOnMap()!=null){
                                sector.getItemOnMap().toFront();
                            }
                            sector.getMapButton().setStroke(ColorsStorage.highlight);
                        }
                    }
                });
                sector.getMapButton().setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!isSectorPanelOpen && !isSettingOn) {
                            sector.getMapButton().setStroke(ColorsStorage.black);
                        }
                    }
                });
                sector.getMapButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (!isSettingOn && !isSectorPanelOpen){
                            openSectorPanel(sector);
                        }
                    }
                });

                if (sector.getItemOnMap()!=null){
                    newGamePanel.getChildren().add(sector.getItemOnMap());
                    AnchorPane.setTopAnchor(sector.getItemOnMap(),35.0+i*112.5);
                    if (i==0 || i==2 || i==4 || i==6){
                        AnchorPane.setLeftAnchor(sector.getItemOnMap(),35.0+j*130);
                    } else {
                        AnchorPane.setLeftAnchor(sector.getItemOnMap(),95.0+j*130);
                    }
                    sector.getItemOnMap().toFront();
                    sector.getItemOnMap().setOnMouseClicked(sector.getMapButton().getOnMouseClicked());
                    sector.getItemOnMap().setOnMouseExited(sector.getMapButton().getOnMouseExited());
                    sector.getItemOnMap().setOnMouseEntered(sector.getMapButton().getOnMouseEntered());

                }
                counter++;
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
    public void sectorPanelOff(){
        sectorPanel.setVisible(false);
        isSectorPanelOpen=false;
    }

    @FXML
    public void exitButton(){
        Platform.exit();
    }

    @FXML
    public void fullScreenOn(){
        if (((Stage)exit_button.getScene().getWindow()).isFullScreen()){
            ((Stage)exit_button.getScene().getWindow()).setFullScreen(false);
        } else{
            ((Stage)exit_button.getScene().getWindow()).setFullScreen(true);
        }
    }

    @FXML
    public void settingsOn(){
        sectorPanelOff();
        settingsPanel.setVisible(true);
        settingsPanel.toFront();
        isSettingOn=true;
    }

    @FXML
    public void settingsOff(){
        settingsPanel.setVisible(false);
        isSettingOn=false;
    }

    @FXML
    public void openSectorPanel(Sector sector){
        sector.getMapButton().setStroke(ColorsStorage.black);
        settingsOff();
        isSectorPanelOpen=true;
        sectorPanel.setVisible(true);
        sectorPanel.toFront();
        // TODO: 13.10.2020 add sector description, fill army, manage owner
        attackSectorButton.setVisible(false);
        sectorTitleLabel.setText(sector.getName());
        sectorDescriptionLabel.setText(sector.getDescription() + "\n"
                + sector.getDescriptionOfArmySize());
        Sectors.setSelectedSector(sector);
        attackedSector=sector;
        attackedSector.setAttackSectorButton(attackSectorButton);
        for (int k=0;k<attackedSector.getNeighbours().size();k++){
            if (attackedSector.getNeighbours().get(k).getOwner().getName().equals(Players.getActualPlayer().getName())) {
                attackedSector.getAttackSectorButton().setVisible(true);
            }
        }
        if (attackedSector.getOwner().getName().equals(Players.getActualPlayer().getName())){
            attackedSector.getAttackSectorButton().setVisible(false);
        }
        attackSectorButton.toFront();
        sectorClose.toFront();

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
        dateLabel.setText(" D:" + DateSystem.day + " W:" + DateSystem.week + " M:" + DateSystem.month);
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
