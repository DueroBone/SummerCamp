// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

public class BasicAutoCommand extends SequentialCommandGroup {
  public BasicAutoCommand(RobotContainer container) {
    addCommands(
        new SpeedUpCommand(container.getShooter()),
        new RunCommand(() -> container.getDrivetrain().tankDrive(0.5, 0.5), container.getDrivetrain())
            .withTimeout(2.0),
        new InstantCommand(() -> container.getDrivetrain().tankDrive(0.0, 0.0)),
        new InstantCommand(container.getShooter()::stop));
  }
}