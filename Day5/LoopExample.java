package Day5;

import lib.Simplified.Wait;
import lib.Simplified.Inputs.SimpleController;
import lib.Simplified.Motors.Motor;

public class LoopExample {
    public static void main(String[] args) {
        SimpleController controller = new SimpleController(0);
        Motor shooter1 = new Motor(0);

        while (true) {
            if (shooter1.getEncoder().getVelocity() < 100) {
                shooter1.set(1);
            } else {
                shooter1.set(0);
            }

            Wait.waitSeconds(0.1);
            System.out.println(
                    "Position: " + shooter1.getEncoder().getPosition() +
                            " Speed: " + shooter1.getEncoder().getVelocity() +
                            " Power requested: " + shooter1.getOutputCurrent());
        }
    }
}
