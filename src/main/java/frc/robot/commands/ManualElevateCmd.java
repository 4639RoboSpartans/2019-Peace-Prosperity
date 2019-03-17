/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.ElevatorSys;

public class ManualElevateCmd extends Command {
	private final ElevatorSys m_elevator;
	private final OI m_oi;

	public ManualElevateCmd(ElevatorSys m_elevator, OI m_oi) {
		this.m_elevator = m_elevator;
		requires(m_elevator);
		this.m_oi = m_oi;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		m_elevator.manual(m_oi.getLeftY(1));
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
