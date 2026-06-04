package Day2;

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
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25); // Wait 0.25 seconds, makes it easier to read the output

        // Turn on shooter
        isShooterEnabled = true;
        shooterRPM = 1500.0;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25);

        // Drive faster
        driveSpeed += 5;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25);

        // Turn off shooter
        isShooterEnabled = false;
        shooterRPM = 0.0;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25);

        driveSpeed += 0.5;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25);
        isShooterEnabled = true;
        shooterRPM = 1500.0;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25);
        driveSpeed -= 1;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Wait.waitSeconds(0.25);
    }
}
