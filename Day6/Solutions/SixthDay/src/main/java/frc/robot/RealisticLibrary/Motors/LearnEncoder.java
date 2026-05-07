package frc.robot.RealisticLibrary.Motors;

public class LearnEncoder {
    private LearnSparkMax sparkMax;

    public LearnEncoder(LearnSparkMax sparkMax) {
        this.sparkMax = sparkMax;
    }

    public double getPosition() {
            return sparkMax.getPosition();
    }

    public double getVelocity() {
            return sparkMax.getVelocity();
    }
}
