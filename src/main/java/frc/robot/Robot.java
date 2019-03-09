/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevateCommand;
import frc.robot.enums.Height;
import frc.robot.subsystems.BallIntakeSys;
import frc.robot.subsystems.DriveTrainSys;
import frc.robot.subsystems.ElevatorSys;
import frc.robot.subsystems.HatchIntakeSys;
import frc.robot.subsystems.PivotSys;

import com.kauailabs.navx.frc.AHRS;

public class Robot extends TimedRobot {
	private DriveTrainSys m_drive;
	private OI m_oi;
	private SendableChooser<ElevateCommand> chooser;
	private ElevateCommand elevatorCommand;
	private ElevatorSys m_elevator;
	private AHRS navx;
	private BallIntakeSys m_ballIntake;
	private HatchIntakeSys m_hatchIntake;
	private PivotSys m_pivot;

	@Override
	public void robotInit() {
		navx = new AHRS(SPI.Port.kMXP);
		m_oi = new OI();
		m_ballIntake = new BallIntakeSys();
		m_hatchIntake = new HatchIntakeSys();
		m_drive = new DriveTrainSys(navx);
		m_elevator = new ElevatorSys();
		m_pivot = new PivotSys();
		m_drive.setDefaultCommand(new DriveCommand(m_drive, m_oi, navx));
		chooser = new SendableChooser<>();

		chooser.setDefaultOption("Default Height", new ElevateCommand(m_elevator, Height.DEFAULT));
		chooser.addOption("Middle Rocket Hatch", new ElevateCommand(m_elevator, Height.MIDDLE_HATCH));
		chooser.addOption("High Rocket Hatch", new ElevateCommand(m_elevator, Height.HIGH_HATCH));

		chooser.addOption("Low Rocket Ball Hole", new ElevateCommand(m_elevator, Height.LOW_BALL));
		chooser.addOption("Middle Rocket Ball Hole", new ElevateCommand(m_elevator, Height.MIDDLE_BALL));
		chooser.addOption("High Rocket Ball Hole", new ElevateCommand(m_elevator, Height.HIGH_BALL));

		elevatorCommand = chooser.getSelected();
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledInit() {
		elevatorCommand.cancel();
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
		ElevateCommand tempCommand = chooser.getSelected();
		if (!tempCommand.equals(elevatorCommand)) {
			elevatorCommand = tempCommand;
			elevatorCommand.start();
			Scheduler.getInstance().run();
		}
	}

	@Override
	public void testPeriodic() {
	}
}
