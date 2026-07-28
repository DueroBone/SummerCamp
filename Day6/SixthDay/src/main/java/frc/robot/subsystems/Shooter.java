// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.LearnSparkMax;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  LearnSparkMax billyBob = new LearnSparkMax(10, MotorType.kBrushless);

  public Shooter() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.smartCurrentLimit(40);
    billyBob.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shooter RPM", billyBob.getEncoder().getVelocity());
  }

  public void turnOn() {
    billyBob.setVoltage(12);
  }

  public void turnOff() {
    billyBob.setVoltage(0);
  }
}
