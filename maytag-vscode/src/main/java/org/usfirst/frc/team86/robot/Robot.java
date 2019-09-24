package org.usfirst.frc.team86.robot;

import org.usfirst.frc.team86.XBOX.*;
import org.usfirst.frc.team86.util.Time;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
						//TODO: use TimedRobot
	// Robot States
	private Teleop teleop;
	private Teleop xbxTele;
	
	// Robot Subsystems
	private Drive drive;
	private Climber climber;
	private Gear gear;
	private Shooter shooter;
	private XboxDrive xbxDrive;
	private XboxClimber xbxClimber;
	private XboxGear xbxGear;
	private XboxShooter xbxShoot;
	
	@Override
	public void robotInit() {
		//TODO: setPeriod(x ms)
		// subsystem instantiations
		
		// With all configurations
		
		/*
		drive = configuration.get(Drive.class, "drive", IO.leftFrontMotor,
				IO.leftRearMotor,
				IO.rightFrontMotor,
				IO.rightRearMotor,
				IO.navX);
		climber = configuration.get(Climber.class, "climber", IO.climberMotor, IO.pdp);
		gear = configuration.get(Gear.class, "gear", IO.rotateSolenoid, IO.gripSolenoid,
				IO.extendSolenoid, IO.gearLights,
				IO.gearFindBanner, IO.gearAlignBanner,
				IO.gearRotatorMotor);
		shooter = configuration.get(Shooter.class, "shooter", IO.shooterMotor, IO.feederMotor,
				IO.agitatorMotor, IO.vibratorMotor);
		*/
		
		// To test Configuration with Shooter RPM
		drive = new Drive(IO.leftFrontMotor,
				IO.leftRearMotor,
				IO.rightFrontMotor,
				IO.rightRearMotor,
				IO.navX);
		xbxDrive = new XboxDrive(IO.leftFrontMotor,
		IO.leftRearMotor,
		IO.rightFrontMotor,
		IO.rightRearMotor,
		IO.navX);

		shooter = new Shooter(IO.shooterMotor, IO.feederMotor, IO.agitatorMotor, IO.vibratorMotor);
		xbxShoot = new XboxShooter(IO.shooterMotor, IO.feederMotor, IO.agitatorMotor, IO.vibratorMotor);

		climber = new Climber(IO.climberMotor, IO.pdp);
		xbxClimber = new XboxClimber(IO.climberMotor, IO.pdp);

		gear = new Gear(IO.rotateSolenoid, IO.gripSolenoid,
				IO.extendSolenoid, IO.gearLights,
				IO.gearFindBanner, IO.gearAlignBanner,
				IO.gearRotatorMotor);
		
		xbxGear = new XboxGear(IO.rotateSolenoid, IO.gripSolenoid,
		IO.extendSolenoid, IO.gearLights,
		IO.gearFindBanner, IO.gearAlignBanner,
		IO.gearRotatorMotor);
		
		// robot state instantiations
		teleop = new Teleop(drive, climber, gear, shooter);
		xbxTele = new Teleop(xbxDrive, xbxClimber, xbxGear, xbxShoot);
		SmartDashboard.putBoolean("XBOXCONTROL", false);
	}
	
	@Override
	public void robotPeriodic() {
		
	}

	
	@Override
	public void autonomousInit() {
		
	}

	
	@Override
	public void autonomousPeriodic() {
		
	}
	
	@Override
	public void teleopInit() {
		IO.navX.reset();
		teleop.init();
		xbxTele.init();
	}

	@Override
	public void teleopPeriodic() {
		IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kForward : Relay.Value.kOff);
		Time.update();
		JoystickIO.update();

		if (!SmartDashboard.getBoolean("XBOXCONTROL", false)) {
			teleop.update();
			if (JoystickIO.btnGyroReset.onButtonPressed()) {
				IO.navX.reset();
			}
		} else {
			xbxTele.update();
			if (JoystickIO.xbxBtnGyroReset.onButtonPressed()) {
				IO.navX.reset();
			}
		}
	}

	@Override
	public void testPeriodic() {
		
	}
}

