/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.enums.Pivot;
import frc.robot.subsystems.BallIntakeSys;

public class PivotIntakeCommand extends Command {
  private final BallIntakeSys m_intake;
  private final Pivot pivot;

  public PivotIntakeCommand(BallIntakeSys m_intake, Pivot pivot) {
    this.m_intake = m_intake;
    this.pivot = pivot;
    requires(m_intake);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    m_intake.pivot(pivot);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
