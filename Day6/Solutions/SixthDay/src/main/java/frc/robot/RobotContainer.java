package frc.robot;

import frc.robot.Real.Mechanisims.MechanisimHolders;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  Shooter shooter = new Shooter();
  DriveTrain driveTrain = new DriveTrain();

  private final CommandXboxController m_driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
    MechanisimHolders.generateMechanisims();
  }

  private void configureBindings() {
    m_driverController.b().onTrue(new InstantCommand(() -> shooter.setTargetRPM(0)));

    m_driverController.a().onTrue(new InstantCommand(() -> shooter.setTargetRPM(3000)));
    m_driverController.x().onTrue(new InstantCommand(() -> shooter.setTargetRPM(-3000)));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
