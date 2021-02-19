package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public JFXCheckBox fullScreenCheckBox;
    @FXML
    public AnchorPane choseFractionPanel;
    @FXML
    public AnchorPane selectedFractionPanel;
    @FXML
    public Label fractionName;
    @FXML
    public Label fractionDescription;
    @FXML
    public AnchorPane numberOfPlayersBox,settingsPanel;
    @FXML
    public Label fractionTittle;
    @FXML
    public JFXButton select;
    @FXML
    public Slider volume;
    @FXML
    public AnchorPane mainMenuPanel;
    @FXML
    public Label jungle_label,atlantis_label,cyberpunk_label;
    @FXML
    public JFXButton cancel;
    @FXML
    public JFXButton load_button,exit_button,settings_close;
    @FXML
    ToggleButton toggleJungle,toggleAtlantis,toggleCyberpunk;
    @FXML
    private JFXButton settingsButton,onePlayer,twoPlayers,threePlayers;

    @FXML
    public  JFXButton newGameButton;

    static boolean isNewGameCreated = false;

    static int numberOfPlayers, selectedFraction,selectedPlayer;


    static  Players players;


    public void initialize(){
        if (isNewGameCreated){
            newGameButton.setDisable(true);
        }
        fillMissingGraphics();
    }

    private void fillMissingGraphics(){
        mainMenuPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.mainMenuBackground+GraphicsStorage.end);
        settingsPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        numberOfPlayersBox.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        choseFractionPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        selectedFractionPanel.setStyle(GraphicsStorage.begin+GraphicsStorage.settingsBackground+GraphicsStorage.end);
        onePlayer.setStyle(GraphicsStorage.begin+GraphicsStorage.numbButt+GraphicsStorage.end);
        twoPlayers.setStyle(GraphicsStorage.begin+GraphicsStorage.numbButt+GraphicsStorage.end);
        threePlayers.setStyle(GraphicsStorage.begin+GraphicsStorage.numbButt+GraphicsStorage.end);
        jungle_label.setStyle(GraphicsStorage.begin+GraphicsStorage.menuJng+GraphicsStorage.end);
        atlantis_label.setStyle(GraphicsStorage.begin+GraphicsStorage.menuAtl+GraphicsStorage.end);
        cyberpunk_label.setStyle(GraphicsStorage.begin+GraphicsStorage.menuCbr+GraphicsStorage.end);
        toggleJungle.setStyle(GraphicsStorage.begin+GraphicsStorage.toggleJng+GraphicsStorage.end);
        toggleAtlantis.setStyle(GraphicsStorage.begin+GraphicsStorage.toggleAtl+GraphicsStorage.end);
        toggleCyberpunk.setStyle(GraphicsStorage.begin+GraphicsStorage.toggleCbr+GraphicsStorage.end);
        cancel.setStyle(GraphicsStorage.begin+GraphicsStorage.close+GraphicsStorage.end);
        select.setStyle(GraphicsStorage.begin+GraphicsStorage.select+GraphicsStorage.end);
        newGameButton.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        load_button.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        settingsButton.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        exit_button.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);
        settings_close.setStyle(GraphicsStorage.begin+GraphicsStorage.mimiButton+GraphicsStorage.end);

    }

    @FXML
    public void exitButton() throws InterruptedException {
        Soundtracks.playSound(Soundtracks.clickSound);
        Thread.sleep(500);
        Platform.exit();
    }

    @FXML
    public void changeVolume(){
        double vol = volume.getValue();
        Soundtracks.changeVolume(vol);
    }

    @FXML
    public void fullScreenOn(){
        Soundtracks.playSound(Soundtracks.clickSound);

        ((Stage)settingsButton.getScene().getWindow()).setFullScreen(!((Stage) settingsButton.getScene().getWindow()).isFullScreen());
    }

    @FXML
    public void settingsOn(){
        Soundtracks.playSound(Soundtracks.clickSound);

        settingsPanel.setVisible(true);
    }

    @FXML
    public void settingsOff(){
        Soundtracks.playSound(Soundtracks.clickSound);

        settingsPanel.setVisible(false);
    }

    private void assignsStartingSectors(){
        switch (selectedPlayer){
            case 1:
                switch (Players.getActualPlayer().getFraction().getName()){
                    case "Jungle":
                        Sectors.getSectorByName("Sector11").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector11").setIsCityHere(true);
                        break;
                    case "Atlantis":
                        Sectors.getSectorByName("Sector75").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector75").setIsCityHere(true);
                        break;
                    case "Cyberpunk":
                        Sectors.getSectorByName("Sector28").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector28").setIsCityHere(true);
                        break;
                }
                break;
            case 2:
                switch (Players.getActualPlayer().getFraction().getName()){
                    case "Jungle":
                        Sectors.getSectorByName("Sector11").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector11").setIsCityHere(true);
                        break;
                    case "Atlantis":
                        Sectors.getSectorByName("Sector75").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector75").setIsCityHere(true);
                        break;
                    case "Cyberpunk":
                        Sectors.getSectorByName("Sector28").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector28").setIsCityHere(true);
                        break;
                }
                Players.setActualPlayer(2);
                switch (Players.getActualPlayer().getFraction().getName()){
                    case "Jungle":
                        Sectors.getSectorByName("Sector11").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector11").setIsCityHere(true);
                        break;
                    case "Atlantis":
                        Sectors.getSectorByName("Sector75").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector75").setIsCityHere(true);
                        break;
                    case "Cyberpunk":
                        Sectors.getSectorByName("Sector28").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector28").setIsCityHere(true);
                        break;
                }
                Players.setActualPlayer(1);
                break;
            case 3:
                switch (Players.getActualPlayer().getFraction().getName()){
                    case "Jungle":
                        Sectors.getSectorByName("Sector11").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector11").setIsCityHere(true);
                        break;
                    case "Atlantis":
                        Sectors.getSectorByName("Sector75").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector75").setIsCityHere(true);
                        break;
                    case "Cyberpunk":
                        Sectors.getSectorByName("Sector28").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector28").setIsCityHere(true);
                        break;
                }
                Players.setActualPlayer(2);
                switch (Players.getActualPlayer().getFraction().getName()){
                    case "Jungle":
                        Sectors.getSectorByName("Sector11").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector11").setIsCityHere(true);
                        break;
                    case "Atlantis":
                        Sectors.getSectorByName("Sector75").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector75").setIsCityHere(true);
                        break;
                    case "Cyberpunk":
                        Sectors.getSectorByName("Sector28").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector28").setIsCityHere(true);
                        break;
                }
                Players.setActualPlayer(3);
                switch (Players.getActualPlayer().getFraction().getName()){
                    case "Jungle":
                        Sectors.getSectorByName("Sector11").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector11").setIsCityHere(true);
                        break;
                    case "Atlantis":
                        Sectors.getSectorByName("Sector75").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector75").setIsCityHere(true);
                        break;
                    case "Cyberpunk":
                        Sectors.getSectorByName("Sector28").setOwner(Players.getActualPlayer());
                        Sectors.getSectorByName("Sector28").setIsCityHere(true);
                        break;
                }
                Players.setActualPlayer(1);
                break;
        }
    }

    public void startNewGame(){

        // TODO: 21.10.2020 add option to save actual game session

        selectedPlayer=1;
        Players.setActualPlayer(selectedPlayer);
        Sectors sectors = new Sectors();
        for (Sector sector:Sectors.sectorsList){
            sector.fillSectorOwner();
        }
        for (int i=1;i<=numberOfPlayers;i++){
            selectedPlayer=i;
            assignsStartingSectors();
        }
        selectedPlayer=1;
        sectors.correctSectorsArmies();

        // TODO: 21.10.2020 TEST POSITION
        TestingMethods.test1();

        Stage primaryStage = (Stage) settingsButton.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mapPanel.fxml"));
            isNewGameCreated=true;
            primaryStage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hideStartNewGamePanel(){
        Soundtracks.playSound(Soundtracks.clickSound);
        choseFractionPanel.setVisible(false);
        selectedFractionPanel.setVisible(false);

    }

    @FXML
    public void showStartNewGamePanel(){
        Soundtracks.playSound(Soundtracks.clickSound);
        numberOfPlayersBox.setVisible(true);
    }


    @FXML
    public void selectJungle(){
        Soundtracks.playSound(Soundtracks.clickSound);

        fractionName.setText("Jungle");
        fractionDescription.setText(Players.jungleFraction.getFractionDescription());
        selectedFraction=1;
    }

    @FXML
    public void selectAtlantis(){
        Soundtracks.playSound(Soundtracks.clickSound);
        fractionName.setText("Atlantis");
        fractionDescription.setText(Players.atlantisFraction.getFractionDescription());
        selectedFraction=2;
    }

    @FXML
    public void selectCyberpunk(){
        Soundtracks.playSound(Soundtracks.clickSound);
        fractionName.setText("Cyberpunk");
        fractionDescription.setText(Players.cyberpunkFraction.getFractionDescription());
        selectedFraction=3;
    }

    @FXML
    public void loadGame() {
        Soundtracks.playSound(Soundtracks.clickSound);
        if (isNewGameCreated){
            Stage primaryStage = (Stage) selectedFractionPanel.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("mapPanel.fxml"));
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void selectPlayer(ActionEvent e){
        if (e.getSource().equals(select)){
            Soundtracks.playSound(Soundtracks.clickSound);
            Players.selectPlayerFraction(selectedPlayer,selectedFraction);
        }

        switch (selectedFraction){
            case 1:
                toggleJungle.setDisable(true);
                if (toggleAtlantis.isDisabled()){
                    toggleCyberpunk.setSelected(true);
                    selectCyberpunk();
                } else {
                    toggleAtlantis.setSelected(true);
                    selectAtlantis();
                }
                break;
            case 2:
                toggleAtlantis.setDisable(true);
                if (toggleJungle.isDisabled()){
                    toggleCyberpunk.setSelected(true);
                    selectCyberpunk();
                } else {
                    toggleJungle.setSelected(true);
                    selectJungle();
                }
                break;
            case 3:
                toggleCyberpunk.setDisable(true);
                if (toggleAtlantis.isDisabled()){
                    toggleJungle.setSelected(true);
                    selectJungle();
                } else {
                    toggleAtlantis.setSelected(true);
                    selectAtlantis();
                }
                break;
        }

        if (selectedPlayer==numberOfPlayers){
            startNewGame();
        }
        selectedPlayer++;
        fractionTittle.setText("Choose Player " + selectedPlayer + " Fraction:");

    }

    @FXML
    public void gameType(ActionEvent e){
        if (e.getSource().equals(onePlayer)){
            Soundtracks.playSound(Soundtracks.clickSound);
            numberOfPlayersBox.setVisible(false);
            numberOfPlayers=1;
        } else if (e.getSource().equals(twoPlayers)){
            Soundtracks.playSound(Soundtracks.clickSound);
            numberOfPlayersBox.setVisible(false);
            numberOfPlayers=2;
        } else if (e.getSource().equals(threePlayers)){
            Soundtracks.playSound(Soundtracks.clickSound);
            numberOfPlayersBox.setVisible(false);
            numberOfPlayers=3;
        }
        players = new Players(numberOfPlayers);
        choseFractionPanel.setVisible(true);
        selectedFractionPanel.setVisible(true);
        selectedPlayer=1;

        fractionTittle.setText("Choose Player " + selectedPlayer + " Fraction:");

    }

}
