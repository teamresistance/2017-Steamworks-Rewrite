package org.usfirst.frc.team86.util;

public abstract class Gyro {
	
	public abstract double getAngle();
	
	public double getNormalizedAngle() {
		return ((getAngle()% 360) + 360) % 360;
	}
	
	public abstract void reset();
	
}
