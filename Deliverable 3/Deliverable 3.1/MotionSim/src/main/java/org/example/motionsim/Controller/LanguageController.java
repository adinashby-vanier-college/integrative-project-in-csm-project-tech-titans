package org.example.motionsim.Controller;

/*import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;
public class LanguageController extends Application{
    @Override
    public void start(Stage primaryStage) {
        try {
            Locale locale = new Locale("fr");
            ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

            Button startButton = new Button(bundle.getString("start"));
            Button settingsButton = new Button(bundle.getString("settings"));
            Button helpbutton = new Button(bundle.getString("help"));

            VBox layout = new VBox(10, startButton, settingsButton, helpbutton);
            Scene scene = new Scene(layout, 400, 300);

            primaryStage.setTitle("title");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    }
*/
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
    private static Locale currentLocale = Locale.ENGLISH;
    private static ResourceBundle bundle = ResourceBundle.getBundle("motionsim.messages",currentLocale);

    public static void setLanguage(String language) {
       // LanguageController.setLanguage("English");
        if (language.equalsIgnoreCase("Français")) {
            currentLocale = Locale.FRENCH;
        }
        else {
            currentLocale = Locale.ENGLISH;
        }
        bundle = ResourceBundle.getBundle("motionsim.messages",currentLocale);
    } public static String getString(String key ) {
        return bundle.getString(key);
    }
    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}