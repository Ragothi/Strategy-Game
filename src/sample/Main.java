package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("Tree Games");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
//        primaryStage.setMaximized(true);
        primaryStage.show();
        Soundtracks.playMusic(Soundtracks.mainMenuMusic);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
