package org.example.motionsim.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    @FXML
    private Line RightBorderLine;

    private SpringPhysics physics;
    private Point2D originalSpringEndPosition;
    private List<Arc> springArcs;
    private List<Double> originalArcRadius = new ArrayList<>();
    private List<Double> originalArcPositions = new ArrayList<>();
    private Point2D initialBallPosition;
    private Point2D lineStart;
    private Point2D lineEnd;
    private DoubleProperty amplitude = new SimpleDoubleProperty(0.0);
    private double maxSpringDistance;
    private Circle launchBall;
    private boolean isLaunched = false;
    private Label resetMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        physics = SpringPhysics.getInstance();
        physics.setObject(ball);
        physics.addBoundaryLine(RightBorderLine);
        attachBallToLineEnd();

        physics.setVelocityUpdateCallback(velocities -> {
            Platform.runLater(() -> {
                VVelocityFieldLabel.setText(String.format("%.2f", -velocities.getY())); // Negative because Y is inverted
                HVelocityFieldLabel.setText(String.format("%.2f", velocities.getX()));
            });
        });

        physics.setOutOfBoundsCallback(isOutOfBounds -> {
            if (isOutOfBounds) {
                Platform.runLater(() -> {
                    showResetMessage();
                    handleResetBtn(new ActionEvent());
                });
            }
        });

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
        MassSlider.setMax(15);
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

        originalSpringEndPosition = new Point2D(lineEndXInPane, lineEndYInPane);

        ball.setLayoutX(lineEndXInPane);
        ball.setLayoutY(lineEndYInPane);

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
        if (isLaunched) return;

        Point2D mousePosInLocal = ball.getParent().sceneToLocal(event.getSceneX(), event.getSceneY());
        Point2D projectedPoint = projectPointOntoLine(mousePosInLocal, lineStart, lineEnd);

        if (projectedPoint.distance(lineStart) > lineStart.distance(lineEnd)) {
            return;
        }

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

        double minRadiusFactor = 0.5;
        double minSpacingFactor = 0.2;
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
        projectionScale = Math.max(0, Math.min(1, projectionScale));

        return lineStart.add(lineVector.multiply(projectionScale));
    }

    @FXML
    private void handleStartBtn(ActionEvent event) {
        if (isLaunched) return;

        VVelocityFieldLabel.setText(String.format("%.2f", physics.getVerticalVelocity()*(-1)));
        HVelocityFieldLabel.setText(String.format("%.2f", physics.getHorizontalVelocity()));

        physics.setOutOfBoundsCallback(isOutOfBounds -> {
            if (isOutOfBounds) {
                Platform.runLater(() -> {
                    showResetMessage();
                    handleResetBtn(new ActionEvent());
                });
            }
        });

        launchBall = new Circle();
        launchBall.setRadius(ball.getRadius());
        launchBall.setFill(ball.getFill());
        launchBall.setStroke(ball.getStroke());
        launchBall.setStrokeType(ball.getStrokeType());

        double angle = physics.getAngle();
        double angleRad = Math.toRadians(angle);

        Point2D ballScenePos = ball.localToScene(0, 0);
        Point2D ballPanePos = AnimationPane.sceneToLocal(ballScenePos);

        launchBall.setLayoutX(ballPanePos.getX());
        launchBall.setLayoutY(ballPanePos.getY());

        AnimationPane.getChildren().add(launchBall);

        ball.setVisible(false);

        double launchVelocity = physics.calculateLaunchVelocity(
                physics.getSpringConstant(),
                physics.getMass(),
                physics.getAmplitude()
        );

        double horizontalVelocity = launchVelocity * Math.cos(angleRad);
        double verticalVelocity = -launchVelocity * Math.sin(angleRad);

        physics.setObject(launchBall);
        physics.setInitialPosition(ballPanePos.getX(), ballPanePos.getY());
        physics.setVelocity(launchVelocity);
        physics.setHorizontalVelocity(horizontalVelocity);
        physics.setVerticalVelocity(verticalVelocity);
        physics.addBoundaryLine(RightBorderLine);

        System.out.println("\nStarting Launch\n");

        isLaunched = true;
        physics.play();
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
        physics.reset();

        VVelocityFieldLabel.setText("0.00");
        HVelocityFieldLabel.setText("0.00");

        if (launchBall != null) {
            AnimationPane.getChildren().remove(launchBall);
            launchBall = null;
        }

        ball.setVisible(true);

        ball.setLayoutX(originalSpringEndPosition.getX());
        ball.setLayoutY(originalSpringEndPosition.getY());

        physics.setObject(ball);

        amplitude.set(0);
        isLaunched = false;

        updateRealTimeHeight();
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

    private void showResetMessage() {

        if (resetMessage == null) {
            resetMessage = new Label("BALL RESET OUT OF BOUNDS");
            resetMessage.setTextFill(Color.RED);
            resetMessage.setFont(new Font("System Bold", 24));
            resetMessage.setOpacity(0); // Start invisible
        }

        resetMessage.setLayoutX((AnimationPane.getWidth() - resetMessage.getWidth()) / 2);
        resetMessage.setLayoutY((AnimationPane.getHeight() - resetMessage.getHeight()) / 2);

        if (!AnimationPane.getChildren().contains(resetMessage)) {
            AnimationPane.getChildren().add(resetMessage);
        }

        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), resetMessage);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), resetMessage);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        PauseTransition pause = new PauseTransition(Duration.millis(600));

        SequentialTransition sequence = new SequentialTransition(fadeIn, pause, fadeOut);
        sequence.setOnFinished(e -> AnimationPane.getChildren().remove(resetMessage));

        sequence.play();
    }
}
