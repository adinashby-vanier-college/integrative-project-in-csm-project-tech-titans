package org.example.motionsim.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import org.example.motionsim.Model.SpringPhysics;

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
    @FXML
    private Pane spring;
    private List<Arc> springArcs;
    private List<Double> originalArcRadius = new ArrayList<>();
    private List<Double> originalArcPositions = new ArrayList<>();
    private Point2D initialBallPosition;
    private Point2D lineStart;
    private Point2D lineEnd;
    private DoubleProperty amplitude = new SimpleDoubleProperty(0.0);
    private double maxSpringDistance;
    @FXML
    private Circle springAdjuster;
    @FXML
    private AnchorPane SpringPane;
    @FXML
    private Group groupSpring;
    @FXML
    private Rectangle MassRec;
    @FXML
    private Rectangle MassFieldRec;
    @FXML
    private Label MassLabel;
    @FXML
    private Label MassFieldLabel;
    @FXML
    private Slider MassSlider;
    @FXML
    private Label HeightLabel;
    @FXML
    private Label HeightFieldLabel;
    @FXML
    private Rectangle HeightFieldRec;
    @FXML
    private Rectangle HeightRec;

    private SpringPhysics physics;
    private Timeline timeline;
    private Point2D originalSpringEndPosition;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        physics = SpringPhysics.getInstance();
        physics.setObject(ball);

        attachBallToLineEnd();

        AmplitudeSlider.setMin(0);
        AmplitudeSlider.setMax(maxSpringDistance);
        amplitude.bindBidirectional(AmplitudeSlider.valueProperty());
        amplitude.addListener((obs, oldVal, newVal) -> {
            AmplitudeFieldLabel.setText(String.format("%.2f", newVal.doubleValue()));
            updateBallPosition(newVal.doubleValue());
            physics.setAmplitude(Double.valueOf(AmplitudeFieldLabel.getText()));
            updateRealTimeHeight();
        });

        springArcs = new ArrayList<>();
        for (Node node : spring.getChildren()) {
            if (node instanceof Arc) {
                Arc arc = (Arc) node;
                springArcs.add(arc);
                originalArcRadius.add(arc.getRadiusX());
                originalArcPositions.add(arc.getLayoutX());
            }
        }

        MassSlider.setMin(0);
        MassSlider.setMax(50);
        MassSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            MassFieldLabel.setText(String.format("%.2f", newValue.doubleValue()));
            physics.setMass(Double.valueOf(MassFieldLabel.getText()));
        });

        AngleSlider.setMin(0);
        AngleSlider.setMax(65);
        AngleFieldLabel.textProperty().bind(AngleSlider.valueProperty().asString("%.2f"));
        Rotate rotate = new Rotate();
        groupSpring.getTransforms().add(rotate);
        rotate.setPivotX(30);
        rotate.setPivotY(89);
        AngleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            rotate.setAngle(newValue.doubleValue()*(-1));
            physics.setAngle(Double.valueOf(AngleFieldLabel.getText()));
            physics.setAngleRad(physics.calculateAngleRad(physics.getAngle()));
            updateRealTimeHeight();
        });
        physics.setGravity(Double.valueOf(GravityFieldLabel.getText()));
        physics.setSpringConstant(Double.valueOf(SpringConstantFieldLabel.getText()));
    }

    private void updateRealTimeHeight() {
        double angleDegrees = Double.parseDouble(AngleFieldLabel.getText());
        double angleRadians = Math.toRadians(angleDegrees);

        double currentAmplitude = Double.parseDouble(AmplitudeFieldLabel.getText());

        double rawHeight = (maxSpringDistance - currentAmplitude) * Math.sin(angleRadians);

        if (Math.abs(rawHeight) < 1e-9) {
            rawHeight = 0.0;
        }

        HeightFieldLabel.setText(String.format("%.2f", rawHeight));
        physics.setHeight(Double.valueOf(HeightFieldLabel.getText()));
    }

    /**
     * Places the ball based on the amplitude and also compresses the arcs.
     */
    private void updateBallPosition(double newAmplitude) {
        double ratio = newAmplitude/maxSpringDistance;

        Point2D newPos = lineEnd.add(lineStart.subtract(lineEnd).multiply(ratio));
        ball.setLayoutX(newPos.getX());
        ball.setLayoutY(newPos.getY());

        compressArcs(ratio);
    }

    /**
     * This forces the ball's center to match the end of springPath.
     */
    private void attachBallToLineEnd() {
        double lineEndXInPane = springPath.getLayoutX() + springPath.getEndX();
        double lineEndYInPane = springPath.getLayoutY() + springPath.getEndY();

        // Store the original spring end position
        originalSpringEndPosition = new Point2D(lineEndXInPane, lineEndYInPane);

        // Set initial ball position
        ball.setLayoutX(lineEndXInPane);
        ball.setLayoutY(lineEndYInPane);

        // Store position in physics
        physics.setInitialPosition(lineEndXInPane, lineEndYInPane);

        initialBallPosition = new Point2D(lineEndXInPane, lineEndYInPane);
        lineStart = new Point2D(springPath.getLayoutX() + springPath.getStartX(),
                springPath.getLayoutY() + springPath.getStartY());
        lineEnd = initialBallPosition;

        maxSpringDistance = lineStart.distance(lineEnd);

        System.out.println("Initial Setup:");
        System.out.println("Spring End Position: " + originalSpringEndPosition);
        System.out.println("Ball Position: (" + ball.getLayoutX() + ", " + ball.getLayoutY() + ")");
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

        // Prevents the ball from being moved forward
        if (projectedPoint.distance(lineStart) > lineStart.distance(lineEnd)) {
            return;
        }

        // Makes sure the ball moves strictly along the line.
        ball.setLayoutX(projectedPoint.getX());
        ball.setLayoutY(projectedPoint.getY());

        double currentDistance = lineEnd.distance(projectedPoint);

        amplitude.set(currentDistance);

        updateRealTimeHeight();

        double maxDistance = lineStart.distance(lineEnd);

        double compressionRatio = currentDistance/maxDistance;

        compressArcs(compressionRatio);
    }

    /**
     * Compresses arcs based on a ratio.
     */
    private void compressArcs(double compressionRatio) {
        // Decide how small arcs get at maximum compression
        // arcs are half their original radius at ratio=1
        double minRadiusFactor = 0.5;
        // Same logic for spacing
        double minSpacingFactor = 0.2;
        // Arc #0 is leftmost arc which we'll compress distances relative to it.
        double anchorX = originalArcPositions.get(0);

        for (int i = 0; i < springArcs.size(); i++) {
            Arc arc = springArcs.get(i);

            double origRadius = originalArcRadius.get(i);
            double currentFactorRadius = 1.0 - compressionRatio * (1.0 - minRadiusFactor);
            double newRadiusX = origRadius * currentFactorRadius;
            arc.setRadiusX(newRadiusX);

            double origArcX = originalArcPositions.get(i);
            // How far is this arc from the first arc
            double offset = origArcX - anchorX;

            double currentFactorSpace = 1.0 - compressionRatio * (1.0 - minSpacingFactor);
            double newArcX = anchorX + offset * currentFactorSpace;
            arc.setLayoutX(newArcX);
        }
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
        // Get current positions and spring orientation
        double startX = ball.getLayoutX();
        double startY = ball.getLayoutY();

        // Get spring's end points to determine true direction
        double springStartX = springPath.getLayoutX() + springPath.getStartX();
        double springStartY = springPath.getLayoutY() + springPath.getStartY();
        double springEndX = springPath.getLayoutX() + springPath.getEndX();
        double springEndY = springPath.getLayoutY() + springPath.getEndY();

        // Calculate spring's actual direction vector
        double springDirX = springEndX - springStartX;
        double springDirY = springEndY - springStartY;
        double springLength = Math.sqrt(springDirX * springDirX + springDirY * springDirY);

        // Normalize direction vector
        springDirX /= springLength;
        springDirY /= springLength;

        // Calculate launch velocity magnitude
        double launchVelocity = physics.calculateLaunchVelocity(
                physics.getSpringConstant(),
                physics.getMass(),
                physics.getAmplitude()
        );

        // Set velocity components based on spring's actual direction
        double horizontalVelocity = launchVelocity * springDirX;
        double verticalVelocity = launchVelocity * springDirY;

        System.out.println("\nLaunch Debug Information:");
        System.out.println("Spring Direction: (" + springDirX + ", " + springDirY + ")");
        System.out.println("Start Position: (" + startX + ", " + startY + ")");
        System.out.println("Launch Velocity: " + launchVelocity);
        System.out.println("Horizontal Velocity: " + horizontalVelocity);
        System.out.println("Vertical Velocity: " + verticalVelocity);

        // Update physics with correct values
        physics.setInitialPosition(startX, startY);
        physics.setVelocity(launchVelocity);
        physics.setHorizontalVelocity(horizontalVelocity);
        physics.setVerticalVelocity(verticalVelocity);

        physics.play();
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
        System.out.println("Before Reset:");
        System.out.println("Ball Position: (" + ball.getLayoutX() + ", " + ball.getLayoutY() + ")");

        physics.reset();

        System.out.println("After Reset:");
        System.out.println("Ball Position: (" + ball.getLayoutX() + ", " + ball.getLayoutY() + ")");

        // Explicitly reset ball position to spring end
        ball.setLayoutX(originalSpringEndPosition.getX());
        ball.setLayoutY(originalSpringEndPosition.getY());

        // Reset amplitude to 0
        amplitude.set(0);

        // Update the real-time height
        updateRealTimeHeight();
    }

    private void verifySpringOrientation() {
        double startX = springPath.getLayoutX() + springPath.getStartX();
        double startY = springPath.getLayoutY() + springPath.getStartY();
        double endX = springPath.getLayoutX() + springPath.getEndX();
        double endY = springPath.getLayoutY() + springPath.getEndY();

        System.out.println("\nSpring Orientation:");
        System.out.println("Start point: (" + startX + ", " + startY + ")");
        System.out.println("End point: (" + endX + ", " + endY + ")");
        System.out.println("Direction vector: (" + (endX - startX) + ", " + (endY - startY) + ")");
    }

    @FXML
    private void handleHVelocityComboBox(ActionEvent event) {
    }

    @FXML
    public void handleAOOComboBox(ActionEvent actionEvent) {
    }

    @FXML
    public void handleVVelocityComboBox(ActionEvent actionEvent) {
    }

    @Deprecated
    public void handleAmplitudeSlider(Event event) {
    }

    @FXML
    private void handleMenuGoToSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/SettingsScreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) MenuBar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMenuGoToManual() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/UserManual.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) MenuBar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMenuGoToSandbox() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/IPCSMFXMLSimulator.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) MenuBar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
