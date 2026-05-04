package frc.robot.RealisticLibrary.Mechanisims;

import java.util.ArrayList;

import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

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

    private static SimulatedMotor getMotorByPort(int port) {
        for (SimulatedMotor motor : simulatedMotors) {
            if (motor.getPort() == port) {
                return motor;
            }
        }
        return null;
    }

    public static void generateMechanisims() {
        if (hasGeneratedMechanisims || simulatedMechanisims.size() != 0) {
            return;
        }

        if (doesMotorListContain(1, 2, 3, 4, 5, 6, 7, 8)
                && !doesMotorListContain(20)) {
            // Is from 2026 robot, create all mechanisims

            // LD 1, 2
            // RD 3, 4
            // Shooter 5
            // Feeder 6
            // Intake 7
            // Conveyor 8
            // Climber 10, 11
        }
        if (doesMotorListContain(1, 2, 3, 4)) {
            // Create 4 motor drivetrain
        } else if (doesMotorListContain(1, 2)) {
            // Create 2 motor drivetrain
        }

        // TODO: get values from real flywheel
        double flywheelInertia = 0.005;
        if (doesMotorListContain(10, 11)) {
            // Create 2 motor flywheel
            Flywheel flywheel = new Flywheel(flywheelInertia, 1, false,
                    getMotorByPort(10), getMotorByPort(11));
            simulatedMechanisims.add(flywheel);
        } else if (doesMotorListContain(10)) {
            // Create 1 motor flywheel
            Flywheel flywheel = new Flywheel(flywheelInertia, 1, false,
                    getMotorByPort(10));
            simulatedMechanisims.add(flywheel);
        }

        // TODO: get values from real intake rotator
        if (doesMotorListContain(20)) {
            // Create 1 motor arm
            Arm arm = new Arm(0, 0, 0,
                    0, 0, 0,
                    getMotorByPort(20));
            simulatedMechanisims.add(arm);
        }
        hasGeneratedMechanisims = true;
    }
}
