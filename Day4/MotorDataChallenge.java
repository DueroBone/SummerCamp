package Day4;

// Tells java where to find the Motors and SimpleMotor classes
import lib.Simplified.Motors.Motors;
import lib.Simplified.Motors.SimpleMotor;


public class MotorDataChallenge {
    /*
     * Id 1: Left drive 1
     * Id 2: Left drive 2
     * Id 3: Right drive 1
     * Id 4: Right drive 2
     * 
     * Id 10: Shooter left
     * Id 11: Shooter right
     * 
     * Find average shooter speed, and average drive speed.
     * Find if there's any weird things going on with the motors.
     */
    public static void main(String[] args) {
        SimpleMotor[] motors = Motors.getSimpleMotorData();
    }
}
