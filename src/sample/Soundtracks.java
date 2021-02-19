package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Soundtracks {



    //<editor-fold desc="Sounds">
    private static final String mainMenu = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\main_menu.mp3";
    private static final String cityJng = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\city_jng.mp3";
    private static final String cityAtl = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\city_atl.mp3";
    private static final String cityCbr = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\city_cbr.mp3";
    private static final String combatJng = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\combat_jng.mp3";
    private static final String combatAtl = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\combat_atl.mp3";
    private static final String combatCbr = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\combat_cbr.mp3";
    private static final String mapJng = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\map_jng.mp3";
    private static final String mapAtl = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\map_atl.mp3";
    private static final String mapCbr = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\map_cbr.mp3";
    private static final String victory = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\victory.mp3";
    private static final String archer = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\archer.mp3";
    private static final String click = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\click.wav";
    private static final String sword = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\sword.mp3";
    private static final String lose = "E:\\Studia\\java\\TIM\\JavaFX\\JFXButtonTest\\sounds\\lose.mp3";


    private static final Media mainMenuSong =  new Media(Paths.get(mainMenu).toUri().toString());
    private static final Media cityJngSong =  new  Media(Paths.get(cityJng  ).toUri().toString());
    private static final Media cityAtlSong =  new  Media(Paths.get(cityAtl  ).toUri().toString());
    private static final Media cityCbrSong =  new  Media(Paths.get(cityCbr  ).toUri().toString());
    private static final Media combatJngSong = new Media(Paths.get(combatJng).toUri().toString());
    private static final Media combatAtlSong = new Media(Paths.get(combatAtl).toUri().toString());
    private static final Media combatCbrSong = new Media(Paths.get(combatCbr).toUri().toString());
    private static final Media mapJngSong  =   new Media(Paths.get(mapJng   ).toUri().toString());
    private static final Media mapAtlSong  =   new Media(Paths.get(mapAtl   ).toUri().toString());
    private static final Media mapCbrSong  =   new Media(Paths.get(mapCbr   ).toUri().toString());
    private static final Media victorySong  =   new Media(Paths.get(victory   ).toUri().toString());
    private static final Media clickSong  =   new Media(Paths.get(click   ).toUri().toString());
    private static final Media archerSong  =   new Media(Paths.get(archer   ).toUri().toString());
    private static final Media swordSong  =   new Media(Paths.get(sword   ).toUri().toString());
    private static final Media loseSong  =   new Media(Paths.get(lose   ).toUri().toString());


    public static final MediaPlayer mainMenuMusic  =   new MediaPlayer(mainMenuSong);
    public static final MediaPlayer cityJngMusic   =   new MediaPlayer(cityJngSong);
    public static final MediaPlayer cityAtlMusic   =   new MediaPlayer(cityAtlSong);
    public static final MediaPlayer cityCbrMusic   =   new MediaPlayer(cityCbrSong);
    public static final MediaPlayer combatJngMusic =   new MediaPlayer(combatJngSong);
    public static final MediaPlayer combatAtlMusic =   new MediaPlayer(combatAtlSong);
    public static final MediaPlayer combatCbrMusic =   new MediaPlayer(combatCbrSong);
    public static final MediaPlayer mapJngMusic    =    new MediaPlayer(mapJngSong);
    public static final MediaPlayer mapAtlMusic    =    new MediaPlayer(mapAtlSong);
    public static final MediaPlayer mapCbrMusic    =    new MediaPlayer(mapCbrSong);
    public static final MediaPlayer victoryMusic    =    new MediaPlayer(victorySong);
    public static final MediaPlayer clickSound    =    new MediaPlayer(clickSong);
    public static final MediaPlayer archerSound    =    new MediaPlayer(archerSong);
    public static final MediaPlayer swordSound    =    new MediaPlayer(swordSong);
    public static final MediaPlayer loseMusic    =    new MediaPlayer(loseSong);


    //</editor-fold>

    private static boolean playSound=true;
    public static double soundVolume=0.5;
    public static double musicVolume=0.2;
    private static MediaPlayer actualSong = mainMenuMusic;
    public static MediaPlayer actualSound = clickSound;


    public static void playMusic(MediaPlayer song){
        stopMusic();
        actualSong=song;
        actualSong.setOnReady(new Runnable() {
            @Override
            public void run() {
                try {
                    actualSong.setStopTime(Duration.seconds(actualSong.getMedia().getDuration().toSeconds()));

                } finally {
                    //dont care
                }
            }
        });
        if (actualSong!=victoryMusic){
            actualSong.setCycleCount(MediaPlayer.INDEFINITE);
        } else {
            actualSong.setCycleCount(1);
        }
        actualSong.setVolume(musicVolume);
        actualSong.setStartTime(Duration.seconds(0));
        actualSong.setAutoPlay(true);
        actualSong.play();
    }

    public static void playSound(MediaPlayer sound){
        if (playSound){
            actualSound=sound;
            sound.setVolume(soundVolume);
            sound.setCycleCount(1);
            sound.seek(Duration.ZERO);
            sound.setAutoPlay(true);
            sound.play();
        }
    }

    public static void playSounds (Boolean playSounds ){
        playSound=playSounds;
    }

    private static void stopMusic(){
        actualSong.stop();
    }

    public static void changeVolume(double volume){
        musicVolume=volume;
        actualSong.setVolume(volume);
    }

    public static void changeSoundVolume(double volume){
        soundVolume=volume;
    }



}

