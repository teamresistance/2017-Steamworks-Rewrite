package org.usfirst.frc.team86.robot;

import org.usfirst.frc.team86.util.InvertibleDigitalInput;
import org.usfirst.frc.team86.util.InvertibleSolenoid;

public class InvertibleSolenoidWithPosition extends InvertibleSolenoid {

  private final InvertibleDigitalInput retractedLimit;

  public InvertibleSolenoidWithPosition(
      int module,
      int channel,
      boolean isSolenoidInverted,
      InvertibleDigitalInput retractedLimit) {
    super(module, channel, isSolenoidInverted);
    this.retractedLimit = retractedLimit;
  }

  @Override
  public boolean isRetracted() {
    return retractedLimit.get();
  }
}
