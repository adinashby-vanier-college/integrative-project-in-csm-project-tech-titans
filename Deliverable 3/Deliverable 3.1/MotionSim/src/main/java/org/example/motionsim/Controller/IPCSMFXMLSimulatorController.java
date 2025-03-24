/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.example.motionsim.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class IPCSMFXMLSimulatorController implements Initializable {

    @FXML
    private MenuBar MenuBar;
    @FXML
    private Pane AnimationPane;
    @FXML
    private Rectangle ParamAdjustRec;
    @FXML
    private Label ParamAdjustLabel;
    @FXML
    private Line OrthogonalToLeftLine;
    @FXML
    private Rectangle AmplitudeRec;
    @FXML
    private Label AmplitudeLabel;
    @FXML
    private Rectangle AmplitudeFieldRec;
    @FXML
    private Label AmplitudeFieldLabel;
    @FXML
    private Slider AmplitudeSlider;
    @FXML
    private Rectangle GravityRec;
    @FXML
    private Label GravityLabel;
    @FXML
    private Slider GravitySlider;
    @FXML
    private Rectangle AngleRec;
    @FXML
    private Label AngleLabel;
    @FXML
    private Rectangle AngleFieldRec;
    @FXML
    private Label AngleFieldLabel;
    @FXML
    private Slider AngleSlider;
    @FXML
    private Rectangle SpringConstantRec;
    @FXML
    private Label SpringConstantLabel;
    @FXML
    private Rectangle SpringConstantFieldRec;
    @FXML
    private Label SpringConstantFieldLabel;
    @FXML
    private Slider SpringConstantSlider;
    @FXML
    private Rectangle MassRec;
    @FXML
    private Label MassLabel;
    @FXML
    private Rectangle MassFieldRec;
    @FXML
    private Label MassFieldLabel;
    @FXML
    private Slider MassSlider;
    @FXML
    private Line OrthogonalToLeftLine1;
    @FXML
    private Rectangle BarGraphRec;
    @FXML
    private Label BarGraphLabel;
    @FXML
    private BarChart<?, ?> BarGraph;
    @FXML
    private NumberAxis BarChartYAxis;
    @FXML
    private CategoryAxis BarChartXAxis;
    @FXML
    private Line OrthogonalToLeftLine2;
    @FXML
    private Line OrthogonalToLeftLine3;
    @FXML
    private Rectangle ControlsRec;
    @FXML
    private Label ControlsLabel;
    @FXML
    private Line OrthogonalToLeftLine4;
    @FXML
    private Line LeftLine;
    @FXML
    private Rectangle EnterTimeRec;
    @FXML
    private Label EnterTimeLabel;
    @FXML
    private TextField EnterTimeField;
    @FXML
    private Button EnterTimeBtn;
    @FXML
    private Polyline StartShape;
    @FXML
    private Button StartBtn;
    @FXML
    private Rectangle StopShape;
    @FXML
    private Button StopBtn;
    @FXML
    private Button ResetBtn;
    @FXML
    private Polyline StartShape1;
    @FXML
    private Polyline StartShape11;
    @FXML
    private Rectangle HVelocityRec;
    @FXML
    private Label HVelocityLabel;
    @FXML
    private ComboBox<?> HVelocityComboBox;
    @FXML
    private Rectangle HVelocityFieldRec;
    @FXML
    private Label HVelocityFieldLabel;
    @FXML
    private Rectangle VVelocityRec;
    @FXML
    private Label VVelocityLabel;
    @FXML
    private ComboBox<?> VVelocityComboBox;
    @FXML
    private Rectangle VVelocityFieldRec;
    @FXML
    private Label VVelocityFieldLabel;
    @FXML
    private Rectangle AOORec;
    @FXML
    private Label AOOLabel;
    @FXML
    private ComboBox<?> AOOComboBox;
    @FXML
    private Rectangle AOOFieldRec;
    @FXML
    private Label AOOFieldLabel;
    @FXML
    private Rectangle SimulationTimeRec;
    @FXML
    private Label SimulationTimeLabel;
    @FXML
    private ComboBox<?> SimulationTimeComboBox;
    @FXML
    private Rectangle SimulationTimeFieldRec;
    @FXML
    private Label SimulationTimeFieldLabel;
    @FXML
    private Rectangle GravityFieldRec;
    @FXML
    private Pane spring;
    @FXML
    private Circle springAdjuster;
    @FXML
    private AnchorPane SpringPane;
    @FXML
    private Group groupSpring;
    @FXML
    private Arc SpringAdjusterPath;
    @FXML
    private Label GravityFieldField;

    private static double CenterX = 20;
    private static double CenterY = 135;
    private static double RadiusX = 100;
    private static double RadiusY = 100;
    private static double StartAngle = 0;
    private static double Length = 90;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        double initialAngle = Math.toRadians(StartAngle);
        groupSpring.setLayoutX(CenterX + RadiusX * Math.cos(initialAngle));
        groupSpring.setLayoutY(CenterY - RadiusY* Math.sin(initialAngle));

        AngleSlider.setMin(0);
        AngleSlider.setMax(75);
        AngleFieldLabel.textProperty().bind(AngleSlider.valueProperty().asString("%.2f"));
        Rotate rotate = new Rotate();
        spring.getTransforms().add(rotate);
        rotate.setPivotX(10);
        rotate.setPivotY(39);
        AngleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            rotate.setAngle(newValue.doubleValue()*(-1));
        });
    }

    @FXML
    private void handleEnterTimeBtn(ActionEvent event) {
    }

    @FXML
    private void handleStartBtn(ActionEvent event) {
    }

    @FXML
    private void handleStopBtn(ActionEvent event) {
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
    }

    @FXML
    private void handleHVelocityComboBox(ActionEvent event) {
    }

    @FXML
    private void handleVVelocityComboBox(ActionEvent event) {
    }

    @FXML
    private void handleAOOComboBox(ActionEvent event) {
    }

    @FXML
    private void handleSimulationTimeComboBox(ActionEvent event) {
    }

    @FXML
    public void handleSpringAdjuster(MouseEvent event) {
        double dx = event.getX() - CenterX;
        double dy = event.getY() - CenterY;
        double angle = Math.toDegrees(Math.atan2(-dy, dx));

        if (angle < 0) {
            angle += 360;
        }

        if (angle >= StartAngle && angle <= StartAngle + Length) {
            double radianAngle = Math.toRadians(angle);
            groupSpring.setLayoutX(CenterX + RadiusX * Math.cos(radianAngle));
            groupSpring.setLayoutY(CenterY - RadiusY * Math.sin(radianAngle));
        }
    }
}
