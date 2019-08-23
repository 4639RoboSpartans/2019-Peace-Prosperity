/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.subsystems.ElevatorSys;

public class ElevatorDropCmd extends InstantCommand {
	private final ElevatorSys m_elevator;

	public ElevatorDropCmd(ElevatorSys es) {
		this.m_elevator = es;
		requires(m_elevator);
	}

	@Override
	protected void initialize() {
		m_elevator.drop();
	}
}
