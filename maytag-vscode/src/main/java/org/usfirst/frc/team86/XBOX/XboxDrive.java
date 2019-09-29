package org.usfirst.frc.team86.XBOX;

import org.usfirst.frc.team86.robot.JoystickIO;
import org.usfirst.frc.team86.util.Gyro;
import org.usfirst.frc.team86.util.MecanumDrive;
import org.usfirst.frc.team86.util.MecanumDrive.DriveType;
import org.usfirst.frc.team86.util.Updatable;
import org.usfirst.frc.team86.util.Util;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class XboxDrive implements Updatable {
	
	private MecanumDrive drive;
	private Gyro gyro;
	
	private boolean grabAngleOnce = false;
	private double holdAngle = 0;
	
	private double kP = 0.025;
	private double kI;
	private double kD = 0.03;
	private double kF;
	private double maxI;

	private double scaleFactor;

	public XboxDrive(SpeedController leftFrontMotor,
			SpeedController leftRearMotor, 
			SpeedController rightFrontMotor, 
			SpeedController rightRearMotor, 
			Gyro gyro) {
		this.drive = new MecanumDrive(leftFrontMotor, leftRearMotor,
				rightFrontMotor, rightRearMotor, gyro);
		this.gyro = gyro;
		scaleFactor = 1;
	}
	
	@Override
	public void init() {
		drive.setPIDValues(kP, kI, kD, kF, maxI);
	}
	
	public void update() {
		SmartDashboard.putNumber("scaleFactor", scaleFactor);
		double scaledX = Util.scaleInput(JoystickIO.xboxController.getRawAxis(4) * scaleFactor);
		double scaledY = Util.scaleInput(JoystickIO.xboxController.getRawAxis(5) * scaleFactor);
		double scaledRotate = Util.scaleInput(JoystickIO.xboxController.getRawAxis(0) * scaleFactor);

		if (JoystickIO.xbxBtnSpeedShiftUp.onButtonPressed()) {
			scaleFactor += .1;
		} else if (JoystickIO.xbxBtnSpeedShiftDown.onButtonPressed()) {
			scaleFactor -= .1;
		}

		if (scaleFactor > 1) {
			scaleFactor = 1;
		} else if(scaleFactor < .3) {
			scaleFactor = .3;
		}
		
		if (JoystickIO.btnHoldLeft.isDown()) {
			drive.drive(DriveType.ROTATE_PID, scaledX, scaledY, 0, 330);
			grabAngleOnce = true;
		} else if (JoystickIO.btnHoldCenter.isDown()) {
			drive.drive(DriveType.ROTATE_PID, scaledX, scaledY, 0, 270);
			grabAngleOnce = true;
		} else if (JoystickIO.btnHoldRight.isDown()) {
			drive.drive(DriveType.ROTATE_PID, scaledX, scaledY, 0, 210);
			grabAngleOnce = true;
		} else if (JoystickIO.btnHoldLeftHopper.isDown()) {
			drive.drive(DriveType.ROTATE_PID, -1, scaledY, 0, 0);
			grabAngleOnce = true;
		} else if (JoystickIO.btnHoldRightHopper.isDown()) {
			drive.drive(DriveType.ROTATE_PID, 1, scaledY, 0, 180);
			grabAngleOnce = true;
		} else {
			SmartDashboard.putNumber("Angle Being Held", holdAngle);
			if (scaledRotate == 0) {
				SmartDashboard.putBoolean("Is Holding Angle", true);
				if (grabAngleOnce) {
					grabAngleOnce = false;
					holdAngle = gyro.getNormalizedAngle();
				}
				drive.drive(DriveType.ROTATE_PID, scaledX, scaledY, 0, holdAngle);
			} else {
				grabAngleOnce = true;
				drive.drive(DriveType.STICK_FIELD, scaledX, scaledY, scaledRotate, 0);
			}
		}
	}

	
	

}