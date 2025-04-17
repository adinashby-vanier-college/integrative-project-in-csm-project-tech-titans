package org.example.motionsim.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.motionsim.Model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ToggleButton hidePasswordBtn;
    @FXML private Button loginBtn;
    @FXML private Button signUpBtn;
    @FXML private AnchorPane LoginScreen;
    @FXML private Text loginNotSuccessfulMsg;

    private TextField visiblePasswordField = new TextField();
    private List<User> userList;
    private final File userFile = new File("Data/users.json");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUserFromJson();
        passwordToggle();
    }

    private void loadUserFromJson() {
        if (!userFile.exists()) {
            userList = new ArrayList<>();
            return;
        }
        try (Reader reader = new FileReader(userFile)) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            userList = new Gson().fromJson(reader, userListType);
            if (userList == null) {
                userList = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoginBtn(ActionEvent actionEvent) {
        String inputEmail = emailField.getText().trim();
        String inputPassword = getPasswordText().trim();

        boolean match = userList != null && userList.stream().anyMatch(user -> user.getEmail().equals(inputEmail) && user.getPassword().equals(inputPassword));

        if (match) {
            loginNotSuccessfulMsg.setOpacity(0.0);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/motionsim/StartingMenu.fxml"));
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loginNotSuccessfulMsg.setOpacity(1.0);
        }
    }

    @FXML
    private void handleSignUpBtn(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/motionsim/SignUpScreen.fxml"));
            Stage stage = (Stage) signUpBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void passwordToggle() {
        visiblePasswordField.setLayoutX(passwordField.getLayoutX());
        visiblePasswordField.setLayoutY(passwordField.getLayoutY());
        visiblePasswordField.setPrefWidth(passwordField.getPrefWidth());
        visiblePasswordField.setPrefHeight(passwordField.getPrefHeight());
        visiblePasswordField.setVisible(false);

        LoginScreen.getChildren().add(visiblePasswordField);

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
}
