/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.subsystems.ElevatorSys;
import frc.robot.util.enums.Height;

public class ElevatorMoveCmd extends InstantCommand {
	private final ElevatorSys m_elevator;
	private final Height height;

	public ElevatorMoveCmd(ElevatorSys es, Height height) {
		this.m_elevator = es;
		requires(m_elevator);
		this.height = height;
	}

	@Override
	protected void initialize() {
		m_elevator.move(height);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
