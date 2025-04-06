package org.example.motionsim.Controller;
/*
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
public class MusicController {
    private static MusicController instance;
    private MediaPlayer mediaPlayer;
    private double volume = 0.5;
    private boolean isMuted = false;
     private MusicController() {}
    public static synchronized  MusicController getInstance() {
         if (instance == null) {
             instance = new MusicController();
         }
    return instance;
     }
     public void playMusic (String filePath) {
         stopMusic();
         try{
             Media media = new Media(new File(filePath).toURI().toString());
             mediaPlayer =  new MediaPlayer(media);
             mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
             mediaPlayer.setVolume(volume);
             mediaPlayer.play();
         }catch (Exception e) {
             e.printStackTrace();
         }

     }
     public void stopMusic() {
         if (mediaPlayer != null) {
             mediaPlayer.stop();
         }
     }
     public void setVolume(double newVolume) {
         volume = newVolume;
         if (mediaPlayer != null) {
             mediaPlayer.setVolume(volume);
         }
     }
     public void mute(boolean mute ) {
         isMuted =mute;
         if (mediaPlayer != null) {
             mediaPlayer.setMute(isMuted);
         }
     }
     public double getVolume() {
         return volume;
     }
    public boolean isMuted() {
        return isMuted;
    }
}
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;

public class MusicController extends Application {
    private MediaPlayer mediaPlayer;

    private void playMusic(String fileName) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        try {
            String filePath = new File("/motionsim/resources/songs/" + fileName).toURI().toString();
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
        //Media media = new Media(new File(filePath));
        //mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //mediaPlayer.play();
        //}
    }
    private void handleMusicSelection(String menuId) {
        switch (menuId) {
            case "mainMenu":
                playMusic("resources/motionsim/songs/MainMenuSong.mp3");
                break;
            case "settingsMenu":
                playMusic("resources/motionsim/songs/SettingsMenuSong.wav");
                break;
            case "song1":
                playMusic("resources/motionsim/songs/song2.wav");
                break;
            case "song2":
                playMusic("resources/motionsim/songs/song.wav");
                break;
            default:
                System.out.println("No music available for this menu.");
                break;
        }
    }
    public void loadMusic(String fileName) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        try {
            URL resource = getClass().getResource("/songs/" + fileName);
            if (resource == null) {
                throw new RuntimeException("File not found: " + fileName);
            }
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Automatically play Main Menu music on start
        handleMusicSelection("mainMenu");
        Slider volumeSlider = new Slider(0, 1, 0.5);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newValue.doubleValue());
            }
        });
        CheckBox muteCheckBox = new CheckBox("Mute");
        muteCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setMute(newValue);
            }
        });
        root.setTop(volumeSlider);
        root.setBottom(muteCheckBox);

        root.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.focusOwnerProperty().addListener((obs, oldFocus, newFocus) -> {
                    if (newFocus != null) {
                        String id = newFocus.getId();
                        handleMusicSelection(id);
                    }
                });
            }
        });
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Menu Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnMouseClicked(event -> handleMusicSelection("settingsMenu"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
