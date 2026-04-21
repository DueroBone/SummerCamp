package Day5.Solutions;

import lib.Simplified.Wait;
import lib.Simplified.Motors.Motor;
import java.nio.file.Files; // Not for students
import java.nio.file.Paths;
import java.io.IOException;

public class ShooterProject {
    public static void main(String[] args) {
        Motor shooter = new Motor(10);

        int targetSpeed = 0;
        int timer = 0;
        double[][] speedHistory = new double[110][2]; // current, target | Not for students

        while (true) {
            double currentSpeed = shooter.getEncoder().getVelocity();
            if (targetSpeed == 0) {
                shooter.set(0);
            } else if (targetSpeed > 0) {
                if (currentSpeed < targetSpeed) {
                    shooter.set(1);
                } else {
                    shooter.set(0);
                }
            } else if (targetSpeed < 0) {
                if (currentSpeed > targetSpeed) {
                    shooter.set(-1);
                } else {
                    shooter.set(0);
                }
            }

            // Do not edit below this line
            System.out.println("Shooter speed: " + shooter.getEncoder().getVelocity());
            Wait.waitSeconds(0.1);
            speedHistory[timer][0] = shooter.getEncoder().getVelocity();
            speedHistory[timer][1] = targetSpeed;
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

        // Save to CSV | Not for students
        StringBuilder csvString = new StringBuilder();
        csvString.append("current_speed,target_speed\n");
        for (int i = 0; i < timer; i++) {
            csvString.append(speedHistory[i][0]).append(",")
                    .append(speedHistory[i][1]).append("\n");
        }
        try {
            Files.write(Paths.get("shooter_speed_history.csv"), csvString.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
