/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package IPCSM;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

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
    private Line LeftLine;
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
    private Rectangle VelocityRec;
    @FXML
    private Rectangle VelocityFieldRec;
    @FXML
    private Label VelocityFieldLabel;
    @FXML
    private Slider VelocitySlider;
    @FXML
    private Rectangle GravityRec;
    @FXML
    private Label GravityLabel;
    @FXML
    private Rectangle GravityFieldLabel;
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
    private Rectangle SpringCoonstantFieldRec;
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
    private Rectangle SimulationTimeRec;
    @FXML
    private Label SimulationTimeLabel;
    @FXML
    private ComboBox<?> SimulationTimeComboBox;
    @FXML
    private Rectangle SimulationTimeFieldRec;
    @FXML
    private Label SimulationTimeFieldLabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void hancleSimulationTimeComboBox(ActionEvent event) {
    }
    
}
