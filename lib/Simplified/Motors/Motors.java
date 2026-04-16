package lib.Simplified.Motors;

public class Motors {
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

    /** Same as getSimpleMotorData, but they have the same function names as real motors */
    public static RealisticMotor[] getRealisticMotorData() {
        RealisticMotor[] motors = new RealisticMotor[6];
        motors[0] = new RealisticMotor(11, -5205, 0, 12.2);
        motors[1] = new RealisticMotor(10, 5203, 30, 12);
        motors[2] = new RealisticMotor(1, 401, 40, 11.8);
        motors[3] = new RealisticMotor(2, 400, 40, 11.9);
        motors[4] = new RealisticMotor(3, -400, 40, 11.6);
        motors[5] = new RealisticMotor(4, -399, 40, 11.7);
        return motors;
    }
}
