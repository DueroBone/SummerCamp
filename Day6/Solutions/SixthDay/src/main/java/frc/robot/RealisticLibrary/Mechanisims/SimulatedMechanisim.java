package frc.robot.RealisticLibrary.Mechanisims;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public abstract class SimulatedMechanisim extends SubsystemBase {
    SimulatedMotor[] motors;
    protected double conversionFactor;
    protected double targetVoltage = 0;

    @Override
    public abstract void simulationPeriodic();

    public final void setVoltage(double voltage) {
        this.targetVoltage = voltage;
    }

    public abstract double getRpm();

    /** Returns in rotations */
    public abstract double getPosition();

    public abstract double getCurrent();

    public abstract double getTargetVoltage();

    protected double performCurrentLimiting(double voltage) {
        double aveCurrentLimit = 0;
        for (SimulatedMotor motor : motors) {
            aveCurrentLimit += motor.getCurrentLimit();
        }
        aveCurrentLimit /= motors.length;

        // If we're drawing more current than the average current limit,
        // scale down the voltage to simulate current limiting behavior.
        double scale = 1.0;
        if (getCurrent() > aveCurrentLimit) {
            scale = aveCurrentLimit / getCurrent();
            // System.out.println("Current limiting! Current: " + getCurrent() + " Limit: " + aveCurrentLimit + " Scale: " + scale);
        }
        return voltage * scale;
        // return voltage;
    }
}
