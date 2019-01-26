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
import edu.wpi.first.wpilibj.SpeedControllerGroup;

//pitch diam 1.432 inches
//use that diameter to find the arc length
//18 tooth from vex

/**
 * Add your docs here.
 */
public class ElevatorSys extends InjectedSubsystem {
  private static int motorLeftPort = 5;
  private static int motorRightPort = 6;

  private static WPI_TalonSRX motorLeft;
  private static WPI_TalonSRX motorRight;

  //placeholder values
  public static int[] heights = {0, 1, 2, //hatch heights
    0, 1, 2}; //ball heights

  private static Encoder enc;

  private static int encChannel0 = 0;
  private static int encChannel1 = 1;

  private double P = 0, I = 0, D = 0;
  private PIDController pid;

  private static SpeedControllerGroup elevator; 

  public ElevatorSys() {
    motorLeft = new WPI_TalonSRX(motorLeftPort);
    motorRight = new WPI_TalonSRX(motorRightPort);

    elevator = new SpeedControllerGroup(motorLeft, motorRight);

    enc = new Encoder(encChannel0,encChannel1, false, Encoder.EncodingType.k4X);

    pid = new PIDController(P, I, D, enc, elevator);
  }

  public void move(int num) {
    pid.setSetpoint(heights[num]);
    pid.enable();
  }

  public void resetPid() {
    pid.reset();
  }
}
