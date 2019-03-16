/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.enums.Hatch;
import frc.robot.subsystems.HatchIntakeSys;

public class HatchIntakeCmd extends InstantCommand {
	private final HatchIntakeSys m_intake;

	public HatchIntakeCmd(HatchIntakeSys m_intake) {
		this.m_intake = m_intake;
		requires(m_intake);
	}

	@Override
	public synchronized void start() {
		m_intake.setServo(Hatch.invert(m_intake.getCurState()));
	}
}
