package frc.robot.RealisticLibrary.Mechanisims;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public abstract class SimulatedMechanisim extends SubsystemBase {
    SimulatedMotor[] motors;
    protected double conversionFactor;
    protected double targetVoltage = 0;

    @Override
    public abstract void simulationPeriodic();

    public void setVoltage(double voltage, SimulatedMotor motor) {
        this.targetVoltage = voltage;
    }

    public abstract double getRpm(SimulatedMotor motor);

    /** Returns in rotations */
    public abstract double getPosition(SimulatedMotor motor);

    public abstract double getCurrent(SimulatedMotor motor);

    public abstract double getTargetVoltage(SimulatedMotor motor);

    protected double performCurrentLimiting(double voltage, SimulatedMotor motor) {
        // If we're drawing more current than the average current limit,
        // scale down the voltage to simulate current limiting behavior.
        double scale = 1.0;
        if (getCurrent(motor) > motor.getCurrentLimit()) {
            scale = motor.getCurrentLimit() / getCurrent(motor);
            // System.out.println("Current limiting! Current: " + getCurrent(motor) + "
            // Limit: "
            // + motor.getCurrentLimit() + " Scale: " + scale);
        }
        return Math.min(voltage, MechanisimHolders.getBatteryVoltage()) * scale;
        // return voltage;
    }

    protected String getMotorPorts() {
        StringBuilder sb = new StringBuilder();
        for (SimulatedMotor motor : motors) {
            sb.append(motor.getPort()).append(" ");
        }
        return sb.toString().trim();
    }
}
