package frc.robot.Real.Motors;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Real.Mechanisims.MechanisimHolders;
import frc.robot.Real.Mechanisims.SimulatedMechanisim;
import frc.robot.Real.Motors.SparkConfigParser.ParsedSparkConfig;

public class SimulatedMotor {
    private int port;
    private SimulatedMechanisim mechanisim;
    private double currentLimit = 80;
    private double posConversionFactor = 1;
    private double velConversionFactor = 1;
    // TODO: handle inverted
    private boolean isInverted = false;

    public SimulatedMotor(int port) {
        this.port = port;

        MechanisimHolders.simulatedMotors.add(this);
        // Generate all mechanisims after all motors are added
        if (!MechanisimHolders.hasGeneratedMechanisims) {
            CommandScheduler.getInstance().schedule(new RunCommand(() -> MechanisimHolders.generateMechanisims()));
        }
    }

    @Override
    public String toString() {
        return "SimulatedMotor(port=" + port + ")";
    }

    public void configure(SparkMaxConfig config) {
        ParsedSparkConfig parsedConfig = SparkConfigParser.parseConfig(config);
        this.currentLimit = parsedConfig.currentLimit != null ? parsedConfig.currentLimit : this.currentLimit;
        this.posConversionFactor = parsedConfig.positionConversionFactor != null ? parsedConfig.positionConversionFactor
                : this.posConversionFactor;
        this.velConversionFactor = parsedConfig.velocityConversionFactor != null ? parsedConfig.velocityConversionFactor
                : this.velConversionFactor;
        this.isInverted = parsedConfig.isInverted != null ? parsedConfig.isInverted : this.isInverted;
    }

    public void setMechanisim(SimulatedMechanisim mechanisim) {
        this.mechanisim = mechanisim;
    }

    public int getPort() {
        return port;
    }

    public double getPosition() {
        if (mechanisim == null) {
            return 0;
        }
        return mechanisim.getPosition() * posConversionFactor;
    }

    public double getVelocity() {
        if (mechanisim == null) {
            return 0;
        }
        return mechanisim.getRpm() * velConversionFactor;
    }

    public double getCurrent() {
        if (mechanisim == null) {
            return 0;
        }
        return mechanisim.getCurrent();
    }

    public double getVoltage() {
        if (mechanisim == null) {
            return 0;
        }
        return mechanisim.getTargetVoltage();
    }

    public double getCurrentLimit() {
        return currentLimit;
    }

    public void setVoltage(double voltage) {
        if (mechanisim != null) {
            mechanisim.setVoltage(voltage);
        }
    }

    public void set(double speed) {
        setVoltage(speed * 12);
    }
}
