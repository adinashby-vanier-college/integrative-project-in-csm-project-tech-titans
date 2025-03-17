package org.example.motionsim.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import  javafx.scene.Node;


import java.io.IOException;

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
        helpButton.setOnAction(this::openUserManual);
    } private void saveSettings() {
        System.out.println("Settings saved!");
    }
    public void openHelp() {
        System.out.println("Help Button! Open User Manual right here!");
    }
    @FXML
    public void openUserManual(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/UserManual.fxml"));
            Parent root = loader.load();
            Stage userManualStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            userManualStage.setTitle("User Guide");
           // userManualStage.setScene(new Scene(root,500,400));
            userManualStage.setScene(scene);
            userManualStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }