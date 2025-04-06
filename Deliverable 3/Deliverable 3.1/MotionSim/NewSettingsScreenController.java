/*
package org.example.motionsim.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.Node;


//public class NewSettingsScreenController implements Initializable {
public class NewSettingsScreenController {
    @FXML
    private Rectangle TopRec;
    @FXML
    private Line TopLine;
    @FXML
    private Label SettingsScreenLabel;
    @FXML
    private Rectangle LanguageRec;
    @FXML
    private Label LanguageLabel;
    @FXML
    private ComboBox<?> LanguageComboBox;
    @FXML
    private Rectangle GameplayRec;
    @FXML
    private Label GameplayLabel;
    @FXML
    private RadioButton EasyOption;
    @FXML
    private RadioButton NormalOption;
    @FXML
    private RadioButton HardOption;
    @FXML
    private Rectangle WallpaperRec;
    @FXML
    private Label WallpaperLabel;
    @FXML
    private ComboBox<?> WallpaperComboBox;
    @FXML
    private Rectangle AudioRec;
    @FXML
    private Label AudioLabel;
    @FXML
    private Slider MusicVolumeSlider;
    @FXML
    private Rectangle MusicVolumeRec;
    @FXML
    private Label MusicVolumeLabel;
    @FXML
    private CheckBox MusicVolumeMuteBox;
    @FXML
    private Slider SFXVolumeSlider;
    @FXML
    private Rectangle SFXVolumeRec;
    @FXML
    private Label SFXVolumeLabel;
    @FXML
    private CheckBox SFXVolumeMuteBox;
    @FXML
    private Button DefaultBtn;
    @FXML
    private Button SaveBtn;
    @FXML
    private Button ExitBtn;
    @FXML
    private Rectangle BottomRec;
    @FXML
    private Line BottomLine;
    @FXML
    private Button HelpBtn;
    @FXML
    private ComboBox<?> ThemeComboBox;
    
    


    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        VBox tooltipBox = new VBox();
        tooltipBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup = new Popup();
        Text tooltipText = new Text("Select the preferred language of display.");
        tooltipText.setFill(Color.BLACK);
        tooltipBox.getChildren().add(tooltipText);
        popup.getContent().add(tooltipBox);
        LanguageLabel.setOnMouseEntered(e -> popup.show(LanguageLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        LanguageLabel.setOnMouseExited(e -> popup.hide());
        LanguageLabel.setOnMouseMoved(e -> {
        popup.setX(e.getScreenX() + 10);
        popup.setY(e.getScreenY() + 10); 
        });
       
        VBox tooltipBox2 = new VBox();
        tooltipBox2.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup2 = new Popup();
        Text tooltipText2 = new Text("Adjust the level of difficulty in the simulator's 'Game Mode.'");
        tooltipText2.setFill(Color.BLACK);
        tooltipBox2.getChildren().add(tooltipText2);
        popup2.getContent().add(tooltipBox2);
        GameplayLabel.setOnMouseEntered(e -> popup2.show(GameplayLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        GameplayLabel.setOnMouseExited(e -> popup2.hide());
        GameplayLabel.setOnMouseMoved(e -> {
        popup2.setX(e.getScreenX() + 10);
        popup2.setY(e.getScreenY() + 10); 
        });
        
        VBox tooltipBox3 = new VBox();
        tooltipBox3.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup3 = new Popup();
        Text tooltipText3 = new Text("Alter the background and thematic color.");
        tooltipText3.setFill(Color.BLACK);
        tooltipBox3.getChildren().add(tooltipText3);
        popup3.getContent().add(tooltipBox3);
        WallpaperLabel.setOnMouseEntered(e -> popup3.show(WallpaperLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        WallpaperLabel.setOnMouseExited(e -> popup3.hide());
        WallpaperLabel.setOnMouseMoved(e -> {
        popup3.setX(e.getScreenX() + 10);
        popup3.setY(e.getScreenY() + 10); 
        });
        
        VBox tooltipBox4 = new VBox();
        tooltipBox4.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup4 = new Popup();
        Text tooltipText4 = new Text("Adjust the level of sound effects and/or music volume.");
        tooltipText4.setFill(Color.BLACK);
        tooltipBox4.getChildren().add(tooltipText4);
        popup4.getContent().add(tooltipBox4);
        AudioLabel.setOnMouseEntered(e -> popup4.show(AudioLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        AudioLabel.setOnMouseExited(e -> popup4.hide());
        AudioLabel.setOnMouseMoved(e -> {
        popup4.setX(e.getScreenX() + 10);
        popup4.setY(e.getScreenY() + 10); 
        });
        
    }    

    @FXML
    private void handleLanguageComboBox(ActionEvent event) {
    }

    @FXML
    private void handleWallpaperComboBox(ActionEvent event) {
    }

    @FXML
    private void handleDefaultBtn(ActionEvent event) {
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
    }

    @FXML
    private void handleExitBtn(ActionEvent event) {
    }

    @FXML
    private void handleHelpBtn(ActionEvent event) {
    }

    @FXML
    private void handleThemeComboBox(ActionEvent event) {
    }
    
}
/*
package org.example.motionsim.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class NewSettingsScreenController {
    @FXML
    private ComboBox<String> LanguageComboBox;
    @FXML
    private ComboBox<String> WallpaperComboBox;
    @FXML
    private ComboBox<String> ThemeComboBox;
    @FXML
    private RadioButton EasyOption, NormalOption,HardOption;
    @FXML
    private Slider MusicVolumeSlider, SFXVolumeSlider;
    @FXML
    private CheckBox MusicMuteBox, SFXMuteBox;
    @FXML
    private Button DefaultBtn, ExitBtn;
    @FXML
    private Pane SpringPane;
    @FXML
    private Label SettingsScreenLabel;

@FXML
public void initialize() {
LanguageComboBox.getItems().addAll("English","Français");
WallpaperComboBox.getItems().addAll("Blue","Green");
ThemeComboBox.getItems().addAll("Light","Dark");
EasyOption.setText(LanguageController.getString("easy"));
NormalOption.setText(LanguageController.getString("normal"));
HardOption.setText(LanguageController.getString("hard"));
SettingsScreenLabel.setText(LanguageController.getString("settings"));
MusicVolumeSlider.valueProperty().addListener(obs,oldVal,newVal) ->  {
        System.out.println("Music Volume" + newVal);
    };
SFXVolumeSlider.valueProperty().addListener(obs,oldVal,newVal) -> {
        System.out.println("SFX Volume: " + newVal);
    };
}
@FXML
public void handleLanguageComboBox(ActionEvent event) {
    String selectedLanguage = LanguageComboBox.getValue();
    LanguageController.setLanguage(selectedLanguage);
    initialize();
}
@FXML
    private void handleWallpaperComboBox(ActionEvent event) {
    String selectedWallaper = WallpaperComboBox.getValue();
    switch (selectedWallaper) {
    case "Blue" -> SpringPane.setStyle("-fx-background-color: lightblue;");
    case "Green" -> SpringPane.setStyle("-fx-background-color: lightgreen;");
    case "Gray" -> SpringPane.setStyle("-fx-background-color: ligtgray;");
    default -> SpringPane.setStyle("fx-background-color: white;");
}
}
@FXML
private void handleThemeComboBox(ActionEvent event) {
    String selectedTheme = ThemeComboBox.getValue();
    if (selectedTheme.equals("Dark")) {
        SpringPane.setStyle("-fx-background-color: #333;");
    }else {
        SpringPane.setStyle("-fx-background-color: #fff;");
    }
}
@FXML
private void handleDefaultBtn(ActionEvent event) {
    LanguageComboBox.setValue("English");
    WallpaperComboBox.setValue("Blue");
    ThemeComboBox.setValue("Light");
    EasyOption.setSelected(true);
    MusicVolumeSlider.setValue(50);
    SFXVolumeSlider.setValue(50);
    MusicMuteBox.setSelected(false);
    SFXMuteBox.setSelected(false);
}
@FXML
private void handleExitBtn(ActionEvent event) {
    Stage stage = (Stage) ExitBtn.getScene().getWindow();
    stage.close();
}
@FXML
private void handleMusicMuteBox(ActionEvent event) {
    //MusicVolumeSlider.setDisable(MusicMuteBox.isSelected());
    if (MusicMuteBox.isSelected()) {
        MusicVolumeSlider.setDisable(true);
        System.out.println("Music muted");
    }else {
        MusicVolumeSlider.setDisable(false);
        System.out.println("Music unmuted");
    }
}

@FXML
private void handleSFXMuteBox(ActionEvent event) {
    //SFXVolumeSlider.setDisable(SFXMuteBox.isSelected());
    if (SFXMuteBox.isSelected()) {
        SFXVolumeSlider.setDisable(true);
        System.out.println("SFX muted.");
    }else {
        SFXVolumeSlider.setDisable(false);
        System.out.println("SFX unmuted.");
    }
}
@FXML
    private void handleDifficultySelection(ActionEvent event) {
    if (EasyOption.isSelected()) {
        System.out.println("Diffculty set to Easy");
    } else if (NormalOption.isSelected()) {
        System.out.println("Difficulty set to Normal");

    } else if (HardOption.isSelected()) {
        System.out.println("Difficulty set to Hard");

    }
}
@FXML
    private void handleMusicVolumeChange(MouseEvent event) {
    double volume = MusicVolumeSlider.getValue();
    System.out.println("Music Volume:" + volume);
}
@FXML
    private void handleSFXVolumeChange(MouseEvent event) {
    double volume = SFXVolumeSlider.getValue();
    System.out.println("SFX Volume: " + volume);
}
}
 */
