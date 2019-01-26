/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrainSys;

public class DriveCommand extends Command {
  private DriveTrainSys m_drive;
  private OI m_oi;

  public DriveCommand(DriveTrainSys m_drive, OI m_oi) {
    this.m_drive = m_drive;
    this.m_oi = m_oi;
    requires(m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  } 

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_drive.drive(m_oi.getControllerX(), m_oi.getControllerY(), m_oi.getControllerZ());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
