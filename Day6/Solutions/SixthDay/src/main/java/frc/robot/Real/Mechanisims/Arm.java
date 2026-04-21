package frc.robot.Real.Mechanisims;

import java.util.Arrays;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.Real.Motors.SimulatedMotor;

public class Arm extends SimulatedMechanisim {
    SingleJointedArmSim armSim;

    public Arm(double gearing, double momentOfInertia, double armLengthMeters, double minAngleRadians,
            double maxAngleRadians, double startingAngleRadians, SimulatedMotor... motors) {
        armSim = new SingleJointedArmSim(DCMotor.getNEO(motors.length), gearing, momentOfInertia,
                armLengthMeters, minAngleRadians, maxAngleRadians, true, startingAngleRadians);
        this.conversionFactor = gearing / (2 * Math.PI);

        for (SimulatedMotor _motor : motors) {
            _motor.setMechanisim(this);
        }
    }

    @Override
    public void simulationPeriodic() {
        armSim.update(0.02);

        armSim.setInputVoltage(performCurrentLimiting(targetVoltage));
        if (getCurrent() >= 80) {
            System.out.println("A motor is now on fire! " + Arrays.toString(motors));
        }
    }

    @Override
    public double getRpm() {
        return armSim.getVelocityRadPerSec() * 60 * conversionFactor;
    }

    @Override
    public double getPosition() {
        return armSim.getAngleRads() * conversionFactor;
    }

    @Override
    public double getCurrent() {
        return armSim.getCurrentDrawAmps() / motors.length;
    }

    @Override
    public double getTargetVoltage() {
        return armSim.getInput(0);
    }

}
