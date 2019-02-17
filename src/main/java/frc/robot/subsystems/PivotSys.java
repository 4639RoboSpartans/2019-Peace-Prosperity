/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * Add your docs here.
 */
public class PivotSys extends InjectedSubsystem {
  private static WPI_VictorSPX motor;
  private static AnalogPotentiometer pot;

  private static int motorPort = 0;

  public static boolean isUp = true;

  public PivotSys() {
    motor = new WPI_VictorSPX(motorPort);
    pot = new AnalogPotentiometer(1, 360, 30);
  }

  public void setMotor(double speed) {
    motor.set(speed);
  }

  public AnalogPotentiometer getPotentiometer() {
    return pot;
  }
}
