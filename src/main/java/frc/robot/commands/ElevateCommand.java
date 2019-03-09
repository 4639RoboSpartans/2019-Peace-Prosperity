/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.enums.Height;
import frc.robot.subsystems.ElevatorSys;

public class ElevateCommand extends InstantCommand {
	private final ElevatorSys m_elevator;
	private final Height height;

	public ElevateCommand(ElevatorSys es, Height height) {
		this.m_elevator = es;
		requires(m_elevator);
		this.height = height;
	}

	@Override
	protected void initialize() {
		m_elevator.move(height);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ElevateCommand)) {
			return false;
		}
		ElevateCommand e = (ElevateCommand) o;
		return height == e.height;
	}
}
