// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BasicAutoCommand extends SequentialCommandGroup {
  /** Creates a new BasicAutoCommand. */
  public BasicAutoCommand(RobotContainer container) {
    addCommands(
        new SpeedUpCommand(container.shooter),
        new RunCommand(() -> container.drivetrain.tankDrive(0.5, 0.5),
            container.drivetrain)
            .withTimeout(2),
        new WaitCommand(2),
        new RunCommand(() -> container.drivetrain.tankDrive(-0.5, -0.5),
            container.drivetrain)
            .withTimeout(2));
  }
}
