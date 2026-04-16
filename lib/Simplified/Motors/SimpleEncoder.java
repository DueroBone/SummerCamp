package lib.Simplified.Motors;

public class SimpleEncoder {
    private double position;
    private double velocity;

    public SimpleEncoder(double position, double velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public double getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    protected void setPosition(double position) {
        this.position = position;
    }

    protected void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
