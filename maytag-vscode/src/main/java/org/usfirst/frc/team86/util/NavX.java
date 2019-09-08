package frc.util;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class NavX extends Gyro {
	
	public AHRS ahrs = new AHRS(SPI.Port.kMXP);
	
	public double getAngle() {
		return ahrs.getAngle();
	}
	
	public void reset() {
		ahrs.reset();
	}
	
	public AHRS getAHRS() {
		return ahrs;
	}

}
