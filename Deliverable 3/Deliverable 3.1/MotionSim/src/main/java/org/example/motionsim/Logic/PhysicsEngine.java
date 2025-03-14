package org.example.motionsim.Logic;
/*
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class PhysicsEngine {
    private Canvas canvas = new Canvas(1450,500);
    private SpringMassSystem spring = new SpringMassSystem(1.0,5.0,0.2);
    private ProjectileMotion projectileMotion = new ProjectileMotion(10,45,0);
    double timeElasped = 0;
    public PhysicsEngine() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                spring.update();
                projectileMotion.updateMotion();
                draw();
                timeElasped += 0.01;
            }
        }.start();
    }
    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLUE);
        gc.strokeLine(400,100,400,100 + spring.getDisplacement() * 150);
        gc.setFill(Color.RED);
        gc.fillOval(390,100 + spring.getDisplacement()* 150,20,20);
        gc.setFill(Color.GREEN);
        gc.fillOval(200 + projectileMotion.getPositionX()* 10, 500 - projectileMotion.getPositionY()* 10, 10, 10);
    }
    public Canvas getCanvas () {
        return canvas;
    }
    public SpringMassSystem getSpringSystem() {
        return spring;
    }
    public ProjectileMotion getProjectileMotion() {
        return projectileMotion;
    }
}
*/
public class PhysicsEngine {
    private SpringMassSystem springMassSystem;
    private ProjectileMotion projectileMotion;
    public PhysicsEngine() {
        springMassSystem = new SpringMassSystem();
        projectileMotion = new ProjectileMotion();
    }
    public SpringMassSystem getSpringMassSystem() {
        return springMassSystem;
    }
    public ProjectileMotion getProjectileMotion() {
        return projectileMotion;
    }
    public void updatePhysics() {
        springMassSystem.update();
        projectileMotion.update();
    }
}