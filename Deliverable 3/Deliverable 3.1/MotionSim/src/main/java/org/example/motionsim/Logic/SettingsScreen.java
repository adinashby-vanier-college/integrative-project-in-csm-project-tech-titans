package org.example.motionsim.Logic;
/*
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;
public class SettingsScreen {
    private VBox settingsPanel;
    public SettingsScreen(PhysicsEngine engine) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages",java.util.Locale.FRENCH);
        Slider springSlider = new Slider(10,100,50);
        Slider gravitySlider = new Slider(5,20,9.81);
        ChoiceBox<String> languageBox = new ChoiceBox<>();
        languageBox.getItems().addAll("English", "Français");
        Button saveButton = new Button(bundle.getString("Save"));
        saveButton.setOnAction(e -> {
            engine.getSpringSystem().setSpringConstant(springSlider.getValue());
            engine.getProjectileMotion().setGravity(gravitySlider.getValue());
        });
        settingsPanel = new VBox(10,springSlider,gravitySlider,languageBox,saveButton);
    }
    public VBox getSettingsPanel() {
        return settingsPanel;
    }
}
*/
/*
import javafx.scene.Scene;
import javafx .scene.control.*;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class SettingsScreen {
    private VBox settingsPanel;
    private Button userManualButton = new Button("Help?");
    private Button exitButton = new Button("Exit");
    public SettingsScreen(SpringMassSystem spring) {

        Label languageLabel = new Label("Language: ");
        ChoiceBox<String> languageBox = new ChoiceBox<>();
        languageBox.getItems().addAll("English", "Français");
        languageBox.setValue("English");
        Label difficultyLabel = new Label("Difficulty: ");
        ToggleGroup difficultyGroup = new ToggleGroup();
        RadioButton easy = new RadioButton("Easy");
        RadioButton medium = new RadioButton("Nedium");
        RadioButton hard = new RadioButton("Hard");
        easy.setToggleGroup(difficultyGroup);
        medium.setToggleGroup(difficultyGroup);
        hard.setToggleGroup(difficultyGroup);
        medium.setSelected(true);

        Label colorLabel = new Label("Change Background: ");
        ColorPicker backgroundColor = new ColorPicker();
        Label controlLabel = new Label(" Control Center: ");
        CheckBox timer = new CheckBox("Timer");
        CheckBox soundeffects = new CheckBox("Sound Effects");
        Label volumeLabel = new Label("Music Volume: ");
        Slider volumerSlider = new Slider(0, 100, 50);
        settingsPanel = new VBox(10, languageLabel, languageBox, difficultyLabel, colorLabel, backgroundColor, controlLabel, timer, soundeffects, volumerSlider, userManualButton);

    }

    public VBox getSettingsPanel() {
        return settingsPanel;
    }

    public void openUserManual(UserManual userManual) {
        userManualButton.setOnAction(e -> userManual.start(new Stage()));
    }
    }
*/
//package org.example.motionsim.Logic;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;
public class SettingsScreen {
    private GridPane settingsLayout;
    private Button userManualButton = new Button("Help?");
    private Button saveButton = new Button("Save");
    private Button exitButton = new Button("Exit");
    public SettingsScreen(SpringMassSystem spring) {
        // Load Language Resources
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        // Default to English
        settingsLayout = new GridPane(); settingsLayout.setPadding(new Insets(20));
        settingsLayout.setVgap(10); settingsLayout.setHgap(15); // General Section - Language Selection
         Label languageLabel = new Label(bundle.getString("language"));
         ChoiceBox<String> languageBox = new ChoiceBox<>();
         languageBox.getItems().addAll("English", "Français");
         languageBox.setValue("English"); // Difficulty Section
         Label difficultyLabel = new Label(bundle.getString("difficulty"));
         ToggleGroup difficultyGroup = new ToggleGroup();
         RadioButton easy = new RadioButton(bundle.getString("easy"));
         RadioButton medium = new RadioButton(bundle.getString("medium"));
         RadioButton hard = new RadioButton(bundle.getString("hard"));
         easy.setToggleGroup(difficultyGroup);
         medium.setToggleGroup(difficultyGroup);
         hard.setToggleGroup(difficultyGroup);
         medium.setSelected(true); // Wallpaper Section
         Label wallpaperLabel = new Label(bundle.getString("wallpaper"));
         Label backgroundLabel = new Label(bundle.getString("changeBackground"));
         ComboBox<String> backgroundPicker = new ComboBox<>();
         backgroundPicker.getItems().addAll("Default", "Light Mode", "Dark Mode"); // Control Center Section
         Label controlLabel = new Label(bundle.getString("controlCenter"));
         CheckBox timerToggle = new CheckBox(bundle.getString("timer")); // Audio Section
         Label audioLabel = new Label(bundle.getString("audio"));
         CheckBox soundEffectsToggle = new CheckBox(bundle.getString("soundEffects"));
         Label volumeLabel = new Label(bundle.getString("musicVolume"));
         Slider volumeSlider = new Slider(0, 100, 50);
         volumeSlider.setShowTickMarks(true); volumeSlider.setShowTickLabels(true); // Add Components to GridPane
        settingsLayout.add(languageLabel, 0, 0);
        settingsLayout.add(languageBox, 1, 0);
        settingsLayout.add(difficultyLabel, 0, 1);
        settingsLayout.add(easy, 1, 2);
        settingsLayout.add(medium, 1, 3);
        settingsLayout.add(hard, 1, 4);
        settingsLayout.add(wallpaperLabel, 0, 5);
        settingsLayout.add(backgroundLabel, 0, 6);
        settingsLayout.add(backgroundPicker, 1, 6);
        settingsLayout.add(controlLabel, 0, 7);
        settingsLayout.add(timerToggle, 1, 8);
        settingsLayout.add(audioLabel, 0, 9);
        settingsLayout.add(soundEffectsToggle, 1, 10);
        settingsLayout.add(volumeLabel, 0, 11);
        settingsLayout.add(volumeSlider, 1, 11);
        settingsLayout.add(saveButton, 0, 12);
        settingsLayout.add(exitButton, 1, 12);
        settingsLayout.add(userManualButton, 2, 12);
    } public GridPane getSettingsPanel() {
        return settingsLayout;
    } public void openSettingsWindow() {
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Settings");
        Scene scene = new Scene(settingsLayout, 500, 500);
        settingsStage.setScene(scene); settingsStage.show();
    } public void openUserManual(UserManual userManual) {
        userManualButton.setOnAction(e -> userManual.start(new Stage()));
    }
}
