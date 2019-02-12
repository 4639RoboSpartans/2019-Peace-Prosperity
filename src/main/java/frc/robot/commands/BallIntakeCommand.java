/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.BallIntakeSys;

public class BallIntakeCommand extends Command {

private BallIntakeSys m_intake;
private OI m_oi;

  public BallIntakeCommand(BallIntakeSys intake, OI oi) {
    m_intake = intake;
    m_oi = oi;
    requires(m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

   // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_intake.ballIntake(m_oi.getLeftTrigger(), m_oi.getRightTrigger());
  }

  // Make this  return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    m_intake.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
