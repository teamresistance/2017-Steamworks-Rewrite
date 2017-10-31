package org.usfirst.frc.team86.robot;

import org.usfirst.frc.team86.util.Updatable;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.VictorSP;

public class Shooter implements Updatable {
	
	private CANTalon shooterMotor;
	private VictorSP feederMotor;
	private VictorSP agitatorMotor;
	private VictorSP vibratorMotor;

	public Shooter(CANTalon shooterMotor, VictorSP feederMotor,
			VictorSP agitatorMotor, VictorSP vibratorMotor) {
		this.shooterMotor = shooterMotor;
		this.feederMotor = feederMotor;
		this.agitatorMotor = agitatorMotor;
		this.vibratorMotor = vibratorMotor;
	}

	@Override
	public void init() {
		// Shooter Motor Init
		IO.shooterMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		IO.shooterMotor.reverseSensor(false);
		IO.shooterMotor.reverseOutput(false);
		IO.shooterMotor.configEncoderCodesPerRev(20);
		IO.shooterMotor.configNominalOutputVoltage(+0.0, -0.0);
		IO.shooterMotor.configPeakOutputVoltage(12.0, 0.0);
	}

	@Override
	public void update() {
		if (JoystickIO.btnShooter.isDown()) {
			IO.feederMotor.set(1.0);
			IO.shooterMotor.changeControlMode(TalonControlMode.Speed);
			IO.shooterMotor.set(3100);
			if (JoystickIO.btnAgitator.isDown()) {
				IO.agitatorMotor.set(0.45);
				IO.vibratorMotor.set(0.6);
			} else {
				IO.agitatorMotor.set(0.0);
			}
		} else {
			IO.shooterMotor.changeControlMode(TalonControlMode.PercentVbus);
			IO.shooterMotor.set(0.0);
			IO.feederMotor.set(0.0);
			IO.agitatorMotor.set(0.0);
			IO.vibratorMotor.set(0.0);
		}
	}

}
