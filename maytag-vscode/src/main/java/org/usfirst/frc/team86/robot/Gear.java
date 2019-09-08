package frc.robot;

import org.usfirst.frc.team86.util.InvertibleDigitalInput;
import org.usfirst.frc.team86.util.SingleSolenoid;
import org.usfirst.frc.team86.util.Time;
import org.usfirst.frc.team86.util.Updatable;

import edu.wpi.first.wpilibj.VictorSP;

public class Gear implements Updatable {

	private final double INSERT_DELAY = 0.5;
	private final double GRAB_DELAY = 0.1;
	private final double START_ROTATE_DELAY = 1.0;
	private final double RELEASE_DELAY = 3.0;
	private final double ROTATE_TIMEOUT = 5.0;
	private final double PICKUP_DELAY = 0.9;
	
	private SingleSolenoid rotateSolenoid;
	private SingleSolenoid gripSolenoid;
	private SingleSolenoid extendSolenoid;
	private SingleSolenoid gearLights;
	private InvertibleDigitalInput gearFindBanner;
	private InvertibleDigitalInput gearAlignBanner;
	private VictorSP gearRotatorMotor;
	
	private int currentState = 0;
	private double initialTime = Time.getTime();
	
	public Gear(SingleSolenoid rotateSolenoid,
			SingleSolenoid gripSolenoid,
			SingleSolenoid extendSolenoid, 
			SingleSolenoid gearLights,
			InvertibleDigitalInput gearFindBanner,
			InvertibleDigitalInput gearAlignBanner,
			VictorSP gearRotatorMotor) {
		this.rotateSolenoid = rotateSolenoid;
		this.gripSolenoid = gripSolenoid;
		this.extendSolenoid = extendSolenoid;
		this.gearLights = gearLights;
		this.gearFindBanner = gearFindBanner;
		this.gearAlignBanner = gearAlignBanner;
		this.gearRotatorMotor = gearRotatorMotor;
	}
	
	public void init() {
		
	}
	
	public void update() {
		switch(currentState) {
		case 0: // Default
			// control lights
			readyToPlace();
//			setPos(true, false, false);
			if(JoystickIO.btnPickupGear.onButtonPressed()) {
				currentState = 1;
				initialTime = Time.getTime();
			} else if(JoystickIO.btnPlaceGear.onButtonPressed()) {
				currentState = 10;
			}
			break;
		case 1: // Waiting
			setPos(false, true, false);
			if(!JoystickIO.btnPickupGear.isDown()) {
				currentState = 2;
			} else if(gearFindBanner.get() && Time.getTime() - initialTime > PICKUP_DELAY) {
				currentState = 2;
			}
			break;
		case 2: // Extend and start delay
			setPos(false, true, true);
			initialTime = Time.getTime();
			currentState = 3;
		case 3:
			if(Time.getTime() - initialTime >= INSERT_DELAY) {
				currentState = 4;
			}
			break;
		case 4: // Expand and start delay
			setPos(true, true, true);
			initialTime = Time.getTime();
			currentState = 5;
		case 5:
			if(Time.getTime() - initialTime >= GRAB_DELAY) {
				currentState = 6;
			}
			break;
		case 6: // Retract
			setPos(true, true, false);
			if(extendSolenoid.isRetracted()) {
				currentState = 7;
			}
			break;
		case 7: // Rotate up and start delay
			setPos(true, false, false);
			initialTime = Time.getTime();
			currentState = 8;
			break;
		case 8:
			if(Time.getTime() - initialTime >= START_ROTATE_DELAY) {
				currentState = 12;
			}
			break;
		case 12: // Run motor to align gear
			initialTime = Time.getTime();
			currentState = 9;
			gearRotatorMotor.set(0.25);
			break;
		case 9:
			if(gearAlignBanner.get() || (Time.getTime() - initialTime >= ROTATE_TIMEOUT)) {
				gearRotatorMotor.set(0.0);
				currentState = 0;
			} else if(JoystickIO.btnPickupGear.onButtonPressed()) {
				gearRotatorMotor.set(0.0);
				currentState = 1;
			}
			break;
		case 10: // Release gear and start delay
			setPos(false, false, false);
			initialTime = Time.getTime();
			currentState = 11;
			break;
		case 11:
			if(Time.getTime() - initialTime >= RELEASE_DELAY) {
				currentState = 0;
			} else if(JoystickIO.btnPickupGear.onButtonPressed()) {
				currentState = 1;
			}
			break;
		default:
			currentState = 0;
			break;
		}
	}
	
	public void readyToPlace() {
		setPos(true, false, false);
	}
	
	public void searching() {
		
	}
	
	public void setPos(boolean grip, boolean rotate, boolean extend) {
		if(grip) {
			// set lights to false here
			gearLights.extend();
			gripSolenoid.extend();
		} else {
			// set lights to true here
			gearLights.retract();
			gripSolenoid.retract();
		}
		if(rotate)
			rotateSolenoid.extend();
		else
			rotateSolenoid.retract();
		
		if(extend)
			extendSolenoid.extend();
		else
			extendSolenoid.retract();
	}
	
}