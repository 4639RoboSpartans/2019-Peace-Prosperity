/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.*;
import frc.robot.subsystems.BallIntakeSys;
import frc.robot.subsystems.DriveTrainSys;
import frc.robot.subsystems.ElevatorSys;
import frc.robot.util.enums.Height;

public class Robot extends TimedRobot {
	private OI m_oi;
	private DriveTrainSys m_drive;
	private ElevatorSys m_elevator;
	private BallIntakeSys m_ballIntake;

	@Override
	public void robotInit() {
		m_ballIntake = new BallIntakeSys();
		m_drive = new DriveTrainSys();
		m_elevator = new ElevatorSys();
		m_oi = new OI();
		m_drive.setDefaultCommand(new ManualDriveCmd(m_drive, m_oi));
		m_ballIntake.setDefaultCommand(new BallIntakeCmd(m_ballIntake, m_oi));
		m_elevator.setDefaultCommand(new ElevatorManualCmd(m_elevator, m_oi));

		m_oi.getButton(1, 3).whenPressed(new ElevatorDropCmd(m_elevator));
		m_oi.getButton(1, 2).whenPressed(new ElevatorUndropCmd(m_elevator));
		m_oi.getButton(1, 1).whenPressed(new ElevatorMoveCmd(m_elevator, Height.GROUND));

		m_oi.getPovButton(1, 0).whenPressed(new ElevatorMoveCmd(m_elevator, Height.HIGH_HATCH));
		m_oi.getPovButton(1, 2).whenPressed(new ElevatorMoveCmd(m_elevator, Height.MIDDLE_HATCH));
		m_oi.getPovButton(1, 4).whenPressed(new ElevatorMoveCmd(m_elevator, Height.LOW_HATCH));
		m_oi.getPovButton(1, 6).whenPressed(new ElevatorMoveCmd(m_elevator, Height.LOAD));

		for (Height h : Height.values()) {
			SmartDashboard.putData(h.name(), new ElevatorMoveCmd(m_elevator, h));
		}
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
