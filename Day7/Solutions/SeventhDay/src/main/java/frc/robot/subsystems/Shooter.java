// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.LearnSparkMax;

public class Shooter extends SubsystemBase {
  // Shooter motor - private to prevent external access
  private final LearnSparkMax shooterMotor = new LearnSparkMax(10, MotorType.kBrushless);

  // Speed constants
  private static final int TARGET_SHOOTING_SPEED = 5000; // RPM
  private static final int TARGET_IDLE_SPEED = 2500; // RPM
  private static final double ACCELERATION_INCREMENT = 0.05;
  private static final double DECELERATION_INCREMENT = 0.02;
  private static final double MIN_SPEED_THRESHOLD = 0.9; // 90% of target
  private static final double MAX_SPEED_THRESHOLD = 1.1; // 110% of target
  private static final double MIN_POWER = 0.0;
  private static final double MAX_POWER = 1.0;

  // State management
  private ShooterState currentState = ShooterState.IDLE;
  private double motorPower = 0.0;

  // Shooter operating states
  private enum ShooterState {
    IDLE,
    SHOOTING,
    COAST
  }

  public Shooter() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(true);
    config.idleMode(IdleMode.kCoast);
    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  /**
   * Set the shooter to idle state
   */
  public void setIdle() {
    currentState = ShooterState.IDLE;
    motorPower = 0.0;
  }

  /**
   * Set the shooter to shooting state
   */
  public void setShooting() {
    currentState = ShooterState.SHOOTING;
  }

  /**
   * Stop the shooter with coast mode
   */
  public void stop() {
    currentState = ShooterState.COAST;
    motorPower = 0.0;
  }

  /**
   * Get the current shooter velocity in RPM
   */
  public double getVelocity() {
    return shooterMotor.getEncoder().getVelocity();
  }

  /**
   * Set motor power directly (0.0 to 1.0)
   */
  public void setPower(double power) {
    currentState = ShooterState.SHOOTING;
    motorPower = Math.max(MIN_POWER, Math.min(power, MAX_POWER));
  }

  @Override
  public void periodic() {
    double currentVelocity = getVelocity();
    SmartDashboard.putNumber("Shooter Velocity (RPM)", currentVelocity);

    switch (currentState) {
      case IDLE:
        motorPower = 0.0;
        break;
      case SHOOTING:
        // Ramp motor power based on velocity feedback
        if (currentVelocity < TARGET_SHOOTING_SPEED * MIN_SPEED_THRESHOLD) {
          motorPower += ACCELERATION_INCREMENT;
        } else if (currentVelocity > TARGET_SHOOTING_SPEED * MAX_SPEED_THRESHOLD) {
          motorPower -= DECELERATION_INCREMENT;
        }
        motorPower = Math.max(MIN_POWER, Math.min(motorPower, MAX_POWER));
        break;
      case COAST:
        motorPower = 0.0;
        break;
    }

    shooterMotor.set(motorPower);
  }
}
