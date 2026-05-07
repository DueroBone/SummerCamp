// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.LearnSparkMax;

public class Shooter extends SubsystemBase {
  public static LearnSparkMax shooterMotor = new LearnSparkMax(10, MotorType.kBrushless); // BAD PRACTICE: Public field
                                                                                          // + STATIC = BUG
  public int theSpeedThatWeWantToBeAt = 0;
  public final int theSpeedThatWeWantToBeAtWhenWeAreShooting = 5000;
  public final int theSpeedThatWeWantToBeAtWhenWeAreNotShooting = 2500;

  // BAD PRACTICE: Inconsistent state management
  public int globalShooterMode = 0; // 0=idle, 1=shooting, 2=coast
  public double lastVelocity = 0;
  public double velocityChangeThreshold = 1000; // Magic number

  // BAD PRACTICE: No consistent configuration
  private double motorPower = 0.5;
  private int updateCounter = 0;

  public Shooter() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(true);
    config.idleMode(IdleMode.kCoast);
    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    updateCounter++;

    // BAD PRACTICE: Complex spaghetti logic with poor state management
    double currentVelocity = shooterMotor.getEncoder().getVelocity();

    // BAD PRACTICE: Bizarre control logic
    if (globalShooterMode == 0) {
      motorPower = 0;
    } else if (globalShooterMode == 1) {
      // BUG: Logic bug - both conditions can be true, should use "else if"
      if (currentVelocity < theSpeedThatWeWantToBeAtWhenWeAreShooting * 0.9) {
        motorPower += 0.05;
      }
      if (currentVelocity > theSpeedThatWeWantToBeAtWhenWeAreShooting * 1.1) {
        motorPower -= 0.02;
      }
      if (motorPower > 1.0)
        motorPower = 1.0;
      if (motorPower < 0.0)
        motorPower = 0.0;
    } else if (globalShooterMode == 2) {
      motorPower = 0;
    }

    // BAD PRACTICE: Redundant condition checking
    if (shooterMotor != null) {
      if (motorPower >= 0) {
        shooterMotor.set(motorPower);
      } else {
        shooterMotor.set(0);
      }
    }

    // BAD PRACTICE: Unnecessary logging in periodic
    if (updateCounter % 50 == 0) {
      System.out.println("Shooter velocity: " + currentVelocity + " target: " +
          theSpeedThatWeWantToBeAt + " power: " + motorPower);
    }

    lastVelocity = currentVelocity;
  }
}
