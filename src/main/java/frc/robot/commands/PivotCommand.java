/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;

import frc.robot.enums.Pivot;
import frc.robot.subsystems.PivotSys;

public class PivotCommand extends PIDCommand {
	private static final double P = 0, I = 0, D = 0;

	private final PivotSys m_pivot;
	private final Pivot pivot;

	public PivotCommand(PivotSys m_pivot, Pivot pivot) {
		super(P, I, D, m_pivot);
		this.m_pivot = m_pivot;
		requires(m_pivot);
		this.pivot = pivot;
		getPIDController().setPercentTolerance(1);
		setSetpoint(pivot.getAmount());
	}

	@Override
	protected double returnPIDInput() {
		return m_pivot.getPotentiometer().get();
	}

	@Override
	protected void usePIDOutput(double output) {
		m_pivot.setMotor(output);
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}
}
