/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.DriveTrainSys;

import com.kauailabs.navx.frc.AHRS;

public class DriveCommand extends Command {
	private final DriveTrainSys m_drive;
	private final OI m_oi;
	private final AHRS navx;

	// toggle for field oriented driving
	private static final boolean fieldOriented = false;

	public DriveCommand(DriveTrainSys m_drive, OI m_oi, AHRS navx) {
		this.m_drive = m_drive;
		this.m_oi = m_oi;
		this.navx = navx;
		requires(m_drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		m_drive.drive(m_oi.getLeftX(0), m_oi.getLeftY(0), m_oi.getRightX(0), (fieldOriented ? navx.getYaw() : 0.0));
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
	}
}
