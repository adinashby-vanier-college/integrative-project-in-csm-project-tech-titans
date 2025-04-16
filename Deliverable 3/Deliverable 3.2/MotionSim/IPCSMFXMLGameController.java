package org.example.motionsim.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.*;
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
    private Arc SpringAdjusterPath;
    private MediaPlayer mediaPlayer;

    private static double CenterX = -20;
    private static double CenterY = 99;
    private static double RadiusX = 100;
    private static double RadiusY = 100;
    private static double StartAngle = 0;
    private static double Length = 90;
    private static double springConstant = 10;
    private double angle;
    private double mass = 5;
    private double gravity = 9.8;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        double initialAngle = Math.toRadians(StartAngle);
        groupSpring.setLayoutX(CenterX + RadiusX * Math.cos(initialAngle));
        groupSpring.setLayoutY(CenterY - RadiusY * Math.sin(initialAngle));

        attachBallToLineEnd();

        AmplitudeSlider.setMin(0);
        AmplitudeSlider.setMax(maxSpringDistance);
        amplitude.bindBidirectional(AmplitudeSlider.valueProperty());
        amplitude.addListener((obs, oldVal, newVal) -> {
            AmplitudeFieldLabel.setText(String.format("%.2f", newVal.doubleValue()));
            updateBallPosition(newVal.doubleValue());
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
        MassSlider.setMax(100);
        MassSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            MassFieldLabel.setText(String.format("%.2f", newValue.doubleValue()));
        });

        AngleSlider.setMin(0);
        AngleSlider.setMax(75);
        AngleFieldLabel.textProperty().bind(AngleSlider.valueProperty().asString("%.2f"));
        Rotate rotate = new Rotate();
        groupSpring.getTransforms().add(rotate);
        rotate.setPivotX(30);
        rotate.setPivotY(89);
        AngleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            rotate.setAngle(newValue.doubleValue()*(-1));
        });
        playMainMenuSong();
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

        ball.setLayoutX(lineEndXInPane);
        ball.setLayoutY(lineEndYInPane);

        // These lines find the initial ball position and calculate the line direction
        initialBallPosition = new Point2D(lineEndXInPane, lineEndYInPane);
        lineStart = new Point2D(springPath.getLayoutX() + springPath.getStartX(),
                springPath.getLayoutY() + springPath.getStartY());
        lineEnd = initialBallPosition;

        maxSpringDistance = lineStart.distance(lineEnd);
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
        double currentAmplitude = amplitude.get();
        double velocity = SpringPhysics.calculateLaunchVelocity(springConstant, mass, currentAmplitude);
        double angleDeg = AngleSlider.getValue();
        double angleRad = Math.toRadians(angleDeg);

        double xVelocity = velocity * Math.cos(angleRad);
        double yVelocity = -velocity * Math.sin(angleRad);

        Timeline ballTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            // 16 ms = 60 fps
            double dt = 0.016;

            double x = ball.getLayoutX();
            double y = ball.getLayoutY();

            x += xVelocity * dt;
            y += yVelocity * dt;

            ball.setLayoutX(x);
            ball.setLayoutY(y);
        }));
        ballTimeline.setCycleCount(Timeline.INDEFINITE);

        // This animates the spring returning back to its original state when launch happens
        KeyValue amplitudeToZero = new KeyValue(amplitude, 0, Interpolator.EASE_OUT);
        KeyFrame springResetFrame = new KeyFrame(Duration.millis(300), amplitudeToZero);
        Timeline springReturn = new Timeline(springResetFrame);

        ballTimeline.play();
        springReturn.play();
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
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
    public void handleSpringAdjuster(MouseEvent event) {
        double dx = event.getX() - CenterX;
        double dy = event.getY() - CenterY;
        angle = Math.toDegrees(Math.atan2(-dy, dx));

        if (angle < 0) {
            angle += 360;
        }

        if (angle >= StartAngle && angle <= StartAngle + Length) {
            double radianAngle = Math.toRadians(angle);
            groupSpring.setLayoutX(CenterX + RadiusX * Math.cos(radianAngle));
            groupSpring.setLayoutY(CenterY - RadiusY * Math.sin(radianAngle));
        }
    }


    @FXML
    private void handleMenuGoToSettings() {
       /* try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/SettingsScreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) MenuBar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
        try {
            stopMusic(); // Stop the main menu song before switching
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
    private void playMainMenuSong() {
        try {
            String musicPath = getClass().getResource("/songs/MainMenuSong.mp3").toString();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the song
            mediaPlayer.setVolume(0.5); // Set initial volume (adjust as needed)
            mediaPlayer.play();
            System.out.println("Playing Main Menu Song...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing Main Menu Song: " + e.getMessage());
        }
    }
    // Method to stop any currently playing song
    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
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

