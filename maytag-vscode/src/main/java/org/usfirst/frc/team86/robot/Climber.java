package frc.robot;

import org.usfirst.frc.team86.util.Time;
import org.usfirst.frc.team86.util.Updatable;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;

public class Climber implements Updatable {

  private SpeedController climberMotor;
  private PowerDistributionPanel pdp;
  private double prevTime;
  private boolean climbed;
  
  
  // TODO read from file
  private double timeDuration;
  private double spikeLimit;
  
  public Climber(SpeedController climberMotor, PowerDistributionPanel pdp) {
	  this.climberMotor = climberMotor;
	  this.pdp = pdp;
  }

  public void init() {
    this.prevTime = Time.getTime();
    this.climbed = false;
  }

  public void update() {
    double current = pdp.getCurrent(8);
    double currTime = Time.getTime();

    if(JoystickIO.btnClimber.onButtonPressed()) {
    	climbed = false;
    }
    
    if (JoystickIO.btnClimber.isDown() && !climbed) {
      if (current >= spikeLimit) {
        if (currTime - prevTime >= timeDuration) {
          climberMotor.set(0.0);
          climbed = true;
        } else {
          climberMotor.set(1.0);
        }
      } else {
        prevTime = Time.getTime();
        climberMotor.set(1.0);
      }
    } else {
      climberMotor.set(0.0);
    }
  }


}