package org.example.motionsim.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Node;

public class NewSettingsScreenController implements Initializable {

    @FXML
    private Rectangle TopRec, LanguageRec, GameplayRec, WallpaperRec, AudioRec, MusicVolumeRec, SFXVolumeRec, BottomRec;
    @FXML
    private Line TopLine, BottomLine;
    @FXML
    private Label SettingsScreenLabel, LanguageLabel, GameplayLabel, WallpaperLabel, AudioLabel, MusicVolumeLabel, SFXVolumeLabel;
    @FXML
    private ComboBox<String> LanguageComboBox, WallpaperComboBox, ThemeComboBox, MusicComboBox;
    @FXML
    private RadioButton EasyOption, NormalOption, HardOption;
    @FXML
    private Slider MusicVolumeSlider, SFXVolumeSlider;
    @FXML
    private CheckBox MusicVolumeMuteBox, SFXVolumeMuteBox;
    @FXML
    private Button DefaultBtn, SaveBtn, ExitBtn, HelpBtn;

    private ToggleGroup difficultyGroup;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button PlayMusicBtn;
    @FXML
    private Button StopMusicBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupVolumeControl();
        //loadMusic("playSettingsMenuSong");
        loadMusic("SettingsMenuSong.wav");
        setupTooltips();
       // updateLanguage();
// Language ComboBox setup
        LanguageComboBox.getItems().addAll("English", "Français");
        LanguageComboBox.setValue("English");
        playSettingsMenuSong();
        //updateLanguage();
// Difficulty setup
        difficultyGroup = new ToggleGroup();
        EasyOption.setToggleGroup(difficultyGroup);
        NormalOption.setToggleGroup(difficultyGroup);
        HardOption.setToggleGroup(difficultyGroup);
        NormalOption.setSelected(true);

// Wallpaper setup
        WallpaperComboBox.getItems().addAll("Default", "Dark Mode", "Light Mode");
        WallpaperComboBox.setValue("Default");
if (MusicComboBox != null) {
    MusicComboBox.getItems().addAll("Main Menu Song", "Settings Menu Song", "Song 1", "Song 2");
    MusicComboBox.setValue("Main Menu Song");
    loadMusic("MainMenuSong.mp3");
} else {
    System.err.println("Music is null");
}
// Music Selection setup
       // loadMusic("MainMenuSong.mp3");

// Volume Slider setup
        MusicVolumeSlider.setMin(0);
        MusicVolumeSlider.setMax(100);
        MusicVolumeSlider.setValue(50);
        MusicVolumeSlider.setShowTickLabels(true);
        MusicVolumeSlider.setShowTickMarks(true);

// Sound Effects Toggle
        SFXVolumeMuteBox.setSelected(false);
        MusicVolumeMuteBox.setSelected(false);
    }


    private void setupTooltips() {
        createTooltip(LanguageLabel, "Select the preferred language of display.");
        createTooltip(GameplayLabel, "Adjust the level of difficulty in the simulator's 'Game Mode.'");
        createTooltip(WallpaperLabel, "Alter the background and thematic color.");
        createTooltip(AudioLabel, "Adjust the level of sound effects and/or music volume.");
    }
    @FXML
    private void handlePlayMusic(ActionEvent event) {
        try {
            // Update the path to the song file accordingly
            String musicPath = "file:src/main/resources/motionsim/songs/SettingsMenuSong.wav";
            Media media = new Media(new File(musicPath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            System.out.println("Playing music...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStopMusic(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            System.out.println("Music stopped.");
        }
    }
    @FXML
    private void handleSaveBtn(ActionEvent event) {
        System.out.println("Settings saved!");
    }

    private void createTooltip(Label label, String message) {
        VBox tooltipBox = new VBox();
        tooltipBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px;");
        Popup popup = new Popup();
        Text tooltipText = new Text(message);
        tooltipText.setFill(Color.BLACK);
        tooltipBox.getChildren().add(tooltipText);
        popup.getContent().add(tooltipBox);

        label.setOnMouseEntered(e -> popup.show(label, e.getScreenX() + 10, e.getScreenY() + 10));
        label.setOnMouseExited(e -> popup.hide());
        label.setOnMouseMoved(e -> {
            popup.setX(e.getScreenX() + 10);
            popup.setY(e.getScreenY() + 10);
        });
    }

    private void loadMusic(String fileName) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        try {
            // Use class loader to load the resource file
            URL resource = getClass().getResource("/songs/" + fileName);
            if (resource == null) {
                throw new RuntimeException("File not found: " + fileName);
            }
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(MusicVolumeSlider.getValue() / 100.0);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void playSettingsMenuSong() {
        try {
            String musicPath = getClass().getResource( "/resources/songs/NewSettingsScreen.wav").toString();
            Media media = new Media(new File(musicPath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the song
            mediaPlayer.setVolume(MusicVolumeSlider.getValue() / 100.0); //
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void stopSettingsMenuSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            System.out.println("Settings Menu song stopped");
        }
    }
    @FXML
    private void handleVolumeChange() {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(MusicVolumeSlider.getValue() / 100);
        }
    }
/*
    @FXML
    private void handleMusicSelection(ActionEvent event) {
        String selectedMusic = MusicComboBox.getValue();
        switch (selectedMusic) {
            case "Main Menu Song":
                loadMusic("MainMenuSong.mp3");
                break;
            case "Settings Menu Song":
                loadMusic("SettingsMenuSong.wav");
                break;
            case "Song 1":
                loadMusic("song.wav");
                break;
            case "Song 2":
                loadMusic("song2.wav");
                break;
        }
    }
*/
    @FXML
    private void handleLanguageComboBox(ActionEvent event) {
        String language = LanguageComboBox.getValue();
        //Locale.setDefault(language.equals("Français") ? Locale.FRENCH : Locale.ENGLISH);
        //System.out.println("Language set to: " + language);
        LanguageController.setLanguage(language);
        updateLanguage();
    }

    @FXML
    private void handleLanguageChange(ActionEvent event) {
        String selectedlanguage = LanguageComboBox.getValue();
        LanguageController.setLanguage(selectedlanguage);
        updateLanguage();
    }
    private void playMainMenuSong() {
        try {
            String musicPath = getClass().getResource("/resources/motionsim/songs/MainMenuSong.mp3").toString();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the song
            mediaPlayer.setVolume(MusicVolumeSlider.getValue() / 100.0); // Set initial volume
            mediaPlayer.play();
            System.out.println("Playing Main Menu Song...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing Main Menu Song: " + e.getMessage());
        }
    }

    @FXML
    private void handleWallpaperComboBox(ActionEvent event) {
        String wallpaper = WallpaperComboBox.getValue();
        System.out.println("Wallpaper set to: " + wallpaper);
    }


    @FXML
    private void handleMusicVolumeChange() {
        double volume = MusicVolumeSlider.getValue() / 100.0;
        mediaPlayer.setVolume(volume);
        System.out.println("Music Volume: " + volume);
    }
    @FXML
    private void handleMuteToggle(ActionEvent event) {
        if (MusicVolumeMuteBox.isSelected()) {
            mediaPlayer.setMute(true);
        } else {
            mediaPlayer.setMute(false);
        }
    }

    @FXML
    private void handleSoundEffectsToggle(ActionEvent event) {
        boolean isMuted = SFXVolumeMuteBox.isSelected();
        System.out.println("Sound Effects Muted: " + isMuted);
    }

    @FXML
    private void handleHelpBtn(ActionEvent event) {
        try {
            Parent helpRoot = FXMLLoader.load(getClass().getResource("/motionsim/UserManual.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(helpRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    @FXML
    private void handleExitBtn(ActionEvent event) {
        try {
            Parent mainRoot = FXMLLoader.load(getClass().getResource("/motionsim/MainScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(mainRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    @FXML
    private void handleExitBtn(ActionEvent event) {
        try {
           // stopSettingsMenuSong();
           // playMainMenuSong();
            loadMusic("MainMenuSong.mp3");
            Parent mainRoot = FXMLLoader.load(getClass().getResource("/motionsim/IPCSMFXMLGame.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(mainRoot));
            stage.show();
            System.out.println("Swicthed to Main menu song");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDefaultBtn(ActionEvent event) {
        LanguageComboBox.setValue("English");
        NormalOption.setSelected(true);
        WallpaperComboBox.setValue("Default");
        MusicVolumeSlider.setValue(50);
        MusicComboBox.setValue("Main Menu Song");
        SFXVolumeMuteBox.setSelected(false);
        System.out.println("Settings reset to default.");
    }

    @FXML
    private void setupVolumeControl() {
        MusicVolumeSlider.setMin(0);
        MusicVolumeSlider.setMax(100);
        MusicVolumeSlider.setValue(50);
        MusicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newValue.doubleValue() / 100);
            }
        });
    }

    //from 186 to end
    private void updateLanguage() {
        SettingsScreenLabel.setText(LanguageController.getString("settings.title"));
        LanguageLabel.setText(LanguageController.getString("settings.language"));
        GameplayLabel.setText(LanguageController.getString("settings.gameplay"));
        WallpaperLabel.setText(LanguageController.getString("settings.wallpaper"));
        AudioLabel.setText(LanguageController.getString("settings.audio"));
        MusicVolumeLabel.setText(LanguageController.getString("settings.music"));
        SFXVolumeLabel.setText(LanguageController.getString("settings.sfx"));
        MusicVolumeMuteBox.setText(LanguageController.getString("settings.mute"));
        EasyOption.setText(LanguageController.getString("settings.easy"));
        NormalOption.setText(LanguageController.getString("settings.normal"));
        HardOption.setText(LanguageController.getString("settings.hard"));
        SaveBtn.setText(LanguageController.getString("settings.save"));
        ExitBtn.setText(LanguageController.getString("settings.exit"));
        HelpBtn.setText(LanguageController.getString("settings.help"));
        DefaultBtn.setText(LanguageController.getString("settings.default"));
    ThemeComboBox.getItems().setAll(
            LanguageController.getString("settings.light"),LanguageController.getString("settings.dark"),LanguageController.getString("settings.default")
    );
    WallpaperComboBox.getItems().setAll(
            LanguageController.getString("settings.light"),LanguageController.getString("settings.dark"),LanguageController.getString("settings.default")
                );
    }
    @FXML
    private void handleThemeComboBox() {
        String selectedTheme = ThemeComboBox.getValue();
        if (selectedTheme.equals("Dark")) {
            System.out.println("Theme set to Dark");
        }else {
            System.out.println("Theme set to light");
        }
    }
}