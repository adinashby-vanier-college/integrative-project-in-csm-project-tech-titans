package org.example.motionsim.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class UserManualController {
    @FXML
    private TextArea gameExplanation;
    @FXML
    private TextArea scienceExplanation;
    @FXML
    private Button closeButton;

    public void initialize() {
        gameExplanation.setText(
                "This game uses the study of physics, waves and mechanics to fire a ball with a spring!\n\n" +
                        "You my favourite player, are able to change the spring constant(k), the angle of the string," +
                        "the gravity(g), and you will finally charge the ball by dragging the string back and letting go!"
        );
        scienceExplanation.setText(
                "The force exerted by the spring the Hooke's Law: F = -kx \n" +
                        "-The launch velocity (v) depends on the energy stored in the compressed spring:\n" +
                        "U = 1/2 kx^2"
        );
        //   closeButton.setOnAction(e -> ((Stage) closeButton.getScene().getWindow()).close());
        // }
    }
        @FXML
        private void closeUserManual (ActionEvent actionEvent){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/SettingsScreen.fxml"));
                Parent settingsRoot = loader.load();
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(settingsRoot);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
