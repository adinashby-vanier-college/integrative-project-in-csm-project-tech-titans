
package IPCSM;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;


public class NewSettingsScreenController implements Initializable {

    @FXML
    private Rectangle TopRec;
    @FXML
    private Line TopLine;
    @FXML
    private Label SettingsScreenLabel;
    @FXML
    private Rectangle LanguageRec;
    @FXML
    private Label LanguageLabel;
    @FXML
    private ComboBox<?> LanguageComboBox;
    @FXML
    private Rectangle GameplayRec;
    @FXML
    private Label GameplayLabel;
    @FXML
    private RadioButton EasyOption;
    @FXML
    private RadioButton NormalOption;
    @FXML
    private RadioButton HardOption;
    @FXML
    private Rectangle WallpaperRec;
    @FXML
    private Label WallpaperLabel;
    @FXML
    private ComboBox<?> WallpaperComboBox;
    @FXML
    private Rectangle AudioRec;
    @FXML
    private Label AudioLabel;
    @FXML
    private Slider MusicVolumeSlider;
    @FXML
    private Rectangle MusicVolumeRec;
    @FXML
    private Label MusicVolumeLabel;
    @FXML
    private CheckBox MusicVolumeMuteBox;
    @FXML
    private Slider SFXVolumeSlider;
    @FXML
    private Rectangle SFXVolumeRec;
    @FXML
    private Label SFXVolumeLabel;
    @FXML
    private CheckBox SFXVolumeMuteBox;
    @FXML
    private Button DefaultBtn;
    @FXML
    private Button SaveBtn;
    @FXML
    private Button ExitBtn;
    @FXML
    private Rectangle BottomRec;
    @FXML
    private Line BottomLine;
    @FXML
    private Button HelpBtn;
    @FXML
    private ComboBox<?> ThemeComboBox;
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        VBox tooltipBox = new VBox();
        tooltipBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup = new Popup();
        Text tooltipText = new Text("Select the preferred language of display.");
        tooltipText.setFill(Color.BLACK);
        tooltipBox.getChildren().add(tooltipText);
        popup.getContent().add(tooltipBox);
        LanguageLabel.setOnMouseEntered(e -> popup.show(LanguageLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        LanguageLabel.setOnMouseExited(e -> popup.hide());
        LanguageLabel.setOnMouseMoved(e -> {
        popup.setX(e.getScreenX() + 10);
        popup.setY(e.getScreenY() + 10); 
        });
       
        VBox tooltipBox2 = new VBox();
        tooltipBox2.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup2 = new Popup();
        Text tooltipText2 = new Text("Adjust the level of difficulty in the simulator's 'Game Mode.'");
        tooltipText2.setFill(Color.BLACK);
        tooltipBox2.getChildren().add(tooltipText2);
        popup2.getContent().add(tooltipBox2);
        GameplayLabel.setOnMouseEntered(e -> popup2.show(GameplayLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        GameplayLabel.setOnMouseExited(e -> popup2.hide());
        GameplayLabel.setOnMouseMoved(e -> {
        popup2.setX(e.getScreenX() + 10);
        popup2.setY(e.getScreenY() + 10); 
        });
        
        VBox tooltipBox3 = new VBox();
        tooltipBox3.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup3 = new Popup();
        Text tooltipText3 = new Text("Alter the background and thematic color.");
        tooltipText3.setFill(Color.BLACK);
        tooltipBox3.getChildren().add(tooltipText3);
        popup3.getContent().add(tooltipBox3);
        WallpaperLabel.setOnMouseEntered(e -> popup3.show(WallpaperLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        WallpaperLabel.setOnMouseExited(e -> popup3.hide());
        WallpaperLabel.setOnMouseMoved(e -> {
        popup3.setX(e.getScreenX() + 10);
        popup3.setY(e.getScreenY() + 10); 
        });
        
        VBox tooltipBox4 = new VBox();
        tooltipBox4.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 5px; -fx-spacing: 5px;");
        Popup popup4 = new Popup();
        Text tooltipText4 = new Text("Adjust the level of sound effects and/or music volume.");
        tooltipText4.setFill(Color.BLACK);
        tooltipBox4.getChildren().add(tooltipText4);
        popup4.getContent().add(tooltipBox4);
        AudioLabel.setOnMouseEntered(e -> popup4.show(AudioLabel, e.getScreenX() + 10, e.getScreenY() + 10));
        AudioLabel.setOnMouseExited(e -> popup4.hide());
        AudioLabel.setOnMouseMoved(e -> {
        popup4.setX(e.getScreenX() + 10);
        popup4.setY(e.getScreenY() + 10); 
        });
        
    }    

    @FXML
    private void handleLanguageComboBox(ActionEvent event) {
    }

    @FXML
    private void handleWallpaperComboBox(ActionEvent event) {
    }

    @FXML
    private void handleDefaultBtn(ActionEvent event) {
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
    }

    @FXML
    private void handleExitBtn(ActionEvent event) {
    }

    @FXML
    private void handleHelpBtn(ActionEvent event) {
    }

    @FXML
    private void handleThemeComboBox(ActionEvent event) {
    }
    
}
