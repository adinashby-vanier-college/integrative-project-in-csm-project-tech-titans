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
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.0/60.0), event -> {
            updatePosition();
            setElapsedTime(getElapsedTime() + 1.0/60.0);
            if (calculateY(getElapsedTime()) <= 0 && getElapsedTime() > 0)
                timeline.stop();
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

    public void updatePosition() {
        if (object == null) return;

        // Only update position if animation is running
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            double currentX = calculateX(getElapsedTime());
            double currentY = calculateY(getElapsedTime());

            object.setLayoutX(currentX);
            object.setLayoutY(currentY);
        }
    }

    public double getObjectHeight() {
        if (object == null) {
            return 0;
        }
        if (object instanceof Circle) {
            Circle circle = (Circle) object;
            return circle.getRadius() * 2;
        } else if (object instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) object;
            return rectangle.getWidth();
        } else {
            System.out.println("The object is using an unsupported Shape type: " + object.getClass().getSimpleName());
            return 0;
        }
    }

    public double getObjectWidth() {
        if (object == null) {
            return 0;
        }
        if (object instanceof Circle) {
            Circle circle = (Circle) object;
            return circle.getRadius() * 2;
        } else if (object instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) object;
            return rectangle.getWidth();
        } else {
            System.out.println("The object is using an unsupported Shape type: " + object.getClass().getSimpleName());
            return 0;
        }
    }

    public void setInitialPosition(double x, double y) {
        this.initialX = x;
        this.initialY = y;
        this.originalPosition = new Point2D(x, y);
    }

    public double calculateX(double time) {
        return initialX + (HorizontalVelocity * time);
    }


    public double calculateY(double time) {
        return initialY + (VerticalVelocity * time) - (0.5 * Gravity * time * time);
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

    public static double calculateVerticalVelocity(double Velocity, double AngleRad){
        return Velocity*Math.sin(AngleRad);
    }

    public static double calculateHorizontalVelocity(double Velocity, double AngleRad){
        return Velocity*Math.cos(AngleRad);
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

    public void play() {
        timeline.play();
    }

    public void reset() {
        // Stop the timeline
        timeline.stop();

        // Reset all motion parameters
        setElapsedTime(0);
        setVelocity(0);
        setVerticalVelocity(0);
        setHorizontalVelocity(0);

        // Reset position to original
        if (object != null && originalPosition != null) {
            object.setLayoutX(originalPosition.getX());
            object.setLayoutY(originalPosition.getY());
        }

        // Reset amplitude
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
