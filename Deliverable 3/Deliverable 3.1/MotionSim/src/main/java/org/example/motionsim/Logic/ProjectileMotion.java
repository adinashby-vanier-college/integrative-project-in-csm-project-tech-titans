package org.example.motionsim.Logic;
/*
public class ProjectileMotion {
    private double velocityX, velocityY, positionX, positionY, gravity = 9.81;
    private double timeStep = 0.01;
    public ProjectileMotion(double velocity, double angle, double height) {
        this.velocityX = velocity * Math.cos(radians);
        this.velocityY = velocity * Math.sin(radians);
        this.positionX = 0;
        this.positionY = height;
    }
    public void updateMotion() {
        positionX += velocityX * timeStep;
        velocityY -= gravity * timeStep;
        positionY += velocityY * timeStep;
    }

public class ProjectileMotion {
    private double velocityX, velocityY, positionX,positionY,gravity = 9.81;
    private double timeStep = 0.01;
    public ProjectileMotion(double velocity, double angle, double height) {
        double radians = Math.toRadians(angle);
        this.velocityX = velocity * Math.cos(radians);
        this.velocityY = velocity * Math.sin(radians);
        this.positionX = 0;
        this.positionY = height;
    }
    public void updateMotion() {
        positionX += velocityX * timeStep;
        velocityY -= gravity * timeStep;
        positionY += velocityY * timeStep;

        if(positionY < 0) {
            positionY = 0;
            velocityY = 0;
        }
    }
    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    public void setGravity(double g) {
        this.gravity = g;
    }
 }
*/

public class ProjectileMotion {
    private double velocity;
    private double angle;
    private double gravity = 9.81;
    private double time = 0.0;
    private double positionX, positionY = 0.0;

    public void launch(double velocity,double angle) {
        this.velocity = velocity;
        this.angle = Math.toRadians(angle);
        this.time = 0.0;
        this.positionX = 0.0;
        this.positionY = 0.0;
    }
    public void update() {
        time += 0.1;
        positionX = velocity * Math.cos(angle) * time;
        positionY = velocity * Math.sin(angle) * time - 0.5* gravity* time*time;
        if (positionY < 0) {
            positionY = 0;
        }
    }
    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
}