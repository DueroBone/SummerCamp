package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RealisticLibrary.Motors.LearnSparkMax;

public class Drivetrain extends SubsystemBase {
  LearnSparkMax frontLeft = new LearnSparkMax(1, MotorType.kBrushless);
  LearnSparkMax frontRight = new LearnSparkMax(2, MotorType.kBrushless);
  LearnSparkMax backLeft = new LearnSparkMax(3, MotorType.kBrushless);
  LearnSparkMax backRight = new LearnSparkMax(4, MotorType.kBrushless);

  public Drivetrain() {
    SparkMaxConfig config = new SparkMaxConfig();
  }

  @Override
  public void periodic() {
  }
}
