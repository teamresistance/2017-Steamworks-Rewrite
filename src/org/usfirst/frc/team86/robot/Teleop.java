package org.usfirst.frc.team86.robot;

import org.usfirst.frc.team86.util.Updatable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop implements Updatable {
	
	private Updatable[] subsystems;
	
	public Teleop(Updatable...subsystems) {
		this.subsystems = subsystems;
	}

	@Override
	public void init() {
		for (Updatable subsystem: subsystems) {
			subsystem.init();
		}
		
	}

	@Override
	public void update() {
		for (Updatable subsystem: subsystems) {
			subsystem.update();
		}
		SmartDashboard.putNumber("# Subsystems", subsystems.length);
	}

}
