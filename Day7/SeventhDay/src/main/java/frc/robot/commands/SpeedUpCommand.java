// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SpeedUpCommand extends Command {
  Shooter spinny;

  /** Creates a new SpeedUpCommand. */
  public SpeedUpCommand(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    this.spinny = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    spinny.globalShooterMode = 1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
     * No need to do anything, the Shooter subsystem's periodic
     * method will handle the logic of speeding up the shooter.
     */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return spinny.theSpeedThatWeWantToBeAt >= spinny.theSpeedThatWeWantToBeAtWhenWeAreShooting;
  }
}
