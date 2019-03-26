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

import com.ctre.phoenix.motorcontrol.ControlMode;

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
		double amount = m_oi.getLeftY(1) * 0.5;
		if (m_elevator.getControlMode() != ControlMode.PercentOutput) {
			if (amount > 0) {
				m_elevator.manual(amount);
			}
		} else {
			m_elevator.manual(amount);
		}
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
