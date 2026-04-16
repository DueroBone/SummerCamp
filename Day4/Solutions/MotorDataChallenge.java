package Day4.Solutions;

import lib.Simplified.Motors.Motors;
import lib.Simplified.Motors.RealisticMotor;
import lib.Simplified.Motors.SimpleMotor;


public class MotorDataChallenge {
    /*
     * Id 1: Left drive 1
     * Id 2: Left drive 2
     * Id 3: Right drive 1
     * Id 4: Right drive 2
     * 
     * Id 10: Shooter left
     * Id 11: Shooter right
     * 
     * Find average shooter speed, and average drive speed.
     * Find if there's any weird things going on with the motors.
     */
    public static void main(String[] args) {
        SimpleMotor[] motors = Motors.getSimpleMotorData();

        // Print out all the motor data to see if there's any weird things going on with the motors
        for (SimpleMotor motor : motors) {
            System.out.println("Motor " + motor.getMotorId() + ": " + 
            motor.getRpm() + " RPM, " + motor.getCurrent() + 
            " A, " + motor.getVoltage() + " V");
        }

        // Get the correct motors by their ids
        SimpleMotor driveLeft1 = getMotorById(motors, 1);
        SimpleMotor driveLeft2 = getMotorById(motors, 2);
        SimpleMotor driveRight1 = getMotorById(motors, 3);
        SimpleMotor driveRight2 = getMotorById(motors, 4);
        SimpleMotor shooterLeft = getMotorById(motors, 10);
        SimpleMotor shooterRight = getMotorById(motors, 11);

        // Other way of getting drive motors using a loop
        int[] driveMotorIds = {1, 2, 3, 4};
        SimpleMotor[] driveMotors = new SimpleMotor[4];
        for (int i = 0; i < driveMotorIds.length; i++) {
            driveMotors[i] = getMotorById(motors, driveMotorIds[i]);
        }

        double averageShooterSpeed = (positive(shooterLeft.getRpm()) +
                                    positive(shooterRight.getRpm())) / 2.0;
        double averageDriveSpeed = 0;
        for (SimpleMotor driveMotor : driveMotors) {
            averageDriveSpeed += positive(driveMotor.getRpm());
        }
        averageDriveSpeed /= driveMotors.length;

        System.out.println("Average shooter speed: " + averageShooterSpeed + " RPM");
        System.out.println("Average drive speed: " + averageDriveSpeed + " RPM");

        // ============================ Advanced version: ============================ //

        RealisticMotor[] realisticMotors = Motors.getRealisticMotorData();

        for (RealisticMotor motor : realisticMotors) {
            System.out.println("Motor " + motor.getDeviceId() + ": " + 
            motor.getEncoder().getVelocity() + " RPM, " + motor.getOutputCurrent() + 
            " A, " + motor.getBusVoltage() + " V");
        }

        RealisticMotor driveLeft1Adv = getMotorById(realisticMotors, 1);
        RealisticMotor driveLeft2Adv = getMotorById(realisticMotors, 2);
        RealisticMotor driveRight1Adv = getMotorById(realisticMotors, 3);
        RealisticMotor driveRight2Adv = getMotorById(realisticMotors, 4);
        RealisticMotor shooterLeftAdv = getMotorById(realisticMotors, 10);
        RealisticMotor shooterRightAdv = getMotorById(realisticMotors, 11);

        double averageShooterSpeedAdv = (positive(shooterLeftAdv.getEncoder().getVelocity()) +
                                        positive(shooterRightAdv.getEncoder().getVelocity())) / 2.0;
        double averageDriveSpeedAdv = 0;
        RealisticMotor[] driveMotorsAdv = {driveLeft1Adv, driveLeft2Adv, driveRight1Adv, driveRight2Adv};
        for (RealisticMotor driveMotor : driveMotorsAdv) {
            averageDriveSpeedAdv += positive(driveMotor.getEncoder().getVelocity());
        }
        averageDriveSpeedAdv /= driveMotorsAdv.length;

        System.out.println("Average shooter speed (advanced): " + averageShooterSpeedAdv + " RPM");
        System.out.println("Average drive speed (advanced): " + averageDriveSpeedAdv + " RPM");
    }

    static SimpleMotor getMotorById(SimpleMotor[] motors, int id) {
        for (SimpleMotor motor : motors) {
            if (motor.getMotorId() == id) {
                return motor;
            }
        }
        return null;
    }

    static double positive(double value) {
        if (value < 0) {
            return -value;
        }
        return value;
    }

    static RealisticMotor getMotorById(RealisticMotor[] motors, int id) {
        for (RealisticMotor motor : motors) {
            if (motor.getDeviceId() == id) {
                return motor;
            }
        }
        return null;
    }
}
