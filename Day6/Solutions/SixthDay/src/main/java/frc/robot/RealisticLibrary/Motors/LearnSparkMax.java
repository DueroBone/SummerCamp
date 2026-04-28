package frc.robot.RealisticLibrary.Motors;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class LearnSparkMax extends SubsystemBase {
    private final boolean isReal;
    private SparkMax realMotor;
    private SimulatedMotor simulatedMotor;
    private LearnEncoder encoder;

    public LearnSparkMax(int port, SparkMax.MotorType motorType) {
        isReal = Robot.isReal();
        if (isReal) {
            realMotor = new SparkMax(port, motorType);
        } else {
            simulatedMotor = new SimulatedMotor(port);
        }
        encoder = new LearnEncoder();
    }

    public void configure(SparkMaxConfig config, ResetMode resetMode, PersistMode persistMode) {
        if (isReal) {
            realMotor.configure(config, resetMode, persistMode);
        } else {
            simulatedMotor.configure(config);
        }
    }

    public void setVoltage(double voltage) {
        if (isReal) {
            realMotor.setVoltage(voltage);
        } else {
            simulatedMotor.setVoltage(voltage);
        }
    }

    /** Input a value -1 to 1 as a percentage */
    public void set(double speed) {
        if (isReal) {
            realMotor.set(speed);
        } else {
            simulatedMotor.set(speed);
        }
    }

    /** Returns the output percentage from -1 to 1 */
    public double get() {
        if (isReal) {
            return realMotor.get();
        } else {
            return simulatedMotor.getVoltage() / 12;
        }
    }

    public LearnEncoder getEncoder() {
        return encoder;
    }

    @Override
    public void periodic() {
        if (isReal) {
            encoder.setPosition(realMotor.getEncoder().getPosition());
            encoder.setVelocity(realMotor.getEncoder().getVelocity());
        } else {
            encoder.setPosition(simulatedMotor.getPosition());
            encoder.setVelocity(simulatedMotor.getVelocity());
        }
    }
}
