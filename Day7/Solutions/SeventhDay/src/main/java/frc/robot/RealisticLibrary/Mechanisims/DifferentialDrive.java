package frc.robot.RealisticLibrary.Mechanisims;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public class DifferentialDrive extends SimulatedMechanisim {
    DifferentialDrivetrainSim driveSim;
    private double[] leftVoltages;
    private double[] rightVoltages;
    private final double circumference;
    private final double trackWidth;
    private double velocityResistance; // volts per (meter per second)
    private double turnResistance; // volts per (radian per second)
    private DifferentialDriveOdometry odometry;
    private Field2d field = new Field2d();

    private SimulatedMotor[] leftMotors;
    private SimulatedMotor[] rightMotors;

    /** All parameters are in SI units (meters / kg) */
    public DifferentialDrive(double momentOfInertiaRotation, double gearing, double robotMass,
            double wheelRadius, double trackWidth, double velocityResistance, double turnResistance,
            SimulatedMotor[] leftMotors, SimulatedMotor[] rightMotors) {
        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;
        this.motors = new SimulatedMotor[leftMotors.length + rightMotors.length];
        System.arraycopy(leftMotors, 0, this.motors, 0, leftMotors.length);
        System.arraycopy(rightMotors, 0, this.motors, leftMotors.length, rightMotors.length);
        this.leftVoltages = new double[leftMotors.length];
        this.rightVoltages = new double[rightMotors.length];

        circumference = 2 * Math.PI * wheelRadius;
        this.trackWidth = trackWidth;
        this.velocityResistance = velocityResistance;
        this.turnResistance = turnResistance;

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

        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0), 0, 0);
        field.setRobotPose(odometry.getPoseMeters());
        SmartDashboard.putData("Robot field position", field);
    }

    @Override
    public void simulationPeriodic() {
        if (driveSim == null) {
            return;
        }

        double leftVolt = 0;
        for (SimulatedMotor motor : leftMotors) {
            leftVolt += performCurrentLimiting(leftVoltages[subIndexOfMotor(motor)],
                    motor);
            // leftVolt += motor.getVoltage();
        }
        leftVolt /= leftMotors.length;
        double rightVolt = 0;
        for (SimulatedMotor motor : rightMotors) {
            rightVolt += performCurrentLimiting(rightVoltages[subIndexOfMotor(motor)],
                    motor);
            // rightVolt += motor.getVoltage();
        }
        rightVolt /= rightMotors.length;

        // Simulate driving resistance
        if (Math.abs(driveSim.getLeftVelocityMetersPerSecond() + driveSim.getRightVelocityMetersPerSecond()) > 0.1) {
            leftVolt -= velocityResistance
                    * Math.signum(driveSim.getLeftVelocityMetersPerSecond());
            rightVolt -= velocityResistance
                    * Math.signum(driveSim.getRightVelocityMetersPerSecond());
        }

        // Simulate turning resistance
        double turnRate = (driveSim.getRightVelocityMetersPerSecond() - driveSim.getLeftVelocityMetersPerSecond())
                / trackWidth;
        leftVolt += turnRate * turnResistance;
        rightVolt -= turnRate * turnResistance;

        driveSim.setInputs(leftVolt, rightVolt);
        driveSim.update(0.02);

        odometry.update(driveSim.getHeading(),
                driveSim.getLeftPositionMeters(),
                driveSim.getRightPositionMeters());
        field.setRobotPose(odometry.getPoseMeters());

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
        if (driveSim == null) {
            return 0;
        }

        if (isLeftMotor(motor)) {
            return driveSim.getLeftVelocityMetersPerSecond() / circumference * 60;
        } else {
            return driveSim.getRightVelocityMetersPerSecond() / circumference * 60;
        }
    }

    @Override
    public double getPosition(SimulatedMotor motor) {
        if (driveSim == null) {
            return 0;
        }

        if (isLeftMotor(motor)) {
            return driveSim.getLeftPositionMeters() / circumference;
        } else {
            return driveSim.getRightPositionMeters() / circumference;
        }
    }

    @Override
    public double getCurrent(SimulatedMotor motor) {
        if (driveSim == null) {
            return 0;
        }

        if (isLeftMotor(motor)) {
            return driveSim.getLeftCurrentDrawAmps() / leftMotors.length;
        } else {
            return driveSim.getRightCurrentDrawAmps() / rightMotors.length;
        }
    }

    @Override
    public double getTargetVoltage(SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            return leftVoltages[subIndexOfMotor(motor)];
        } else {
            return rightVoltages[subIndexOfMotor(motor)];
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

    public void setVoltage(double voltage, SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            leftVoltages[subIndexOfMotor(motor)] = voltage;
        } else {
            rightVoltages[subIndexOfMotor(motor)] = voltage;
        }
    }

    private int subIndexOfMotor(SimulatedMotor motor) {
        if (isLeftMotor(motor)) {
            for (int i = 0; i < leftMotors.length; i++) {
                if (leftMotors[i] == motor) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < rightMotors.length; i++) {
                if (rightMotors[i] == motor) {
                    return i;
                }
            }
        }
        return -1;
    }
}
