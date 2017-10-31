package org.usfirst.frc.team86.robot;

import org.usfirst.frc.team86.util.Util;
import org.usfirst.frc.team86.util.Gyro;
import org.usfirst.frc.team86.util.MecanumDrive;
import org.usfirst.frc.team86.util.MecanumDrive.DriveType;
import org.usfirst.frc.team86.util.Updatable;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive implements Updatable {
	
	private MecanumDrive drive;
	private Gyro gyro;
	
	private boolean grabAngleOnce = false;
	private double holdAngle = 0;

	public Drive(SpeedController leftFrontMotor,
			SpeedController leftRearMotor, 
			SpeedController rightFrontMotor, 
			SpeedController rightRearMotor, 
			Gyro gyro) {
		this.drive = new MecanumDrive(leftFrontMotor, leftRearMotor,
				rightFrontMotor, rightRearMotor, gyro);
		this.gyro = gyro;
	}
	
	@Override
	public void init() {
		
	}
	
	public void update() {
		double scaledX = Util.scaleInput(JoystickIO.leftJoystick.getX());
		double scaledY = Util.scaleInput(JoystickIO.leftJoystick.getY());
		double scaledRotate = Util.scaleInput(JoystickIO.rightJoystick.getX());
		
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
