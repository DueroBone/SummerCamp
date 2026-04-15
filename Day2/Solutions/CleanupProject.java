package Day2.Solutions;

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

    static void printRobotStats() throws InterruptedException {
        System.out.println("Robot Stats:");
        System.out.println("Shooter Enabled: " + isShooterEnabled);
        System.out.println("Drive Speed: " + driveSpeed + " m/s");
        System.out.println("Balls Collected: " + ballsCollected);
        System.out.println("Shooter RPM: " + shooterRPM);
        System.out.println();
        Thread.sleep(250);
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
