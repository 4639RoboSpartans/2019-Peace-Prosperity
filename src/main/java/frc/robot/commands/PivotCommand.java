/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.PivotSys;

public class PivotCommand extends PIDCommand {
  private PivotSys m_pivot;
  private static double P = 0, I = 0, D = 0;
  private static double highSetpoint = 90;
  private static double lowSetpoint = 180;

  public PivotCommand(PivotSys pivot) {
    super(P, I, D, pivot);
    m_pivot = pivot;
    getPIDController().setPercentTolerance(1);
    setSetpoint(PivotSys.isUp ? lowSetpoint : highSetpoint);
  }

  @Override
  protected double returnPIDInput() {
    return m_pivot.getPotentiometer().get();
  }

  @Override
  protected void usePIDOutput(double output) {
    m_pivot.setMotor(output);
  }

  @Override
  protected boolean isFinished() {
    boolean isOnTarget = getPIDController().onTarget();
    if (isOnTarget)
      m_pivot.isUp = !m_pivot.isUp;
    return isOnTarget;
  }
}
