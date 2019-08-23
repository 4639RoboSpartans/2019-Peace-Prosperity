/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.BallIntakeSys;

public class BallIntakeCmd extends Command {
	private final BallIntakeSys m_intake;
	private final OI m_oi;

	public BallIntakeCmd(BallIntakeSys m_intake, OI m_oi) {
		this.m_intake = m_intake;
		requires(m_intake);
		this.m_oi = m_oi;
	}

	@Override
	protected void execute() {
		double left = m_oi.getLeftTrigger(1);
		double right = m_oi.getRightTrigger(1);
		if (left != 0 && right != 0) {
			m_intake.stop();
		} else {
			if (left > 0) {
				m_intake.intake(0.5);
			} else if (right > 0) {
				m_intake.intake(-0.5);
			} else {
				m_intake.stop();
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		m_intake.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
