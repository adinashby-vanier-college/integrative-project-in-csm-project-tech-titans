package org.example.motionsim.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TimelineWrapper {

    private Circle Circle;
    private double VVelocity;
    private double HVelocity;
    private double Gravity;
    private double TimeOfFlight;
    private double Scale;

    public TimelineWrapper(Circle circle, double vVelocity, double hVelocity, double gravity,
                           double timeOfFlight, double scale) {
        this.Circle = circle;
        this.VVelocity = vVelocity;
        this.HVelocity = hVelocity;
        this.Gravity = gravity;
        this.TimeOfFlight = timeOfFlight;
        this.Scale = scale;
    }

    public Timeline buildTimeline() {

        double frameRate = 60; //fps or hz
        double timeStep = 1 / frameRate;

        Timeline timeline = new Timeline();
        for (double time = 0; time <= TimeOfFlight; time += timeStep) {
            double finalTime = time;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), event -> updatePosition(finalTime));

            timeline .getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        return timeline;
    }

    private void updatePosition(double time) {
        double xTranslation = HVelocity * time;
        double yTranslation = (VVelocity * time) - (0.5 * Gravity * time * time);
        Circle.setTranslateX(xTranslation * Scale);
        Circle.setTranslateY(yTranslation * Scale);
    }

}
