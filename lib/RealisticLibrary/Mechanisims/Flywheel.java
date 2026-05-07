package frc.robot.RealisticLibrary.Mechanisims;

import java.util.Arrays;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public class Flywheel extends SimulatedMechanisim {
    FlywheelSim flywheelSim;
    private double position = 0.001;
    MechanismLigament2d flywheelDisplay;
    int ctr;

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

        Mechanism2d mech2d = new Mechanism2d(1, 1);
        flywheelDisplay = new MechanismLigament2d("Flywheel", 0.33, 90);
        mech2d.getRoot("flywheel", 0.5, 0.5) // center of screen
                .append(flywheelDisplay);
        SmartDashboard.putData("Flywheel Mechanism " + getMotorPorts(), mech2d);
    }

    @Override
    public void simulationPeriodic() {
        if (flywheelSim == null) {
            return;
        }

        flywheelSim.update(0.02);

        // Update position
        position += flywheelSim.getAngularVelocityRPM() * 0.02 / 60;

        if (position != 0) {
            flywheelDisplay.setAngle(position * 180 / Math.PI); // radians to degrees
        } else {
            System.out.println("Flywheel position is NaN, not updating display " + ctr++);
        }

        double volt = performCurrentLimiting(targetVoltage, motors[0]);
        volt = DriverStation.isEnabled() ? volt : 0;
        flywheelSim.setInputVoltage(volt);

        for (SimulatedMotor motor : motors) {
            double amps = getCurrent(motor);
            if (amps >= 80 && amps < motor.getCurrentLimit()) {
                System.out.println("A motor is now on fire! " + motor +
                        " is drawing " + amps + " amps");
            }
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
