package org.example.motionsim.Model;

public class SpringPhysics {
    public static double calculateSpringPotentialEnergy(double springConstant, double amplitude) {
        return 0.5 * springConstant * amplitude * amplitude;
    }

    public static double calculateKineticEnergy(double mass, double velocity) {
        return 0.5 * mass * velocity * velocity;
    }

    public static double calculateLaunchVelocity(double springConstant, double mass, double amplitude) {
        return amplitude * Math.sqrt(springConstant / mass);
    }
}
