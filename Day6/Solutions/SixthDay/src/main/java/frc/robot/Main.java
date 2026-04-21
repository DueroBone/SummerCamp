// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Do NOT add any static variables to this class, or any initialization at all.
 * Unless you know what
 * you are doing, do not modify this file except to change the parameter class
 * to the startRobot
 * call.
 */
public final class Main {
  private Main() {
  }

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>
   * If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
    if (false) {
      System.out.println("Testing idle mode:");
      SparkMaxConfig config = new SparkMaxConfig();
      System.out.println(config.flatten());
      config.idleMode(IdleMode.kBrake);
      System.out.println(config.flatten());
      config.idleMode(IdleMode.kCoast);
      System.out.println(config.flatten());

      System.out.println("Testing current limit:");
      config = new SparkMaxConfig(); // reset config
      config.smartCurrentLimit(50);
      System.out.println(config.flatten());
      config.smartCurrentLimit(100);
      System.out.println(config.flatten());

      System.out.println("Testing conversion factors:");
      config = new SparkMaxConfig(); // reset config
      config.encoder.positionConversionFactor(2.5);
      System.out.println(config.flatten());
      config.encoder.positionConversionFactor(1.0);
      System.out.println(config.flatten());
      config.encoder.velocityConversionFactor(0.5);
      System.out.println(config.flatten());

      System.out.println("Testing inversion:");
      config = new SparkMaxConfig(); // reset config
      config.inverted(true);
      System.out.println(config.flatten());
      config.inverted(false);
      System.out.println(config.flatten());

      System.out.println("Testing full config:");
      config = new SparkMaxConfig(); // reset config
      config.idleMode(IdleMode.kBrake);
      config.smartCurrentLimit(50);
      config.encoder.positionConversionFactor(2.5);
      config.encoder.velocityConversionFactor(0.5);
      config.inverted(true);
      System.out.println(config.flatten());
    }

    RobotBase.startRobot(Robot::new);
  }
}
