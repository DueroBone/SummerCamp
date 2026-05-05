package frc.robot.RealisticLibrary.Mechanisims;

import java.util.Arrays;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public class Arm extends SimulatedMechanisim {
    SingleJointedArmSim armSim;

    public Arm(double gearing, double momentOfInertia, double armLengthMeters, double minAngleRadians,
            double maxAngleRadians, double startingAngleRadians, SimulatedMotor... motors) {
        armSim = new SingleJointedArmSim(DCMotor.getNEO(motors.length), gearing, momentOfInertia,
                armLengthMeters, minAngleRadians, maxAngleRadians, true, startingAngleRadians);

        this.conversionFactor = gearing / (2 * Math.PI);
        this.motors = motors;

        for (SimulatedMotor _motor : motors) {
            _motor.setMechanisim(this);
        }
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
        if (getCurrent(motors[0]) >= 80 && getCurrent(motors[0]) < motors[0].getCurrentLimit()) {
            System.out.println("A motor is now on fire! " + Arrays.toString(motors) +
                    ", drawing " + getCurrent(motors[0]) + " amps");
        }

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
