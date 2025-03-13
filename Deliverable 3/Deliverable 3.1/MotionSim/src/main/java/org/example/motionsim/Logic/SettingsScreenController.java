package org.example.motionsim.Logic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SettingsScreenController {
    @FXML private ChoiceBox<String> languageBox;
    @FXML private ToggleGroup difficultyGroup;
    @FXML private RadioButton easyBtn;
    @FXML private RadioButton normalBtn;
    @FXML private RadioButton hardBtn;
    //@FXML private ComboBox<String> backgroundPicker;
    @FXML private CheckBox timerCheck;
    @FXML private CheckBox soundEffectsCheck;
    @FXML private Slider volumeSlider;
    @FXML private Button saveButton;
    @FXML private Button exitButton;
    @FXML private Button helpButton;
    @FXML ChoiceBox<String> backgroundPicker; //
    private PhysicsEngine physicsEngine;
    public void setPhysicsEngine(PhysicsEngine physicsEngine) {
        this.physicsEngine = physicsEngine;
    }
    public void initialize() {
        // Add options to choice boxes
        languageBox.getItems().addAll("English", "Français");
        languageBox.setValue("English");
        if (backgroundPicker != null) {
            backgroundPicker.getItems().addAll("Default", "Light Mode", "Dark Mode");
            backgroundPicker.setValue("Default"); // Handle save button action
        }else {
            System.out.println("Error");
        }
            saveButton.setOnAction(e -> saveSettings());
        exitButton.setOnAction(e -> System.exit(0));
        helpButton.setOnAction(e -> openHelp());
    } private void saveSettings() {
        System.out.println("Settings saved!");
    }
    public void openHelp() {
        System.out.println("Help Button! Open User Manual right here!");
    }
    public void openUserManual() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User Manual.fxml"));
            Parent root = loader.load();
            Stage userManualStage = new Stage();
            userManualStage.setTitle("User Guide");
            userManualStage.setScene(new Scene(root,500,400));
            userManualStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}