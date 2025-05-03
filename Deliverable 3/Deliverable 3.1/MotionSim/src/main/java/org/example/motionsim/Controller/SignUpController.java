package org.example.motionsim.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.motionsim.Model.ThemeUtil;
import org.example.motionsim.Model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SignUpController implements Initializable {
    @FXML
    Pane SpringPane;
    @FXML
    private Pane mainPane;
    @FXML
    private AnchorPane signUpScreen;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleButton hidePasswordBtn;
    @FXML
    private Button createAccBtn;
    @FXML
    private Text problemMsg;

    private TextField visiblePasswordField = new TextField();
    private List<User> userList = new ArrayList<>();
    private final File userFile = new File("Data/users.json");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsersFromJson();
        passwordToggle();
    }

    private void loadUsersFromJson() {
        if (!userFile.exists()) return;
        try (Reader reader = new FileReader(userFile)) {
            Type listType = new TypeToken<List<User>>() {}.getType();
            userList = new Gson().fromJson(reader, listType);
            // filters out malformed users
            userList = userList.stream().filter(user -> user.getEmail() != null && user.getPassword() != null).collect(Collectors.toList());
            if (userList == null) userList = new ArrayList<>();
        } catch (IOException e) {
            showProblem("Failed to load users.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateAccount(ActionEvent actionEvent) {
        String email = emailField.getText().trim();
        String password = getPasswordText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showProblem("Please fill in both information boxes.");
            return;
        }

        boolean exists = userList.stream().anyMatch(user -> user.getEmail() != null && user.getEmail().equals(email));
        if (exists) {
            showProblem("This email is already registered to an existing account.");
            return;
        }

        userList.add(new User(email, password));
        saveUsersToJson();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/motionsim/LoginScreen.fxml"));
            Stage stage = (Stage) createAccBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            showProblem("Could not load Login screen.");
            e.printStackTrace();
        }
    }

    private void saveUsersToJson() {
        try {
            File dataDir = new File("Data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            try (Writer writer = new FileWriter(userFile)) {
                new Gson().toJson(userList, writer);
                System.out.println("Saving to: " + userFile.getAbsolutePath());

            }
        } catch (IOException e) {
            showProblem("Could not save new user.");
            e.printStackTrace();
        }
    }

    private void passwordToggle() {
        visiblePasswordField.setLayoutX(passwordField.getLayoutX());
        visiblePasswordField.setLayoutY(passwordField.getLayoutY());
        visiblePasswordField.setPrefWidth(passwordField.getPrefWidth());
        visiblePasswordField.setPrefHeight(passwordField.getPrefHeight());
        visiblePasswordField.setVisible(false);

        signUpScreen.getChildren().add(visiblePasswordField);

        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        hidePasswordBtn.setOnAction(e -> {
            if (hidePasswordBtn.isSelected()) {
                visiblePasswordField.setText(passwordField.getText());
                visiblePasswordField.setVisible(true);
                passwordField.setVisible(false);
                hidePasswordBtn.setText("Show Password");
            } else {
                passwordField.setText(visiblePasswordField.getText());
                passwordField.setVisible(true);
                visiblePasswordField.setVisible(false);
                hidePasswordBtn.setText("Hide Password");
            }
        });

        visiblePasswordField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!passwordField.isVisible()) passwordField.setText(newVal);
        });

        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!visiblePasswordField.isVisible()) visiblePasswordField.setText(newVal);
        });
    }

    private String getPasswordText() {
        return passwordField.isVisible() ? passwordField.getText() : visiblePasswordField.getText();
    }

    private void showProblem(String message) {
        problemMsg.setText(message);
        problemMsg.setOpacity(1.0);
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getVisiblePasswordField() {
        return visiblePasswordField;
    }
}
