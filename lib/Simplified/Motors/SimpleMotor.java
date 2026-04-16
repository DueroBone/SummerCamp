package lib.Simplified.Motors;

public class SimpleMotor {
        private int motorId;
        private double rpm;
        private double current;
        private double voltage;

        public SimpleMotor(int motorId, double rpm, double current, double voltage) {
            this.motorId = motorId;
            this.rpm = rpm;
            this.current = current;
            this.voltage = voltage;
        }

        public int getMotorId() {
            return motorId;
        }

        public double getRpm() {
            return rpm;
        }

        public double getCurrent() {
            return current;
        }

        public double getVoltage() {
            return voltage;
        }
}
