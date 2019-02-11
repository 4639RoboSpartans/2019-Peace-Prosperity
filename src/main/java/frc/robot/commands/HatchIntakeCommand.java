/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.HatchIntakeSys;

public class HatchIntakeCommand extends Command {
  private HatchIntakeSys m_intake;
  private OI m_oi;
  public HatchIntakeCommand(HatchIntakeSys intake, OI oi) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_intake = intake;
    m_oi = oi;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean left = m_oi.leftButtonPressed();
    boolean right = m_oi.rightButtonPressed();

    //  if both buttons are currently pressed do nothing
    if(left ^ right) {
      // double check these values
      m_intake.setServo(left ? 0.5:0);
    }
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