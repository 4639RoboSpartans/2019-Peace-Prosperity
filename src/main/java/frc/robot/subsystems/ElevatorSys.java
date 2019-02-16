/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import frc.robot.enums.Height;

//pitch diam 1.432 inches
//use that diameter to find the arc length
//18 tooth from vex

/**
 * Add your docs here.
 */
public class ElevatorSys extends InjectedSubsystem {
  private static final int motorPort = 5;
  private static final int encChannel0 = 0;
  private static final int encChannel1 = 1;
  private static final double P = 0, I = 0, D = 0;

  private final WPI_TalonSRX motor;
  private final Encoder enc;
  private final PIDController pid;

  public ElevatorSys() {
    this.motor = new WPI_TalonSRX(motorPort);
    this.enc = new Encoder(encChannel0, encChannel1, false, Encoder.EncodingType.k4X);
    this.pid = new PIDController(P, I, D, enc, motor);
    this.pid.setOutputRange(-1, 1);
    this.pid.enable();
  }

  public void move(Height height) {
    pid.setSetpoint(height.getHeight());
  }

  public void move(double speed) {
    motor.set(speed);
  }

  public void resetPid() {
    pid.reset();
  }

  public Encoder getEncoder() {
    return enc;
  }
}
