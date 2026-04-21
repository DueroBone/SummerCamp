package frc.robot.Real.Motors;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Robot;

public class LearnSparkMax implements MotorController {
    private final boolean isReal;
    private SparkMax realMotor;
    private SimulatedMotor simulatedMotor;

    public LearnSparkMax(int port, SparkMax.MotorType motorType) {
        isReal = Robot.isReal();
        if (isReal) {
            realMotor = new SparkMax(port, motorType);
        } else {
            simulatedMotor = new SimulatedMotor(port);
        }
    }

    public void configure(SparkMaxConfig config, ResetMode resetMode, PersistMode persistMode) {
        if (isReal) {
            realMotor.configure(config, resetMode, persistMode);
        } else {
            simulatedMotor.configure(config);
        }
    }

    @Override
    public void set(double speed) {
        // Code to set the motor speed using the Spark API
    }

    @Override
    public double get() {
        // Code to get the current motor speed using the Spark API
        return 0;
    }

    @Override
    public void setInverted(boolean isInverted) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInverted'");
    }

    @Override
    public boolean getInverted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInverted'");
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disable'");
    }

    @Override
    public void stopMotor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopMotor'");
    }

}
