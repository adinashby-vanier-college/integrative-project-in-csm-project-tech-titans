package org.example.motionsim.Model;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.example.motionsim.Controller.IPCSMFXMLGameController;

import java.util.function.Consumer;

public class SpringPhysics {

    private Timeline timeline;
    private DoubleProperty elapsedTime;
    private static volatile SpringPhysics instance;
    private double Amplitude;
    private double Angle;
    private double Mass;
    private double Gravity;
    private double SpringConstant;
    private double Velocity;
    private double VerticalVelocity;
    private double HorizontalVelocity;
    private double AngleRad;
    private double Height;
    private double initialX;
    private double initialY;
    private Point2D originalPosition;
    private double lastX;
    private double lastY;
    private Consumer<Point2D> velocityUpdateCallback;
    private Consumer<Boolean> outOfBoundsCallback;

    private Shape object;

    public Shape getObject() {
        return object;
    }

    public void setObject(Shape object) {
        this.object = object;
        updatePosition();
    }

    public final void setElapsedTime(double value) {
        elapsedTimeProperty().set(value);
    }

    public final double getElapsedTime() {
        return elapsedTime == null ? 0.0 : elapsedTime.get();
    }

    public final DoubleProperty elapsedTimeProperty() {
        if (elapsedTime == null) {
            elapsedTime = new DoublePropertyBase(0.0) {

                @Override
                public Object getBean() {
                    return SpringPhysics.this;
                }

                @Override
                public String getName() {
                    return "elapsedTime";
                }
            };
        }
        return elapsedTime;
    }

