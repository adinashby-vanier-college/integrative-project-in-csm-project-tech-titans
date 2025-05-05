package org.example.motionsim.Logic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class UserManual extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label manualText = new Label("USER MANUAL: \n" +
                "1. Adjust the Spring Constant (K) using the settings, \n" +
                "2. Change Gravity(g) for different simualtions. \n" +"" +
                "3. Launch the simulation and observe the physics in action \n" +
                "4.Use energy graphs to analyze performance.");
        VBox layout = new VBox(20,manualText);
        Scene scene = new Scene(layout,500,300);

        primaryStage.setTitle("USER GUIDE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
