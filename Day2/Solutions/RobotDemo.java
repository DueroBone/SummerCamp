package Day2.Solutions;

public class RobotDemo {
    static class Robot {
        String teamName;
        double weight;
        int currentSpeed;
        int targetSpeed;

        public Robot(String teamName, double weight,
                int currentSpeed, int targetSpeed) {
            this.teamName = teamName;
            this.weight = weight;
            this.currentSpeed = currentSpeed;
            this.targetSpeed = targetSpeed;
        }

        public String getTeamName() {
            return teamName;
        }

        public boolean isWeightLegal() {
            return weight <= 125;
        }

        public void changeWeight(double newWeight) {
            weight = newWeight;
        }

        public void speedUp() {
            currentSpeed += 5;
        }

        public void slowDown() {
            currentSpeed -= 5;
        }

        public boolean isAtTargetSpeed() {
            return currentSpeed == targetSpeed;
        }
    }

    public static void main(String[] args) {
        Robot robot = new Robot("Tribe tech", 100.0,
                0, 10);
    }
}