package Day5;

import lib.Simplified.Wait;
import lib.Simplified.Inputs.ProximitySensor;
import lib.Simplified.Motors.Motor;

public class ShooterProject {
    public static void main(String[] args) {
        Motor shooter = new Motor(10);
        ProximitySensor sensor = new ProximitySensor(0);
        Motor drive = new Motor(11);

        int targetSpeed = 0;
        int timer = 0;

        int stage = 0;
        while (true) {
            if (stage == 0) {
                // speed up shooter
                if (shooter.getEncoder().getVelocity() < targetSpeed) {
                    shooter.set(1);
                } else {
                    // shooter is at target speed, move to next stage
                    stage = 1;
                }

            } else if (stage == 1) {
                // drive forward for one foot
                if (drive.getEncoder().getPosition() < 12) {
                    drive.set(1);
                } else {
                    // drive is at target position, move to next stage
                    stage = 2;
                }

            } else if (stage == 2) {
                // shooter goes backwards
                if (shooter.getEncoder().getVelocity() > -targetSpeed) {
                    shooter.set(-1);
                } else {
                    // shooter is backwards, move to next stage
                    stage = 3;
                }

            } else if (stage == 3) {
                // drive backwards for one foot
                if (drive.getEncoder().getPosition() > 0) {
                    drive.set(-1);
                } else {
                    // drive is at target position, move to next stage
                    stage = 4;
                }

            } else {
                // done, stop all motors
                shooter.set(0);
                drive.set(0);
            }

            // Add your code here
            if (shooter.getEncoder().getVelocity() == targetSpeed) {
                if (!sensor.isObjectDetected()) {
                    shooter.set(1);
                }
            } else if (shooter.getEncoder().getVelocity() > targetSpeed) {
                shooter.set(-1);
            } else {
                shooter.set(0);
            }

            System.out.println("Shooter speed: " + shooter.getEncoder().getVelocity());

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
