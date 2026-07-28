package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  Shooter shooter = new Shooter();

  private final CommandXboxController driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    driverController.a().onTrue(new InstantCommand(() -> shooter.turnOn()));
    driverController.a().onFalse(new InstantCommand(() -> shooter.turnOff()));
  }

  public Command getAutonomousCommand() {
    return new InstantCommand();
  }
}
