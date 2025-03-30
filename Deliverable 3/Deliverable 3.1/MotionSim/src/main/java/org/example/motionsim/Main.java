package org.example.motionsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.motionsim.Controller.LanguageController;

import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/motionsim/NewSettingsScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}

    /*
    @Override
    public void start(Stage stage) throws IOException {
        LanguageController.setLanguage("English");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/NewSettingsScreen.fxml"));
        loader.setResources(ResourceBundle.getBundle("messages", LanguageController.getCurrentLocale()));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(LanguageController.getString("settings.title"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
/*
     */