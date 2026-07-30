// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.BasicAutoCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private static final double SHOOTER_HALF_SPEED = 0.5;

  private final Shooter shooter = new Shooter();
  private final DriveTrain drivetrain = new DriveTrain();
  private final CommandXboxController driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  public Shooter getShooter() {
    return shooter;
  }

  public DriveTrain getDrivetrain() {
    return drivetrain;
  }

  private void configureBindings() {
    driverController.a().onTrue(new InstantCommand(shooter::setIdle));
    driverController.b().onTrue(new InstantCommand(shooter::setShooting));
    driverController.x().onTrue(new InstantCommand(() -> shooter.setPower(SHOOTER_HALF_SPEED)));
    driverController.y().onTrue(new InstantCommand(shooter::stop));
  }

  public Command getAutonomousCommand() {
    return new BasicAutoCommand(this);
  }
}
