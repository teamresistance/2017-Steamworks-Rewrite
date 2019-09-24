package org.usfirst.frc.team86.XBOX;

import org.usfirst.frc.team86.robot.JoystickIO;
import org.usfirst.frc.team86.util.Updatable;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class XboxShooter implements Updatable {
	
	private TalonSRX shooterMotor;
	private VictorSP feederMotor;
	private VictorSP agitatorMotor;
	private VictorSP vibratorMotor;
	
	private double targetRPM = 3100.0;

	public XboxShooter(TalonSRX shooterMotor, VictorSP feederMotor,
			VictorSP agitatorMotor, VictorSP vibratorMotor) {
		this.shooterMotor = shooterMotor;
		this.feederMotor = feederMotor;
		this.agitatorMotor = agitatorMotor;
		this.vibratorMotor = vibratorMotor;
	}

	@Override
	public void init() {
		// Shooter Motor Init
		shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		shooterMotor.setInverted(false);
		shooterMotor.setSensorPhase(false);
	}

	@Override
	public void update() {
		if (JoystickIO.xbxBtnShooter.isDown()) {
			feederMotor.set(1.0);
			shooterMotor.set(ControlMode.Velocity, targetRPM);
			if (JoystickIO.xbxBtnAgitator.isDown()) {
				agitatorMotor.set(0.45);
				vibratorMotor.set(0.6);
			} else {
				agitatorMotor.set(0.0);
			}
		} else {
			shooterMotor.set(ControlMode.PercentOutput, 0.0);
			feederMotor.set(0.0);
			agitatorMotor.set(0.0);
			vibratorMotor.set(0.0);
		}
	}

}