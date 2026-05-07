package frc.robot.RealisticLibrary.Mechanisims;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public class Arm extends SimulatedMechanisim {
    SingleJointedArmSim armSim;
    MechanismLigament2d armDisplay;

    public Arm(double gearing, double momentOfInertia, double armLengthMeters, double minAngleRadians,
            double maxAngleRadians, double startingAngleRadians, SimulatedMotor... motors) {
        armSim = new SingleJointedArmSim(DCMotor.getNEO(motors.length), gearing, momentOfInertia,
                armLengthMeters, minAngleRadians, maxAngleRadians, true, startingAngleRadians);

        this.conversionFactor = gearing / (2 * Math.PI);
        this.motors = motors;

        for (SimulatedMotor _motor : motors) {
            _motor.setMechanisim(this);
        }

        Mechanism2d mech2d = new Mechanism2d(armLengthMeters * 2, armLengthMeters * 2);
        armDisplay = new MechanismLigament2d("Arm", armLengthMeters,
                Units.radiansToDegrees(startingAngleRadians));
        mech2d.getRoot("arm", armLengthMeters, armLengthMeters) // center of screen
                .append(armDisplay);
        SmartDashboard.putData("Arm Mechanism " + getMotorPorts(), mech2d);
    }

    @Override
    public void simulationPeriodic() {
        if (armSim == null) {
            return;
        }

        armSim.update(0.02);

        double volt = performCurrentLimiting(targetVoltage, motors[0]);
        volt = DriverStation.isEnabled() ? volt : 0;
        armSim.setInputVoltage(volt);

        for (SimulatedMotor motor : motors) {
            double amps = getCurrent(motor);
            if (amps >= 80 && amps < motor.getCurrentLimit()) {
                System.out.println("A motor is now on fire! " + motor +
                        " is drawing " + amps + " amps");
            }
        }

        armDisplay.setAngle(armSim.getAngleRads() * 180 / Math.PI);

        if (armSim.hasHitUpperLimit()) {
            System.out.println("Arm has crashed into the top!");
        } else if (armSim.hasHitLowerLimit()) {
            System.out.println("Arm has crashed into the bottom!");
        }
    }

    @Override
    public double getRpm(SimulatedMotor motor) {
        return armSim.getVelocityRadPerSec() * 60 * conversionFactor;
    }

    @Override
    public double getPosition(SimulatedMotor motor) {
        return armSim.getAngleRads() * conversionFactor;
    }

    @Override
    public double getCurrent(SimulatedMotor motor) {
        return armSim.getCurrentDrawAmps() / motors.length;
    }

    @Override
    public double getTargetVoltage(SimulatedMotor motor) {
        return armSim.getInput(0);
    }

}
