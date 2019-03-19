/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.PivotSys;

public class ManualPivotCmd extends Command {
	private final PivotSys m_pivot;
	private final OI m_oi;

	public ManualPivotCmd(PivotSys m_pivot, OI m_oi) {
		this.m_pivot = m_pivot;
		requires(m_pivot);
		this.m_oi = m_oi;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		m_pivot.set(m_oi.getRightY(1));
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
