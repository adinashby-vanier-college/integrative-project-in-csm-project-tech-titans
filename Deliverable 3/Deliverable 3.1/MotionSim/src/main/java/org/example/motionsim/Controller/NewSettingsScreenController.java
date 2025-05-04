package org.example.motionsim.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
import org.example.motionsim.Model.GameSettings;
import org.example.motionsim.Model.ThemeUtil;

public class NewSettingsScreenController implements Initializable {
    @FXML
    private Rectangle TopRec, LanguageRec, GameplayRec, WallpaperRec, AudioRec, MusicVolumeRec, BottomRec;
    @FXML
    private Line TopLine, BottomLine;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Label SettingsScreenLabel, LanguageLabel, GameplayLabel, WallpaperLabel, AudioLabel, MusicVolumeLabel;
    @FXML
    private ComboBox<String> LanguageComboBox, WallpaperComboBox, ThemeComboBox, MusicComboBox;
    @FXML
    private RadioButton EasyOption, NormalOption, HardOption;
    @FXML
    private Slider MusicVolumeSlider;
    @FXML
    private CheckBox MusicVolumeMuteBox;
    @FXML
    private Button DefaultBtn, SaveBtn, ExitBtn, HelpBtn;

    private ToggleGroup difficultyGroup;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button PlayMusicBtn;
    @FXML
    private Button StopMusicBtn;
    @FXML
    private Pane MainPane;
    @FXML
    private ComboBox<String> themeComboBox;
    private final Map<String, ThemeUtil.AppTheme> wallpaperMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupVolumeControl();
        applyWallpaperToImageView(AppTheme.getWallpaperPath());
        loadMusic("SettingsMenuSong.wav");
        setupTooltips();
        backgroundImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/wallpapers/DEFAULT.png")).toExternalForm()));
        LanguageComboBox.getItems().addAll("English", "Français");
        LanguageComboBox.setValue("English");
        playSettingsMenuSong();

        // Difficulty setup
        difficultyGroup = new ToggleGroup();
        EasyOption.setToggleGroup(difficultyGroup);
        NormalOption.setToggleGroup(difficultyGroup);
        HardOption.setToggleGroup(difficultyGroup);
        NormalOption.setSelected(true);
        handleDifficultySelection(null);


        if (MusicComboBox != null) {
            MusicComboBox.getItems().addAll("Main Menu Song", "Settings Menu Song", "Song 1", "Song 2");
            MusicComboBox.setValue("Main Menu Song");
            loadMusic("MainMenuSong.mp3");
        } else {
            System.err.println("Music is null");
        }

        // Volume Slider setup
        MusicVolumeSlider.setMin(0);
        MusicVolumeSlider.setMax(100);
        MusicVolumeSlider.setValue(50);
        MusicVolumeSlider.setShowTickLabels(true);
        MusicVolumeSlider.setShowTickMarks(true);

        // Sound Effects Toggle
        MusicVolumeMuteBox.setSelected(false);

        populateWallpaperCombo();
    }

    private void populateWallpaperCombo() {
        wallpaperMap.clear();
        wallpaperMap.put(LanguageController.getString("settings.default"), ThemeUtil.AppTheme.DEFAULT);
        wallpaperMap.put(LanguageController.getString("settings.dark"), ThemeUtil.AppTheme.DARK);
        wallpaperMap.put(LanguageController.getString("settings.light"), ThemeUtil.AppTheme.LIGHT);

        WallpaperComboBox.getItems().setAll(wallpaperMap.keySet());

        ThemeUtil.AppTheme current = ThemeUtil.getCurrentTheme();
        wallpaperMap.forEach((label, theme) -> {
            if (theme == current) {
                WallpaperComboBox.setValue(label);
            }
        });
        if (WallpaperComboBox.getValue() == null) {
            WallpaperComboBox.setValue(LanguageController.getString("settings.default"));
        }
    }

    private void setupTooltips() {
        createTooltip(LanguageLabel, "Select the preferred language of display.");
        createTooltip(GameplayLabel, "Adjust the level of difficulty in the simulator's 'Game Mode.'");
        createTooltip(WallpaperLabel, "Alter the background and thematic color.");
        createTooltip(AudioLabel, "Adjust the level of sound effects and/or music volume.");
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
            URL resource = getClass().getResource("/motionsim/songs/" + fileName);
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
            String musicPath = getClass().getResource("/motionsim/songs/SettingsMenuSong.wav").toString();
            Media media = new Media(musicPath);
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
    private void handleLanguageComboBox(ActionEvent event) {
        String language = LanguageComboBox.getValue();
        LanguageController.setLanguage(language);
        updateLanguage();
    }

    @FXML
    private void handleWallpaperComboBox() {
        String label = WallpaperComboBox.getValue();
        if (label == null) return;

        ThemeUtil.AppTheme theme = wallpaperMap.get(label);
        if (theme != null) {
            ThemeUtil.setTheme(theme);
            ThemeUtil.applyThemeToPane(MainPane);
        }
    }

    private void applyWallpaperToImageView(String resourcePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResource("/wallpapers/DEFAULT.png")).toExternalForm());
            backgroundImageView.setImage(image);
        } catch (Exception e) {
            System.err.println("Could not load wallpaper: " + resourcePath);
            e.printStackTrace();
        }
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

    public class AppTheme {
        private static String wallpaperPath = "/wallpapers/DEFAULT.png";
        public static String getWallpaperPath() {
            return wallpaperPath;
        }
    }

    @FXML
    private void handleExitBtn(ActionEvent event) {
        try {
            stopSettingsMenuSong();
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
        MusicVolumeMuteBox.setText(LanguageController.getString("settings.mute"));
        EasyOption.setText(LanguageController.getString("settings.easy"));
        NormalOption.setText(LanguageController.getString("settings.normal"));
        HardOption.setText(LanguageController.getString("settings.hard"));
        SaveBtn.setText(LanguageController.getString("settings.save"));
        ExitBtn.setText(LanguageController.getString("settings.exit"));
        HelpBtn.setText(LanguageController.getString("settings.help"));
        DefaultBtn.setText(LanguageController.getString("settings.default"));

        populateWallpaperCombo();
    }

    @FXML
    private void handleDifficultySelection(ActionEvent event) {
        if (EasyOption.isSelected()) {
            GameSettings.setDifficulty(GameSettings.Difficulty.EASY);
        } else if (NormalOption.isSelected()) {
            GameSettings.setDifficulty(GameSettings.Difficulty.NORMAL);
        } else if (HardOption.isSelected()) {
            GameSettings.setDifficulty(GameSettings.Difficulty.HARD);
        }
    }
}