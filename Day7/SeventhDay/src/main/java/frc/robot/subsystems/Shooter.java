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
  public LearnSparkMax shooterMotor = new LearnSparkMax(10, MotorType.kBrushless);
  public int theSpeedThatWeWantToBeAt = 0;
  public final int theSpeedThatWeWantToBeAtWhenWeAreShooting = 5000;
  public final int theSpeedThatWeWantToBeAtWhenWeAreNotShooting = 2500;

  // BAD PRACTICE: Inconsistent state management
  public int globalShooterMode = 1; // 0=idle, 1=shooting, 2=coast
  public double lastVelocity = 0;
  public double velocityChangeThreshold = 1000; // Magic number

  // BAD PRACTICE: No consistent configuration
  public double motorPercent = 10;
  public int updateCounter = 0;

  public Shooter() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(true);
    config.idleMode(IdleMode.kCoast);
    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    updateCounter++;

    double currentVelocity = shooterMotor.getEncoder().getVelocity();
    double targetSpeed = theSpeedThatWeWantToBeAt;
    double newMotorPercent = motorPercent;

    if (globalShooterMode == 0) {
      targetSpeed = theSpeedThatWeWantToBeAtWhenWeAreNotShooting;
      newMotorPercent = 0;
    }

    if (globalShooterMode == 1) {
      targetSpeed = theSpeedThatWeWantToBeAtWhenWeAreShooting;

      if (currentVelocity < targetSpeed) {
        newMotorPercent = newMotorPercent + 0.03;
      }

      if (currentVelocity < targetSpeed * 0.9) {
        newMotorPercent = newMotorPercent + 0.02;
      }

      if (currentVelocity > targetSpeed) {
        newMotorPercent = newMotorPercent - 0.01;
      }

      if (currentVelocity > targetSpeed * 1.1) {
        newMotorPercent = newMotorPercent - 0.02;
      }
    }

    if (globalShooterMode == 2) {
      targetSpeed = 0;
      newMotorPercent = 0;
    }

    if (globalShooterMode != 0) {
      if (globalShooterMode != 2) {
        if (newMotorPercent > 1.0) {
          newMotorPercent = 1.0;
        }
      }
    }

    if (newMotorPercent < 0.0) {
      newMotorPercent = 0.0;
    }

    if (newMotorPercent > 1.0) {
      newMotorPercent = 1.0;
    }

    if (shooterMotor != null) {
      if (newMotorPercent >= 0) {
        shooterMotor.set(newMotorPercent);
      } else {
        shooterMotor.set(0);
      }
    }

    motorPercent = newMotorPercent;

    if (updateCounter % 50 <= 5) {
      if (targetSpeed == 0) {
        System.out.println("Shooter velocity: " + currentVelocity + " target: " + targetSpeed +
            " power: " + motorPercent);
      } else {
        System.out.println("Shooter velocity: " + currentVelocity + " target: " + targetSpeed +
            " power: " + motorPercent);
      }
    }

    lastVelocity = currentVelocity;
  }
}
