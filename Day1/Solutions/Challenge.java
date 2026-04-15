package Day1.Solutions;

public class Challenge {
    /**
     * First: seperate into groups by what they're used for (ex: robot info, motor info, etc.)
     * Second: Find what types they should be (ex: String, int, double, boolean, etc.)
     * Third: Fix any typo errors (ex: missing semicolons, misspelled words, etc.)
     * Fourth: Print out all the variables
     * 
     * Beware, the red squiggles are not always correct!
     */
    public static void main(String[] args) {
        // Robot Info
        String robotName = "TribeTech";
        String division = "FRC Indiana Division";
        int timeLeft = 15;
        double batteryLevel = 11.8;

        // Drive Info
        double driveSpeed = 3.5;
        String driveWheelDiameter = "4 inches";
        int gearRatio = 12;
        int tirePressure = 0;

        // Motor Info
        double motorRpm = 150;
        double secondMotorRpm = 175.1;
        double targetRpm = 200;
        double motorVoltage = 12;
        boolean motorsReversed = false;
        String motorType = "CIM";

        // Autonomous/Status
        boolean isAutonomous = false;
        boolean isComputerConnected = true;
        int ballsCollected = 5;

        // Print out all the variables:
        System.out.println("Robot Name: " + robotName);
        System.out.println("Division: " + division);
        System.out.println("Time Left: " + timeLeft);
        System.out.println("Battery Level: " + batteryLevel);
        System.out.println("Drive Speed: " + driveSpeed);
        System.out.println("Drive Wheel Diameter: " + driveWheelDiameter);
        System.out.println("Gear Ratio: " + gearRatio);
        System.out.println("Tire Pressure: " + tirePressure);
        System.out.println("Motor RPM: " + motorRpm);
        System.out.println("Second Motor RPM: " + secondMotorRpm);
        System.out.println("Target RPM: " + targetRpm);
        System.out.println("Motor Voltage: " + motorVoltage);
        System.out.println("Motors Reversed: " + motorsReversed);
        System.out.println("Motor Type: " + motorType);
        System.out.println("Is Autonomous: " + isAutonomous);
        System.out.println("Is Computer Connected: " + isComputerConnected);
        System.out.println("Balls Collected: " + ballsCollected);
    }
}
