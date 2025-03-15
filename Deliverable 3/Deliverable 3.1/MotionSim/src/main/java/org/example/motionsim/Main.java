package org.example.motionsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.classfile.Label;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/motionsim/IPCSMFXMLGame.fxml"));
        Button PlayB = new Button();
        Button SettB = new Button();
        Button LoadB = new Button();
        Button ExitB = new Button();
        CheckBox Mute = new CheckBox();
        Label TechL = new Label();
        Label Title = new Label();
        Label Score = new  Label();
        VBox vbox = new VBox(Title,PlayB,SettB,LoadB,ExitB);
        Scene scene = new Scene(fxmlLoader.load());
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