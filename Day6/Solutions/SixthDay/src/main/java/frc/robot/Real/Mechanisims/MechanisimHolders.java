package frc.robot.Real.Mechanisims;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Real.Motors.SimulatedMotor;

public class MechanisimHolders {
    public static final ArrayList<SimulatedMechanisim> simulatedMechanisims = new ArrayList<>();
    public static final ArrayList<SimulatedMotor> simulatedMotors = new ArrayList<>();
    public static final InstantCommand generateMechanisimsCommand = new InstantCommand(() -> generateMechanisims());

    private static void generateMechanisims() {
        // if all motors in a mechanisim exist in list:
        // generate mechanisim
        // add mechanisim to list
        // add mechanisim to containing motors

        // order checks by most specific to least specific (ex: if 4 motors, check for 4
        // motor mechanisims first, then 3 motor mechanisims, etc.)

        // 1 2 3 4 Drivetrain
        // 1 2 Drivetrain

        // 10 11 Flywheel

        // 10 Flywheel

        // 20 21 Elevator
    }
}
