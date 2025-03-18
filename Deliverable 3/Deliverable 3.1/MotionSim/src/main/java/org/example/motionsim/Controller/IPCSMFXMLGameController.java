/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.example.motionsim.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class IPCSMFXMLGameController implements Initializable {

    @FXML
    private MenuBar MenuBar;
    @FXML
    private Pane AnimationPane;
    @FXML
    private Rectangle GameReadingsRec;
    @FXML
    private Label GameReadingsLabel;
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
    private Line OrthogonalToLeftLine3;
    @FXML
    private Rectangle ControlsRec;
    @FXML
    private Label ControlsLabel;
    @FXML
    private Line OrthogonalToLeftLine4;
    @FXML
    private Polyline StartShape;
    @FXML
    private Button StartBtn;
    @FXML
    private Button ResetBtn;
    @FXML
    private Polyline StartShape1;
    @FXML
    private Polyline StartShape11;
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
    private Rectangle TimeRemainingRec;
    @FXML
    private Label TimeRemainingLabel;
    @FXML
    private Rectangle TimeRemainingFieldRec;
    @FXML
    private Label TimeRemainingFieldLabel;
    @FXML
    private Rectangle PointsRec;
    @FXML
    private Label PointsLabel;
    @FXML
    private Rectangle PointsFieldRec;
    @FXML
    private Label PointsFieldLabel;
    @FXML
    private Rectangle ParamAdjustRec;
    @FXML
    private Label ParamAdjustLabel;
    @FXML
    private Line OrthogonalToLeftLine2;
    @FXML
    private Line OrthogonalToLeftLine1;
    @FXML
    private Line OrthogonalToLeftLine5;
    @FXML
    private Rectangle DynamicLabelsRec;
    @FXML
    private Label DynamicLabelsLabel;
    @FXML
    private Line OrthogonalToLeftLine6;
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
    private Line LeftLine;
    @FXML
    private Rectangle GravityRec;
    @FXML
    private Label GravityLabel;
    @FXML
    private Rectangle GravityFieldRec;
    @FXML
    private Label GravityFieldLabel;
    @FXML
    private Rectangle SpringConstantRec;
    @FXML
    private Label SpringConstantLabel;
    @FXML
    private Rectangle SpringConstantFieldRec;
    @FXML
    private Label SpringConstantFieldLabel;
    @FXML
    private Circle ball;
    @FXML
    private Line springPath;

    private Point2D initialBallPosition;
    private Point2D lineStart;
    private Point2D lineEnd;
    private double springAngle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        attachBallToLineEnd();
    }

    /**
     * This forces the ball's center to match the end of springPath.
     */
    private void attachBallToLineEnd() {
        double lineEndXInPane = springPath.getLayoutX() + springPath.getEndX();
        double lineEndYInPane = springPath.getLayoutY() + springPath.getEndY();

        ball.setLayoutX(lineEndXInPane);
        ball.setLayoutY(lineEndYInPane);

        // These lines find the initial ball position and calculate the line direction
        initialBallPosition = new Point2D(lineEndXInPane, lineEndYInPane);
        lineStart = new Point2D(springPath.getLayoutX() + springPath.getStartX(),
                springPath.getLayoutY() + springPath.getStartY());
        lineEnd = initialBallPosition;

        // This calculates the angle of the spring line
        double dx = lineEnd.getX() - lineStart.getX();
        double dy = lineEnd.getY() - lineStart.getY();
        springAngle = Math.atan2(dy, dx);
    }

    /**
     * This handles dragging the ball backward along the spring path.
     */
    @FXML
    private void handleBallDrag(MouseEvent event) {
        // This converts the mouse position from scene coords to the pane's local coords
        Point2D mousePosInLocal = ball.getParent().sceneToLocal(event.getSceneX(), event.getSceneY());

        // This part projects onto the line in that same local coordinate system.
        Point2D projectedPoint = projectPointOntoLine(mousePosInLocal, lineStart, lineEnd);
        System.out.println("Projected Point: " + projectedPoint);

        // This will prevent the ball from being moved forward
        if (projectedPoint.distance(lineStart) > lineStart.distance(lineEnd)) {
            return;
        }

        // Makes sure the ball moves strictly along the line.
        ball.setLayoutX(projectedPoint.getX());
        ball.setLayoutY(projectedPoint.getY());
    }

    /**
     * This projects a given point onto the line defined by two points.
     * @param projectPoint The point to project
     * @param lineStart The start of the line
     * @param lineEnd The end of the line
     * @return The projected point on the line
     */
    private Point2D projectPointOntoLine(Point2D projectPoint, Point2D lineStart, Point2D lineEnd) {
        Point2D lineVector = lineEnd.subtract(lineStart);
        Point2D pointVector = projectPoint.subtract(lineStart);

        double projectionScale = pointVector.dotProduct(lineVector) / lineVector.dotProduct(lineVector);
        projectionScale = Math.max(0, Math.min(1, projectionScale)); // Clamping to the line segment

        return lineStart.add(lineVector.multiply(projectionScale));
    }

    @FXML
    private void handleStartBtn(ActionEvent event) {
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
    }

    @FXML
    private void handleHVelocityComboBox(ActionEvent event) {
    }

    @FXML
    public void compressSpring() {

    }
}
