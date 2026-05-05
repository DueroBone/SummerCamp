package frc.robot.RealisticLibrary.Mechanisims;

import java.util.Arrays;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

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
        if (flywheelSim == null) {
            return;
        }

        flywheelSim.update(0.02);

        // Update position
        position += flywheelSim.getAngularVelocityRPM() * 0.02 / 60;

        double volt = performCurrentLimiting(targetVoltage, motors[0]);
        volt = DriverStation.isEnabled() ? volt : 0;
        flywheelSim.setInputVoltage(volt);
        if (getCurrent(motors[0]) >= 80 && getCurrent(motors[0]) < motors[0].getCurrentLimit()) {
            System.out.println("A motor is now on fire! " + Arrays.toString(motors) +
                    ", drawing " + getCurrent(motors[0]) + " amps");
        }
    }

    @Override
    public double getRpm(SimulatedMotor motor) {
        double rpm = flywheelSim.getAngularVelocityRPM();
        if (Double.isNaN(rpm)) {
            return 0;
        }
        return rpm / conversionFactor;
    }

    @Override
    public double getPosition(SimulatedMotor motor) {
        return position / conversionFactor;
    }

    @Override
    public double getCurrent(SimulatedMotor motor) {
        return flywheelSim.getCurrentDrawAmps() / motors.length;
    }

    @Override
    public double getTargetVoltage(SimulatedMotor motor) {
        return flywheelSim.getInputVoltage();
    }
}
