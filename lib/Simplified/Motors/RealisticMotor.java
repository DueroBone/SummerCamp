package lib.Simplified.Motors;

public class RealisticMotor {
    private int motorId;
    private SimpleEncoder encoder;
    private double current;
    private double voltage;
    private static final double MAX_RPM = 100;

    public RealisticMotor(int motorId, double rpm, double current, double voltage) {
        this.motorId = motorId;
        this.encoder = new SimpleEncoder(0, rpm);
        this.current = current;
        this.voltage = voltage;
    }

    public int getDeviceId() {
        return motorId;
    }

    public SimpleEncoder getEncoder() {
        return encoder;
    }

    public double getOutputCurrent() {
        return current;
    }

    /** Returns the input voltage, not the output! */
    public double getBusVoltage() {
        return voltage;
    }

    public void set(double percent) {
        // Calculate the new RPM
        double currentRpm = encoder.getVelocity();
        if (percent == 0) {
            currentRpm *= 0.8; // Simulate coasting
        } else {
            currentRpm += percent * 10;
        }

        if (currentRpm > MAX_RPM) {
            currentRpm = MAX_RPM;
            this.current = 10;
            // At max RPM, the motor is working hard but not drawing too much current
        } else if (currentRpm < -MAX_RPM) {
            currentRpm = -MAX_RPM;
            this.current = 10;
        }

        this.encoder.setVelocity(currentRpm);

        // Update position
        double currentPosition = encoder.getPosition();
        currentPosition += currentRpm / 60.0; // Convert RPM to RPS
        encoder.setPosition(currentPosition);

        // Simulate voltage drop under load
        double voltageDrop = Math.abs(percent) * 2;
        this.voltage = 12 - voltageDrop;

        // Simulate current draw based on percent output
        this.current = Math.abs(percent) * 40;
    }

    public void setVoltage(double voltage) {
        double percent = voltage / 12.0;
        set(percent);
    }
}
