package org.usfirst.frc.team86.util;

public interface SingleSolenoid {
  void extend();
  void retract();
  boolean isExtended();
  boolean isRetracted();
}
