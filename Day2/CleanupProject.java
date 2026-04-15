package Day2;

public class CleanupProject {

    // Ignore the "static" keyword for now.
    // You'll learn why it's there at the end of today ;)
    static boolean isShooterEnabled = false;
    static double driveSpeed = 3.5; // m/s
    static int ballsCollected = 5;
    static double shooterRPM = 0.0;

    /**
     * Ignore the throws InterruptedException, it's just so the Thread.sleep()
     * method doesn't cause an error. (More advanced than what you'll learn from me)
     */
    public static void main(String[] args) throws InterruptedException {
        // Print initial stats
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250); // Wait 0.25 seconds, makes it easier to read the output

        // Turn on shooter
        isShooterEnabled = true;
        shooterRPM = 1500.0;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);

        // Drive faster
        driveSpeed += 5;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);

        // Turn off shooter
        isShooterEnabled = false;
        shooterRPM = 0.0;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);

        driveSpeed += 0.5;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);
        isShooterEnabled = true;
        shooterRPM = 1500.0;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);
        driveSpeed -= 1;
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);
    }
}
