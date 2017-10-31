package org.usfirst.frc.team86.util;


/**
 * @author Shreya Ravi
 */
public interface SingleSolenoid {
  void extend();
  void retract();
  boolean isExtended();
  boolean isRetracted();
}
