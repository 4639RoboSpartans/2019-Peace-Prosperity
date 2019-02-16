/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.enums.Hatch;
import frc.robot.subsystems.HatchIntakeSys;

public class HatchIntakeCommand extends Command {
  private final HatchIntakeSys m_intake;
  private final Hatch hatch;

  public HatchIntakeCommand(HatchIntakeSys m_intake, Hatch hatch) {
    this.m_intake = m_intake;
    this.hatch = hatch;
    requires(m_intake);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    m_intake.setServo(hatch.getAmount());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
