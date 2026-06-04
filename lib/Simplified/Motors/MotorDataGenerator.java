package lib.Simplified.Motors;

public class MotorDataGenerator {
    public static SimpleMotor[] getSimpleMotorData() {
        SimpleMotor[] motors = new SimpleMotor[6];
        motors[0] = new SimpleMotor(11, -5205, 0, 12.2);
        motors[1] = new SimpleMotor(10, 5203, 30, 12);
        motors[2] = new SimpleMotor(1, 401, 40, 11.8);
        motors[3] = new SimpleMotor(2, 400, 40, 11.9);
        motors[4] = new SimpleMotor(3, -400, 40, 11.6);
        motors[5] = new SimpleMotor(4, -399, 40, 11.7);
        return motors;
    }

    /**
     * Same as getSimpleMotorData, but they have the same function names as real
     * motors
     */
    public static Motor[] getRealisticMotorData() {
        Motor[] motors = new Motor[6];
        motors[0] = new Motor(11, -5205, 0, 12.2);
        motors[1] = new Motor(10, 5203, 30, 12);
        motors[2] = new Motor(1, 401, 40, 11.8);
        motors[3] = new Motor(2, 400, 40, 11.9);
        motors[4] = new Motor(3, -400, 40, 11.6);
        motors[5] = new Motor(4, -399, 40, 11.7);
        return motors;
    }
}
