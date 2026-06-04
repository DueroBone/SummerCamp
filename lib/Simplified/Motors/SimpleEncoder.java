package lib.Simplified.Motors;

public class SimpleEncoder {
    private double position;
    private double velocity;
    private double conversionFactor = 1.0;

    public SimpleEncoder(double position, double velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public double getPosition() {
        return position * conversionFactor;
    }

    public double getVelocity() {
        return velocity * conversionFactor;
    }

    public void setConversionFactor(double factor) {
        this.conversionFactor = factor;
    }

    protected void setPosition(double position) {
        this.position = position / conversionFactor;
    }

    protected void setVelocity(double velocity) {
        this.velocity = velocity / conversionFactor;
    }
}
