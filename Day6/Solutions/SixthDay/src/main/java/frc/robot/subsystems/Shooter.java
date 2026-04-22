package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Real.Motors.LearnSparkMax;
import java.util.ArrayList;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Shooter extends SubsystemBase {
  double targetRPM = 0;
  LearnSparkMax motor = new LearnSparkMax(10, SparkMax.MotorType.kBrushless);
  ArrayList<Double> rpmHistory = new ArrayList<>();
  ArrayList<Double> targetRpmHistory = new ArrayList<>();

  public Shooter() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.smartCurrentLimit(40);
    config.idleMode(IdleMode.kCoast);
    motor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    if (targetRPM == 0) {
      motor.set(0);
    } else if (targetRPM > 0) { // going forward
      if (getCurrentRPM() < targetRPM) {
        motor.set(1);
      } else {
        motor.set(0);
      }
    } else if (targetRPM < 0) { // going backward
      if (getCurrentRPM() > targetRPM) {
        motor.set(-1);
      } else {
        motor.set(0);
      }
    }

    SmartDashboard.putNumber("Shooter Target RPM", targetRPM);
    SmartDashboard.putNumber("Shooter Current RPM", getCurrentRPM());

    // For graphing purposes
    rpmHistory.add(getCurrentRPM());
    targetRpmHistory.add(targetRPM);

    if (rpmHistory.size() > 500) {
      // Write to file
      StringBuilder sb = new StringBuilder();
      sb.append("current_rpm,target_rpm\n");
      for (int i = 0; i < rpmHistory.size(); i++) {
        sb.append(rpmHistory.get(i)).append(",").append(targetRpmHistory.get(i)).append("\n");
      }
      java.nio.file.Path path = java.nio.file.Paths.get("shooter_data.csv");
      // try {
      // java.nio.file.Files.write(path, sb.toString().getBytes());
      // System.out.println("Wrote shooter data to " + path.toAbsolutePath());
      // } catch (java.io.IOException e) {
      // e.printStackTrace();
      // }
      rpmHistory.clear();
      targetRpmHistory.clear();
    }
  }

  public void setTargetRPM(double targetRPM) {
    this.targetRPM = targetRPM;
  }

  public double getCurrentRPM() {
    return motor.getEncoder().getVelocity();
  }
}
