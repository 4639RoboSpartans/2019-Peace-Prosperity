/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.BallIntakeSys;

public class BallIntakeCmd extends Command {

	private final BallIntakeSys m_intake;
	private final boolean intake;

	public BallIntakeCmd(BallIntakeSys m_intake, boolean intake) {
		this.m_intake = m_intake;
		this.intake = intake;
		requires(m_intake);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		m_intake.intake(intake ? 0.7 : -1);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		m_intake.stopIntake();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
