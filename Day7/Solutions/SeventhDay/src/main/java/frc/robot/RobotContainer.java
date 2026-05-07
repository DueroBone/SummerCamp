// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  Shooter shooter = new Shooter();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandXboxController m_driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // BAD PRACTICE: Button bindings with magic numbers and unclear logic
    m_driverController.a().onTrue(new InstantCommand(() -> {
      shooter.shooterMotor.set(0.75);
    }));

    m_driverController.b().onTrue(new InstantCommand(() -> {
      shooter.shooterMotor.set(1.0);
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
    return new InstantCommand(() -> {
      shooter.shooterMotor.set(0.9);
      try {
        Thread.sleep(2000); // BAD PRACTICE: Blocking in autonomous
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      shooter.shooterMotor.set(0);
    });
  }
}
