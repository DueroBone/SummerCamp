package frc.robot.RealisticLibrary.Mechanisims;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public class DifferentialDrive extends SimulatedMechanisim {
    DifferentialDrivetrainSim driveSim;
    private double leftVoltage = 0;
    private double rightVoltage = 0;
    private final double circumference;

    private SimulatedMotor[] leftMotors;
    private SimulatedMotor[] rightMotors;

    /** All parameters are in SI units (meters / kg) */
    public DifferentialDrive(double momentOfInertiaRotation, double gearing, double robotMass, double wheelRadius,
            double trackWidth, SimulatedMotor[] leftMotors, SimulatedMotor[] rightMotors) {
        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;
        this.motors = new SimulatedMotor[leftMotors.length + rightMotors.length];
        System.arraycopy(leftMotors, 0, this.motors, 0, leftMotors.length);
        System.arraycopy(rightMotors, 0, this.motors, leftMotors.length, rightMotors.length);

        circumference = 2 * Math.PI * wheelRadius;

        for (SimulatedMotor _motor : motors) {
            _motor.setMechanisim(this);
        }

        driveSim = new DifferentialDrivetrainSim(
                DCMotor.getNEO(leftMotors.length), // num per side
                gearing,
                momentOfInertiaRotation,
                robotMass,
                wheelRadius,
                trackWidth,
                null);
    }

    @Override
    public void simulationPeriodic() {
        double leftVolt = 0;
        for (SimulatedMotor motor : leftMotors) {
            leftVolt += performCurrentLimiting(leftVoltage, motor);
        }
        leftVolt /= leftMotors.length;
        double rightVolt = 0;
        for (SimulatedMotor motor : rightMotors) {
            rightVolt += performCurrentLimiting(rightVoltage, motor);
        }
        rightVolt /= rightMotors.length;

        driveSim.setInputs(leftVolt, rightVolt);
        driveSim.update(0.02);
    }

    @Override
    public double getRpm(SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            return driveSim.getLeftVelocityMetersPerSecond() / circumference * 60;
        } else {
            return driveSim.getRightVelocityMetersPerSecond() / circumference * 60;
        }
    }

    @Override
    public double getPosition(SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            return driveSim.getLeftPositionMeters() / circumference;
        } else {
            return driveSim.getRightPositionMeters() / circumference;
        }
    }

    @Override
    public double getCurrent(SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            return driveSim.getLeftCurrentDrawAmps() / leftMotors.length;
        } else {
            return driveSim.getRightCurrentDrawAmps() / rightMotors.length;
        }
    }

    @Override
    public double getTargetVoltage(SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            return leftVoltage;
        } else {
            return rightVoltage;
        }
    }

    private boolean isLeftMotor(SimulatedMotor motor) {
        for (SimulatedMotor m : leftMotors) {
            if (m == motor) {
                return true;
            }
        }
        return false;
    }
}
