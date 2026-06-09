package Day3;

public class SensorFeedback {
    public static void main(String[] args) {
        Robot robot = new Robot();
        // Simulate the robot adjusting its shooter speed and firing
        // Write your code here:

    }

    // Do not modify anything below!

    /**
     * This class simulates a robot with a shooter mechanism.
     * You will have to take care to that the shooter is at
     * the right speed to hit the target when firing.
     */
    static class Robot {
        private int shooterRPM;
        public final double distancePerRPM = 0.25;

        public Robot() {
            shooterRPM = 0;
        }

        public void speedUpShooter() {
            // Random from 1-5 so you can't predict it
            shooterRPM += (Math.random() * 4) + 1;
        }

        public void slowDownShooter() {
            // Random from 1-5
            shooterRPM -= (Math.random() * 4) + 1;
        }

        public int getShooterRPM() {
            return shooterRPM;
        }

        public double getDistanceToTarget() {
            return 5;
        }

        public void fire() {
            // Yes, I made this math more difficult so that you
            // have to figure it out on your own ;)
            int x = shooterRPM;
            double y = distancePerRPM;
            int a = 0xA01F + x * 0 + x;
            int b = (int) (getDistanceToTarget() / y + 40991);
            if (a - b <= 1 && a - b >= -1) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }
        }
    }
}