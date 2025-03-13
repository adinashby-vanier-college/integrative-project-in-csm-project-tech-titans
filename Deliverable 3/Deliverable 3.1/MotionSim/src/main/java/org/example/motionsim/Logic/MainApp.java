package org.example.motionsim.Logic;
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        PhysicsEngine engine = new PhysicsEngine();
        SettingsScreen settings = new SettingsScreen(engine);
        EnergyGraph graph = new EnergyGraph(engine);
        BorderPane root = new BorderPane();
        root.setCenter(engine.getCanvas());
        root.setBottom(engine.getChart());
        root.setRight(settings.getSettingsPanel());

        Scene scene = new Scene(root,1450,800);
        primaryStage.setTitle("Spring and Projectile Stimulation");
        primaryStage.setScene(scene);
        primaryStage.show();
        engine.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        PhysicsEngine physicsEngine = new PhysicsEngine();
        SpringMassSystem springSimulation = new SpringMassSystem(1.0,50.0,0.2);
        SettingsScreen settingsScreen = new SettingsScreen(springSimulation);
        UserManual userManual = new UserManual();

        BorderPane root = new BorderPane();
        root.setCenter(physicsEngine.getCanvas());
        root.setRight(settingsScreen.getSettingsPanel());

        Scene scene = new Scene(root, 1450,800);
        primaryStage.setTitle("Spring and Projectile Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        settingsScreen.openUserManual(userManual);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.example.motionsim.Logic.PhysicsEngine;
import org.example.motionsim.Logic.SettingsScreen;

import java.net.URL;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsScreen.fxml"));
            URL fxmlLocation = getClass().getResource("/motionsim/SettingsScreen.fxml");
            if (fxmlLocation == null) {
                throw new IllegalStateException("Cannot find it");
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            PhysicsEngine physicsEngine = new PhysicsEngine();
            SettingsScreenController controller = loader.getController();
            controller.setPhysicsEngine(physicsEngine);
            Scene scene = new Scene(root, 1450,800);
            primaryStage.setTitle("Physics Simulation");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            primaryStage.setResizable(true);
            primaryStage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}