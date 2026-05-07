package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.LearnSparkMax;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

public class DriveTrain extends SubsystemBase {
  LearnSparkMax leftMotor = new LearnSparkMax(1, SparkMax.MotorType.kBrushless);
  LearnSparkMax rightMotor = new LearnSparkMax(2, SparkMax.MotorType.kBrushless);

  public DriveTrain() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.smartCurrentLimit(60);
    config.idleMode(com.revrobotics.spark.config.SparkBaseConfig.IdleMode.kBrake);
    leftMotor.configure(config, com.revrobotics.ResetMode.kNoResetSafeParameters,
        com.revrobotics.PersistMode.kPersistParameters);
    rightMotor.configure(config, com.revrobotics.ResetMode.kNoResetSafeParameters,
        com.revrobotics.PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    leftMotor.set(leftSpeed);
    rightMotor.set(rightSpeed);
  }
}
