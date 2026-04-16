package Day5.Solutions;

import lib.Simplified.Inputs.SimpleController;
import lib.Simplified.Motors.Motor;

public class LoopExample {
    public static void main(String[] args) {
        SimpleController controller = new SimpleController(0);
        Motor motor = new Motor(1);

        boolean motorOn = false;

        while (true) {
            if (controller.getAButton()) {
                motorOn = true;
            } else if (controller.getBButton()) {
                motorOn = false;
            }

            if (motorOn) {
                motor.set(1);
            } else {
                motor.set(0);
            }
        }
    }
}
