package Day2.Solutions;

import lib.Simplified.Wait;

public class CleanupProject {

    // Ignore the "static" keyword for now.
    // You'll learn why it's there at the end of today ;)
    static boolean isShooterEnabled = false;
    static double driveSpeed = 3.5; // m/s
    static int ballsCollected = 5;
    static double shooterRPM = 0.0;

    public static void main(String[] args) {
        // Print initial stats
        printRobotStats();

        // Turn on shooter
        shooterOn();
        printRobotStats();

        // Drive faster
        changeDriveSpeedBy(5);
        printRobotStats();

        // Turn off shooter
        shooterOff();
        printRobotStats();

        changeDriveSpeedBy(0.5);
        printRobotStats();

        shooterOn();
        printRobotStats();

        changeDriveSpeedBy(-1);
        printRobotStats();
    }

    static void printRobotStats() {
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25); // Wait 0.25 seconds, makes it easier to read the output
    }

    static void shooterOn() {
        isShooterEnabled = true;
        shooterRPM  = 1500.0;
    }

    static void shooterOff() {
        isShooterEnabled = false;
        shooterRPM = 0.0;
    }

    static void changeDriveSpeedBy(double amount) {
        driveSpeed += amount;
    }
}