    SpringPhysics() {
        timeline = new Timeline(new KeyFrame(Duration.seconds((1.0/60.0)/1.5), event -> {
            updatePosition();
            setElapsedTime(getElapsedTime() + 1.0/60.0);

            if (object != null && object.getParent() != null) {

                double currentX = calculateX(getElapsedTime());
                double currentY = calculateY(getElapsedTime());
                double radius = (object instanceof Circle) ? ((Circle) object).getRadius() : 0;

                double parentWidth = object.getParent().getBoundsInLocal().getWidth();
                double parentHeight = object.getParent().getBoundsInLocal().getHeight();

                boolean outOfBounds = (currentX - radius <= 0) ||
                        (currentX + radius >= parentWidth) ||
                        (currentY - radius <= 0) ||
                        (currentY + radius >= parentHeight);

                if (outOfBounds && getElapsedTime() > 0) {
                    timeline.stop();
                    if (outOfBoundsCallback != null) {
                        outOfBoundsCallback.accept(true);
                    }
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public static SpringPhysics getInstance() {
        if (instance == null) {
            synchronized (SpringPhysics.class) {
                if (instance == null) {
                    instance = new SpringPhysics();
                }
            }
        }
        return instance;
    }

    public void setOutOfBoundsCallback(Consumer<Boolean> callback) {
        this.outOfBoundsCallback = callback;
    }

    public void updatePosition() {
        if (object == null || timeline.getStatus() != Animation.Status.RUNNING) return;

        double currentTime = getElapsedTime();
        double currentX = calculateX(currentTime);
        double currentY = calculateY(currentTime);

        double currentHorizontalVelocity = HorizontalVelocity;
        double currentVerticalVelocity = VerticalVelocity + (Gravity * currentTime);

        if (velocityUpdateCallback != null) {
            velocityUpdateCallback.accept(new Point2D(currentHorizontalVelocity, currentVerticalVelocity));
        }

        object.setLayoutX(currentX);
        object.setLayoutY(currentY);

        if (outOfBoundsCallback != null) {
            double radius = 0;
            if (object instanceof Circle) {
                radius = ((Circle) object).getRadius();
            }
            outOfBoundsCallback.accept(currentX - radius < 0 ||
                    currentX + radius > object.getParent().getBoundsInLocal().getWidth() ||
                    currentY - radius < 0 ||
                    currentY + radius > object.getParent().getBoundsInLocal().getHeight());
        }
    }

    public void setInitialPosition(double x, double y) {
        this.initialX = x;
        this.initialY = y;
    }

    public void synchronizeWithBallPosition(double x, double y) {
        setInitialPosition(x, y);
        setElapsedTime(0);
    }

    public double calculateX(double time) {
        double newX = initialX + (HorizontalVelocity * time);
        return newX;
    }

    public double calculateY(double time) {
        double newY = initialY + (VerticalVelocity * time) + (0.5 * Gravity * time * time);
        return newY;
    }

    public static double calculateSpringPotentialEnergy(double springConstant, double amplitude) {
        return 0.5 * springConstant * amplitude * amplitude;
    }

    public static double calculateKineticEnergy(double mass, double velocity) {
        return 0.5 * mass * velocity * velocity;
    }

    public static double calculateLaunchVelocity(double springConstant, double mass, double amplitude) {
        return amplitude * Math.sqrt(springConstant / mass);
    }

    public static double calculateAngleRad(double Angle){
        return Math.toRadians(Angle);
    }

    public void setAmplitude(double Amplitude){
        System.out.println("Setting Amplitude: " + Amplitude);
        this.Amplitude = Amplitude;
    }

    public double getAmplitude() {
        System.out.println("Getting Amplitude: " + Amplitude);
        return Amplitude;
    }

    public void setMass(double Mass){
        System.out.println("Setting Mass: " + Mass);
        this.Mass = Mass;
    }

    public double getMass() {
        System.out.println("Getting Mass: " + Mass);
        return Mass;
    }

    public void setAngle(double Angle){
        System.out.println("Setting Angle: " + Angle);
        this.Angle = Angle;
    }

    public double getAngle() {
        System.out.println("Getting Angle: " + Angle);
        return Angle;
    }

    public void setGravity(double Gravity){
        System.out.println("Setting Gravity: " + Gravity);
        this.Gravity = Gravity;
    }

    public double getGravity() {
        System.out.println("Getting Gravity: " + Gravity);
        return Gravity;
    }

    public void setSpringConstant(double SpringConstant){
        System.out.println("Setting SpringConstant: " + SpringConstant);
        this.SpringConstant = SpringConstant;
    }

    public double getSpringConstant() {
        System.out.println("Getting SpringConstant: " + SpringConstant);
        return SpringConstant;
    }

    public void setVelocity(double Velocity){
        System.out.println("Setting Velocity: " + Velocity);
        this.Velocity = Velocity;
    }

    public double getVelocity() {
        System.out.println("Getting Velocity: " + Velocity);
        return Velocity;
    }

    public void setVerticalVelocity(double VerticalVelocity){
        System.out.println("Setting VerticalVelocity: " + VerticalVelocity);
        this.VerticalVelocity = VerticalVelocity;
    }

    public double getVerticalVelocity() {
        System.out.println("Getting VerticalVelocity: " + VerticalVelocity);
        return VerticalVelocity;
    }

    public void setHorizontalVelocity(double HorizontalVelocity){
        System.out.println("Setting HorizontalVelocity: " + HorizontalVelocity);
        this.HorizontalVelocity = HorizontalVelocity;
    }

    public double getHorizontalVelocity() {
        System.out.println("Getting HorizontalVelocity: " + HorizontalVelocity);
        return HorizontalVelocity;
    }

    public void setAngleRad(double AngleRad){
        System.out.println("Setting AngleRad: " + AngleRad);
        this.AngleRad = AngleRad;
    }

    public double getAngleRad() {
        System.out.println("Getting AngleRad: " + AngleRad);
        return AngleRad;
    }

    public void setHeight(double Height){
        System.out.println("Setting Height: " + Height);
        this.Height = Height;
    }

    public double getHeight() {
        System.out.println("Getting Height: " + Height);
        return Height;
    }

    public void setVelocityUpdateCallback(Consumer<Point2D> callback) {
        this.velocityUpdateCallback = callback;
    }

    public void play() {
        lastX = 0;
        lastY = 0;
        timeline.play();
    }

    public void reset() {
        timeline.stop();
        setElapsedTime(0);

        setVelocity(0);
        setVerticalVelocity(0);
        setHorizontalVelocity(0);

        if (object != null && originalPosition != null) {
            object.setLayoutX(originalPosition.getX());
            object.setLayoutY(originalPosition.getY());
            synchronizeWithBallPosition(originalPosition.getX(), originalPosition.getY());
        }

        setAmplitude(0);
    }
    public void jumpTo(double destination) {
        timeline.pause();
        setElapsedTime(destination);;
        updatePosition();
    }

    public double getTimeOfFlight() {
        System.out.println("Calculating time of flight...");
        if (Gravity <= 0) {
            throw new IllegalArgumentException("Gravitational acceleration must be positive.");
        }
        if (VerticalVelocity == 0 && Height == 0) {
            System.out.println("No vertical motion. Time of flight is 0.");
            return 0;
        }
        double g = Gravity;
        double vy = VerticalVelocity;
        double h = Height;
        double discriminant = Math.pow(vy, 2) + 2 * g * h;
        if (discriminant < 0) {
            throw new IllegalStateException("Invalid state: Discriminant is negative, cannot compute time of flight.");
        }
        double time = (vy + Math.sqrt(discriminant)) / g;
        System.out.println("Time of flight calculated: " + time + " seconds.");
        return time;
    }
}
