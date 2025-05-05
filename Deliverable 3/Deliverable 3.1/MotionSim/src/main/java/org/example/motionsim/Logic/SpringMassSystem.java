package org.example.motionsim.Logic;
/*
public class SpringMassSystem {
    private double mass, springConstant,displacement, velocity;
    private double timeStep = 0.01;
    public SpringMassSystem(double mass, double k, double x ) {
        this.mass = mass;
        this.springConstant = k;
        this.displacement = x ;
        this.velocity = 0;
    }
    public void update() {
        double acceleration = -springConstant* displacement / mass;
        velocity += acceleration * timeStep;
        displacement += velocity * timeStep;
    }
    public double getKineticEnergy() {
        return 0.5 * mass * velocity * velocity;
    }
    public double getPotentialEnergy() {
    return 0.5 * springConstant * displacement * displacement;
    }
    public double TotalEnergy() {
        return getKineticEnergy() + getPotentialEnergy();
    }
    public double getDisplacement() {
        return displacement;
    }
    public void setSpringConstant(double k) {
        this.springConstant = k;
    }
}
*/
public class SpringMassSystem {
    private double mass  = 1.0;
    private double springConstant = 50.0;
    private double displacement = 0.0;
    private double velocity = 0.0;
    private double timeStep = 0.1;

    public void applyForce(double force) {
        double acceleration = force / mass;
        velocity += acceleration * timeStep;
    }
    public void update() {
        double force = -springConstant *displacement;
        applyForce(force);
        displacement += velocity * timeStep;
    }
    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }
    public double getDisplacement() {
        return displacement;
    }
}