// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Shooter;
import frc.robot.commands.BasicAutoCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  public final Shooter shooter = new Shooter();
  public final DriveTrain drivetrain = new DriveTrain();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandXboxController m_driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // BAD PRACTICE: Button bindings with magic numbers and unclear logic
    // Shoot
    m_driverController.a().onTrue(new InstantCommand(() -> {
      shooter.motorPercent = 1;
    }));

    // Stop
    m_driverController.b().onTrue(new InstantCommand(() -> {
      shooter.shooterMotor.set(0);
    }));

    m_driverController.x().onTrue(new InstantCommand(() -> {
      // BAD PRACTICE: Duplicate and inconsistent motor control
      double speed = shooter.shooterMotor.getEncoder().getVelocity();
      if (speed < 3000) {
        shooter.shooterMotor.set(0.8);
      }
    }));

    m_driverController.y().onTrue(new InstantCommand(() -> {
      shooter.shooterMotor.set(0);
    }));
  }

  public Command getAutonomousCommand() {
    // BAD PRACTICE: Hardcoded autonomous behavior
    return new BasicAutoCommand(this);
  }
}
