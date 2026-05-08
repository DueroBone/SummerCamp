// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.LearnSparkMax;

public class DriveTrain extends SubsystemBase {
  LearnSparkMax leftMotor = new LearnSparkMax(1, MotorType.kBrushless);
  LearnSparkMax rightMotor = new LearnSparkMax(2, MotorType.kBrushless);

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.smartCurrentLimit(10);
    leftMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
  }

  public void tankDrive(double leftPercent, double rightPercent) {
    leftMotor.set(process(leftPercent));
    rightMotor.set(process(rightPercent));
  }

  private double process(double input) {
    return process2(process3(input, 0.1), 3);
  }

  private double process2(double input, double x) {
    return Math.pow(input, x);
  }

  private double process3(double input, double x) {
    if (Math.abs(input) < x) {
      return 0;
    }
    return input;
  }
}
