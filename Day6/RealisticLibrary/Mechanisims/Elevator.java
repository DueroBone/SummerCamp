package frc.robot.RealisticLibrary.Mechanisims;

import java.util.Arrays;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import frc.robot.RealisticLibrary.Motors.SimulatedMotor;

public class Elevator extends SimulatedMechanisim {
    ElevatorSim elevatorSim;

    public Elevator(double gearing, double carriageMassKg, double drumRadiusMeters,
            double minHeightMeters, double maxHeightMeters, double startingHeightMeters, SimulatedMotor... motors) {
        elevatorSim = new ElevatorSim(DCMotor.getNEO(motors.length), gearing, carriageMassKg, drumRadiusMeters,
                minHeightMeters, maxHeightMeters, true, startingHeightMeters);

        // Convert linear drum travel in meters to motor rotations.
        this.conversionFactor = gearing / (2 * Math.PI * drumRadiusMeters);
        this.motors = motors;

        for (SimulatedMotor _motor : motors) {
            _motor.setMechanisim(this);
        }
    }

    @Override
    public void simulationPeriodic() {
        if (elevatorSim == null) {
            return;
        }

        elevatorSim.update(0.02);

        double volt = performCurrentLimiting(targetVoltage);
        volt = DriverStation.isEnabled() ? volt : 0;
        elevatorSim.setInputVoltage(volt);
        if (getCurrent() >= 80 && getCurrent() < getCurrentLimit()) {
            System.out.println("A motor is now on fire! " + Arrays.toString(motors) +
                    ", drawing " + getCurrent() + " amps");
        }
        if (elevatorSim.hasHitUpperLimit()) {
            System.out.println("Elevator has crashed into the top!");
        } else if (elevatorSim.hasHitLowerLimit()) {
            System.out.println("Elevator has crashed into the bottom!");
        }
    }

    @Override
    public double getRpm() {
        return elevatorSim.getVelocityMetersPerSecond() * 60 * conversionFactor;
    }

    @Override
    public double getPosition() {
        return elevatorSim.getPositionMeters() * conversionFactor;
    }

    @Override
    public double getCurrent() {
        return elevatorSim.getCurrentDrawAmps() / motors.length;
    }

    @Override
    public double getTargetVoltage() {
        return elevatorSim.getInput(0);
    }
}
