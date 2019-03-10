/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import frc.robot.commands.DriveCmd;
import frc.robot.commands.ElevateCmd;
import frc.robot.enums.Height;
import frc.robot.subsystems.BallIntakeSys;
import frc.robot.subsystems.DriveTrainSys;
import frc.robot.subsystems.ElevatorSys;
import frc.robot.subsystems.HatchIntakeSys;
import frc.robot.subsystems.PivotSys;

public class Robot extends TimedRobot {
	private OI m_oi;
	private DriveTrainSys m_drive;
	private SendableChooser<ElevateCmd> chooser;
	private ElevateCmd elevatorCommand;
	private ElevatorSys m_elevator;
	private BallIntakeSys m_ballIntake;
	private HatchIntakeSys m_hatchIntake;
	private PivotSys m_pivot;

	@Override
	public void robotInit() {
		m_oi = new OI();
		m_ballIntake = new BallIntakeSys();
		m_hatchIntake = new HatchIntakeSys();
		m_drive = new DriveTrainSys();
		m_elevator = new ElevatorSys();
		m_pivot = new PivotSys();
		m_drive.setDefaultCommand(new DriveCmd(m_drive, m_oi));

		chooser = new SendableChooser<>();
		chooser.setDefaultOption("Default Height", new ElevateCmd(m_elevator, Height.DEFAULT));
		chooser.addOption("Middle Rocket Hatch", new ElevateCmd(m_elevator, Height.MIDDLE_HATCH));
		chooser.addOption("High Rocket Hatch", new ElevateCmd(m_elevator, Height.HIGH_HATCH));

		chooser.addOption("Low Rocket Ball Hole", new ElevateCmd(m_elevator, Height.LOW_BALL));
		chooser.addOption("Middle Rocket Ball Hole", new ElevateCmd(m_elevator, Height.MIDDLE_BALL));
		chooser.addOption("High Rocket Ball Hole", new ElevateCmd(m_elevator, Height.HIGH_BALL));
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledInit() {
		if (elevatorCommand != null) {
			elevatorCommand.cancel();
		}
		m_elevator.resetPid();
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
		ElevateCmd tempCommand = chooser.getSelected();
		if (!tempCommand.equals(elevatorCommand)) {
			elevatorCommand = tempCommand;
			elevatorCommand.start();
		}
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
