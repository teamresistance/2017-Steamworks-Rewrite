package frc.robot;


import org.usfirst.frc.team86.util.Gyro;
import org.usfirst.frc.team86.util.InvertibleDigitalInput;
import org.usfirst.frc.team86.util.InvertibleSolenoid;
import org.usfirst.frc.team86.util.NavX;
import org.usfirst.frc.team86.util.SingleSolenoid;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

public class IO {

	public static double VIBRATOR_SPEED = 0.6;

	// PDP
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(0);

	// Motors -- shooting
	public static TalonSRX shooterMotor = new TalonSRX(3);

	public static VictorSP feederMotor = new VictorSP(2);
	public static VictorSP agitatorMotor = new VictorSP(6);

	// Motors -- other
	public static VictorSP vibratorMotor = new VictorSP(0);
	public static VictorSP gearRotatorMotor = new VictorSP(5);
	public static VictorSP climberMotor = new VictorSP(9);

	// Banner Sensors for Gear
	public static InvertibleDigitalInput gearFindBanner = new InvertibleDigitalInput(1, true);
	public static InvertibleDigitalInput gearAlignBanner = new InvertibleDigitalInput(2, true);
	public static InvertibleDigitalInput gearRetractedLimit = new InvertibleDigitalInput(0, true);

	// Drive Motors
	public static Victor leftFrontMotor = new Victor(7);
	public static Victor leftRearMotor = new Victor(8);
	public static Victor rightFrontMotor = new Victor(3);
	public static Victor rightRearMotor = new Victor(1);

	// Pneumatic Cylinders (controlled via Solenoids)
	public static SingleSolenoid gripSolenoid = new InvertibleSolenoid(1, 2, true);
	public static SingleSolenoid extendSolenoid = new InvertibleSolenoidWithPosition(1, 0, false, gearRetractedLimit);
	public static SingleSolenoid rotateSolenoid = new InvertibleSolenoid(1, 1, false);
	public static SingleSolenoid gearLights = new InvertibleSolenoid(1,3, true);
	
	public static Compressor compressor = new Compressor(1);
	public static Relay compressorRelay = new Relay(0);

	// Relay for green LEDs
	public static Relay cameraLights = new Relay(1);
	
	// NavX-MXP navigation sensor
	public static Gyro navX = new NavX();

//	public static UsbCamera gearCamera = CameraServer.getInstance().startAutomaticCapture(0);
//	public static AxisCamera shooterCamera = new AxisCamera("Shooter Camera", "10.0.86.19");
			//CameraServer.getInstance().addAxisCamera("Axis Camera", "axis-camera.local");
	

	static {
		IO.rightFrontMotor.setInverted(true);
		IO.rightRearMotor.setInverted(true);

		IO.climberMotor.setInverted(true);

		IO.feederMotor.setInverted(true);

//		gearCamera.setFPS(20);
//		IO.gearCamera.setResolution(320, 240);
//		IO.gearCamera.setBrightness(0);
	}
}
