package frc.robot.Real.Mechanisims;

import java.util.Arrays;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.Real.Motors.SimulatedMotor;

public class Flywheel extends SimulatedMechanisim {
    FlywheelSim flywheelSim;
    private double position = 0;

    public Flywheel(double momentOfInertia, double gearing, boolean isNeo550, SimulatedMotor... motors) {
        DCMotor motor = isNeo550
                ? DCMotor.getNeo550(motors.length)
                : DCMotor.getNEO(motors.length);

        this.flywheelSim = new FlywheelSim(
                LinearSystemId.createFlywheelSystem(
                        motor,
                        momentOfInertia,
                        gearing),
                motor);

        this.conversionFactor = gearing;
        this.motors = motors;

        for (SimulatedMotor _motor : motors) {
            _motor.setMechanisim(this);
        }
    }

    @Override
    public void simulationPeriodic() {
        flywheelSim.update(0.02);

        // Update position
        position += flywheelSim.getAngularVelocityRPM() * 0.02 / 60;

        flywheelSim.setInputVoltage(performCurrentLimiting(targetVoltage));
        if (getCurrent() >= 80) {
            // System.out.println("A motor is now on fire! " + Arrays.toString(motors) + getCurrent());
        }
    }

    @Override
    public double getRpm() {
        double rpm = flywheelSim.getAngularVelocityRPM();
        if (Double.isNaN(rpm)) {
            return 0;
        }
        return rpm / conversionFactor;
    }

    @Override
    public double getPosition() {
        return position / conversionFactor;
    }

    @Override
    public double getCurrent() {
        return flywheelSim.getCurrentDrawAmps() / motors.length;
    }

    @Override
    public double getTargetVoltage() {
        return flywheelSim.getInputVoltage();
    }
}
