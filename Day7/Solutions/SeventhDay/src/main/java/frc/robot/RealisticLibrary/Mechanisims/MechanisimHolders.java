package frc.robot.RealisticLibrary.Mechanisims;

import java.util.ArrayList;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MechanisimHolders {
    public static final ArrayList<SimulatedMechanisim> simulatedMechanisims = new ArrayList<>();
    public static final ArrayList<SimulatedMotor> simulatedMotors = new ArrayList<>();
    public static boolean hasGeneratedMechanisims = false;

    private static boolean doesMotorListContain(int... ports) {
        for (int port : ports) {
            if (getMotorByPort(port) == null) {
                return false;
            }
        }
        return true;
    }

    private static boolean doesMotorListNotContain(int... ports) {
        for (int port : ports) {
            if (getMotorByPort(port) != null) {
                return false;
            }
        }
        return true;
    }

    private static SimulatedMotor getMotorByPort(int port) {
        for (SimulatedMotor motor : simulatedMotors) {
            if (motor.getPort() == port) {
                return motor;
            }
        }
        return null;
    }

    public static synchronized void generateMechanisims() {
        if (hasGeneratedMechanisims || simulatedMechanisims.size() != 0) {
            return;
        }
        hasGeneratedMechanisims = true;

        // if (doesMotorListContain(1, 2, 3, 4, 5, 6, 7, 8)
        // && doesMotorListNotContain(20)) {
        // Is from 2026 robot, create all mechanisims

        // LD 1, 2
        // RD 3, 4
        // Shooter 5
        // Feeder 6
        // Intake 7
        // Conveyor 8
        // Climber 10, 11
        // }

        if (doesMotorListContain(1, 2, 3, 4, 5, 6, 7, 8)) {
            // TODO: Swerve drive
        }

        if (doesMotorListContain(1, 2, 3, 4)) {
            // TODO: Get values from real drivetrain
            DifferentialDrive drive = new DifferentialDrive(
                    0.95, 5,
                    100, 0.038, 1,
                    2, 4,
                    new SimulatedMotor[] { getMotorByPort(1), getMotorByPort(2) },
                    new SimulatedMotor[] { getMotorByPort(3), getMotorByPort(4) });
            simulatedMechanisims.add(drive);
            System.out.println("Created drivetrain on ports 1, 2, 3, and 4");
        } else if (doesMotorListContain(1, 2)) {
            // TODO: Get values from real drivetrain
            DifferentialDrive drive = new DifferentialDrive(
                    0.95, 5,
                    100, 0.038, 1,
                    2, 1,
                    new SimulatedMotor[] { getMotorByPort(1) },
                    new SimulatedMotor[] { getMotorByPort(2) });
            simulatedMechanisims.add(drive);
            System.out.println("Created drivetrain on ports 1 and 2");
        }

        // TODO: get values from real flywheel
        double flywheelInertia = 0.005;
        if (doesMotorListContain(10, 11)) {
            // Create 2 motor flywheel
            Flywheel flywheel = new Flywheel(flywheelInertia, 1, false,
                    getMotorByPort(10), getMotorByPort(11));
            simulatedMechanisims.add(flywheel);
            System.out.println("Created flywheel on ports 10 and 11");
        } else if (doesMotorListContain(10)) {
            // Create 1 motor flywheel
            Flywheel flywheel = new Flywheel(flywheelInertia, 1, false,
                    getMotorByPort(10));
            simulatedMechanisims.add(flywheel);
            System.out.println("Created flywheel on port 10");
        }

        // TODO: get values from real intake rotator
        if (doesMotorListContain(20)) {
            // Create 1 motor arm
            Arm arm = new Arm(0, 0, 0,
                    0, 0, 0,
                    getMotorByPort(20));
            simulatedMechanisims.add(arm);
            System.out.println("Created arm on port 20");
        }
    }

    public static double getBatteryVoltage() {
        double[] currents = new double[simulatedMotors.size()];
        for (int i = 0; i < simulatedMotors.size(); i++) {
            currents[i] = simulatedMotors.get(i).getCurrent();
        }
        double voltage = BatterySim.calculateLoadedBatteryVoltage(12, 0.015, currents);
        // return 12; // Ignore battery voltage for now
        SmartDashboard.putNumber("Simulated Battery Voltage", voltage);
        return voltage;
    }
}
