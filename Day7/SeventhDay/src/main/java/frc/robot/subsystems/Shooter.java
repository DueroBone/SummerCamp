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
  LearnSparkMax shooterMotor = new LearnSparkMax(1, MotorType.kBrushed);
  public int theSpeedThatWeWantToBeAt = 0;
  public final int theSpeedThatWeWantToBeAtWhenWeAreShooting = 5000;
  public final int theSpeedThatWeWantToBeAtWhenWeAreNotShooting = 2500;

  /** Creates a new Shooter. */
  public Shooter() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(true);
    config.idleMode(IdleMode.kCoast);

    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (shooterMotor.getEncoder().getVelocity() < theSpeedThatWeWantToBeAt) {
      shooterMotor.set(1);
    } else {
      shooterMotor.set(0);
    }
  }
}
