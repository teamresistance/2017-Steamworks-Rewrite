package org.usfirst.frc.team86.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team86.util.Button;

import java.util.ArrayList;

public class JoystickIO {
	// Joysticks
	public static Joystick leftJoystick;
	public static Joystick rightJoystick;
	public static Joystick coJoystick;
	public static Joystick xboxController;

	// Buttons
	private static ArrayList<Button> buttons = new ArrayList<>();

	public static Button btnGyroReset;
	public static Button xbxBtnGyroReset;

	public static Button btnHoldLeft;
	public static Button btnHoldCenter;
	public static Button btnHoldRight;
	
	public static Button btnHoldLeftHopper;
	public static Button btnHoldRightHopper;
	
	public static Button btnPickupGear;
	public static Button xbxBtnPickupGear;
	public static Button btnPlaceGear;
	public static Button xbxBtnPlaceGear;
	public static Button btnShooter;
	public static Button xbxBtnShooter;
	public static Button btnAgitator;
	public static Button xbxBtnAgitator;
	public static Button btnClimber;
	public static Button xbxBtnClimber;

	public static Button btnAuto;
	
	public static void update() {
		for (Button b : buttons) {
			b.update();
		}
	}
	

	private static Button createButton(GenericHID stick, int button) {
		Button newButton = new Button(stick, button);
		buttons.add(newButton);
		return newButton;
	}

	static {
		try{
			leftJoystick = new Joystick(1);
			rightJoystick = new Joystick(0);
			coJoystick = new Joystick(2);
			xboxController = new Joystick(3);
			btnPickupGear = createButton(coJoystick, 2);
			//xbxBtnPickupGear = createButton(xboxController, 1);
			btnPlaceGear = createButton(coJoystick, 5);
			//xbxBtnPlaceGear = createButton(xboxController, 2);
		
			btnShooter = createButton(coJoystick, 1);
			xbxBtnShooter = createButton(xboxController, 5);
			btnAgitator = createButton(coJoystick, 3);
			xbxBtnAgitator = createButton(xboxController, 6);
			btnClimber = createButton(coJoystick, 8);
			xbxBtnClimber = createButton(xboxController, 4);

			btnGyroReset = createButton(rightJoystick, 6);
			xbxBtnGyroReset = createButton(xboxController, 8);


			btnHoldLeft = createButton(rightJoystick, 4);
			btnHoldCenter = createButton(rightJoystick, 3);
			btnHoldRight = createButton(rightJoystick, 5);
			btnHoldLeftHopper = createButton(rightJoystick, 8);
			btnHoldRightHopper = createButton(rightJoystick, 9);
			btnAuto = createButton(leftJoystick, 10);
		} catch(Exception e) {}
	}
}
