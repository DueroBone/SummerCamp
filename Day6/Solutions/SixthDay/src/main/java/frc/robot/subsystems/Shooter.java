package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  double targetRPM = 0;
  public Shooter() {}

  @Override
  public void periodic() {
  }

  public void setTargetRPM(double targetRPM) {
    this.targetRPM = targetRPM;
  }

  public double getCurrentRPM() {
    return 0;
  }
}
