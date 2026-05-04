package frc.robot.RealisticLibrary.Motors;

public class LearnEncoder {
    private double position;
    private double velocity;

    public LearnEncoder() {
        this.position = 0;
        this.velocity = 0;
    }

    public double getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
