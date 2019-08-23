/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCmd extends Command {

	public WaitCmd(double seconds) {
		setTimeout(seconds);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
