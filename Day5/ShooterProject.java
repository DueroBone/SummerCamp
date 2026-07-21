package Day5;

import lib.Simplified.Wait;
import lib.Simplified.Motors.Motor;

public class ShooterProject {
    public static void main(String[] args) {
        Motor shooter = new Motor(10);

        int targetSpeed = 0;
        int timer = 0;

        while (true) {
            // Add your code here

            // Do not edit below this line
            Wait.waitSeconds(0.1);
            timer += 1;
            if (timer == 10) {
                // After 1 seconds, speed up the shooter
                targetSpeed = 75;
                System.out.println("========= Target speed: " + targetSpeed + " =========");
            } else if (timer == 40) {
                // Then, after 3 seconds, reverse
                targetSpeed = -50;
                System.out.println("========= Target speed: " + targetSpeed + " =========");
            } else if (timer == 70) {
                // After 3 seconds, stop the shooter
                targetSpeed = 0;
                System.out.println("========= Target speed: " + targetSpeed + " =========");
            } else if (timer >= 110) {
                // After 4 seconds, end the program
                break;
            }
        }
    }
}
