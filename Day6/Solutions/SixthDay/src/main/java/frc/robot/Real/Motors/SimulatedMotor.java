package frc.robot.Real.Motors;

import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Real.Mechanisims.MechanisimHolders;
import frc.robot.Real.Mechanisims.SimulatedMechanisim;
import frc.robot.Real.Motors.SparkConfigParser.ParsedSparkConfig;

public class SimulatedMotor {
    private int port;
    private SimulatedMechanisim mechanisim;
    private double currentLimit = 100;
    // TODO: handle properties below
    private double posConversionFactor = 1;
    private double velConversionFactor = 1;
    private boolean isInverted = false;

    public SimulatedMotor(int port) {
        this.port = port;

        MechanisimHolders.simulatedMotors.add(this);
        // Generate all mechanisims after all motors are added
        if (!MechanisimHolders.generateMechanisimsCommand.isScheduled()) {
            CommandScheduler.getInstance().schedule(
                    MechanisimHolders.generateMechanisimsCommand);
        }
    }

    @Override
    public String toString() {
        return "SimulatedMotor(port=" + port + ")";
    }

    public void configure(SparkMaxConfig config) {
        ParsedSparkConfig parsedConfig = SparkConfigParser.parseConfig(config);
        this.currentLimit = parsedConfig.currentLimit != null ? parsedConfig.currentLimit : this.currentLimit;
        this.posConversionFactor = parsedConfig.positionConversionFactor != null ? parsedConfig.positionConversionFactor : this.posConversionFactor;
        this.velConversionFactor = parsedConfig.velocityConversionFactor != null ? parsedConfig.velocityConversionFactor : this.velConversionFactor;
        this.isInverted = parsedConfig.isInverted != null ? parsedConfig.isInverted : this.isInverted;
    }

    public void setMechanisim(SimulatedMechanisim mechanisim) {
        this.mechanisim = mechanisim;
    }

    public int getPort() {
        return port;
    }

    public double getPosition() {
        return mechanisim.getPosition() * posConversionFactor;
    }

    public double getVelocity() {
        return mechanisim.getRpm() * velConversionFactor;
    }

    public double getCurrent() {
        return mechanisim.getCurrent();
    }

    public double getVoltage() {
        return mechanisim.getTargetVoltage();
    }

    public double getCurrentLimit() {
        return currentLimit;
    }
}
