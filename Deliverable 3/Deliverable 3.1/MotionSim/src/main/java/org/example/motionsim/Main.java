package org.example.motionsim;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.classfile.Label;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/motionsim/IPCSMFXMLGame.fxml"));
        Button PlayB = new Button("NEW GAME");
        Button SettB = new Button("SETTINGS");
        Button LoadB = new Button("lOAD SAVED SETTINGS");
        Button ExitB = new Button("EXIT APPLICATION");
        for (Button button : new Button[]{newGameBtn, settingsBtn, loadSettingsBtn, exitBtn}) {
            button.setStyle("-fx-font-size: 18; -fx-padding: 10; -fx-border-color: black; -fx-background-color: white;");
        }
        CheckBox Mute = new CheckBox("MUTE AUDIO");

        Label TechL = new Label("TECH TITANS");
        Label Title = new Label("A BIG TITLE");
        Title.setFont(new Font("Arial", 40));
        Label Score = new  Label("HIGHEST SCORE: #####");
        Score.setStyle("-fx-font-size: 16; -fx-border-color: black; -fx-padding: 5; -fx-background-color: white;");

        HBox topbox = new HBox(10, TechL, Mute);
        topbox.setAlignment(Pos.BOTTOM_LEFT);
        topbox.setSpacing(10);

        VBox vbox = new VBox(10, Title,PlayB,SettB,LoadB,ExitB);
        vbox.setAlignment(Pos.CENTER);

        HBox bottombox = new HBox(Score);
        bottombox.setAlignment(Pos.BOTTOM_LEFT);
        bottombox.setSpacing(20);

        BorderPane root = new BorderPane();
        root.setTop(topbox);
        root.setCenter(vbox);
        root.setBottom(bottombox);

        Scene scene = new Scene(fxmlLoader.load(),root, 800,500);
        stage.setResizable(false);
//        stage.setFullScreen(true);
//        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}