package frc.robot;

import org.usfirst.frc.team86.util.Time;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;

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
	
	// Robot Subsystems
	private Drive drive;
	private Climber climber;
	private Gear gear;
	private Shooter shooter;
	
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
		
		shooter = new Shooter(IO.shooterMotor, IO.feederMotor, IO.agitatorMotor, IO.vibratorMotor);
		climber = new Climber(IO.climberMotor, IO.pdp);
		gear = new Gear(IO.rotateSolenoid, IO.gripSolenoid,
				IO.extendSolenoid, IO.gearLights,
				IO.gearFindBanner, IO.gearAlignBanner,
				IO.gearRotatorMotor);
		
		
		// robot state instantiations
		teleop = new Teleop(drive, climber, gear, shooter);
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
	}

	@Override
	public void teleopPeriodic() {
		IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kForward : Relay.Value.kOff);
		Time.update();
		JoystickIO.update();
		teleop.update();
	}

	@Override
	public void testPeriodic() {
		
	}
}

