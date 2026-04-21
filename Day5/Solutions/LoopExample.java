package Day5.Solutions;

import lib.Simplified.Wait;
import lib.Simplified.Inputs.SimpleController;
import lib.Simplified.Motors.Motor;

public class LoopExample {
    public static void main(String[] args) {
        SimpleController controller = new SimpleController(0);
        Motor shooter = new Motor(10);

        Motor leftDrive = new Motor(1);
        Motor rightDrive = new Motor(2);

        boolean shooterOn = false;

        while (true) {
            // Drive control
            leftDrive.set(controller.getLeftY());
            rightDrive.set(controller.getRightY());

            // Shooter control
            if (controller.getAButton()) {
                shooterOn = true;
            } else if (controller.getBButton()) {
                shooterOn = false;
            }

            if (shooterOn) {
                shooter.set(1);
            } else {
                shooter.set(0);
            }

            System.out.println("Shooter speed: " + shooter.getEncoder().getVelocity());
            Wait.waitSeconds(0.1);
        }
    }
}
