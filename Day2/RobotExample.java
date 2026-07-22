package Day2;

public class RobotExample {
    static class Robot {
        double shooterSpeed;
        double wheelSpeed;
        String robotName;
        boolean shooterRunning;

        Robot(String name) {
            robotName = name;
            shooterSpeed = 0;
            wheelSpeed = 0;
            shooterRunning = false;
        }

        void speedUp() {
            wheelSpeed += 5;
        }

        void slowDown() {
            wheelSpeed -= 5;
        }

        void stop() {
            wheelSpeed = 0;
        }

        double getSpeed() {
            return wheelSpeed;
        }
    }

    public static void main(String[] args) {
        Robot myRobot = new Robot("RoboCop");

        myRobot.speedUp();
        myRobot.speedUp();
        myRobot.speedUp();
        System.out.println(myRobot.getSpeed());

        Robot yourRobot = new Robot("Terminator");
        yourRobot.speedUp();
        System.out.println(myRobot.getSpeed());
    }
}
