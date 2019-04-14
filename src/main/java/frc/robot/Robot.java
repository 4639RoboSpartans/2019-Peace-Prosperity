/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import java.io.PrintWriter;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.BallIntakeCmd;
import frc.robot.commands.DriveCmd;
import frc.robot.commands.ElevateCmd;
import frc.robot.commands.HatchIntakeCmd;
import frc.robot.commands.ManualElevateCmd;
import frc.robot.commands.ManualPivotCmd;
import frc.robot.motion.MotorStore;
import frc.robot.motion.Odometry;
import frc.robot.subsystems.BallIntakeSys;
import frc.robot.subsystems.DriveTrainSys;
import frc.robot.subsystems.ElevatorSys;
import frc.robot.subsystems.HatchIntakeSys;
import frc.robot.subsystems.PivotSys;
import frc.robot.util.enums.Hatch;
import frc.robot.util.enums.Height;

public class Robot extends TimedRobot {
	private MotorStore ms;
	private OI m_oi;
	private DriveTrainSys m_drive;
	private ElevateCmd elevatorCommand;
	private ElevatorSys m_elevator;
	private BallIntakeSys m_ballIntake;
	private HatchIntakeSys m_hatchIntake;
	private PivotSys m_pivot;

	private PrintWriter pw;
	private Odometry od;

	@Override
	public void robotInit() {
		ms = new MotorStore();
		m_ballIntake = new BallIntakeSys();
		m_hatchIntake = new HatchIntakeSys();
		m_drive = new DriveTrainSys(ms);
		m_elevator = new ElevatorSys();
		m_pivot = new PivotSys(ms);
		m_oi = new OI();
		m_drive.setDefaultCommand(new DriveCmd(m_drive, m_oi));
		m_ballIntake.setDefaultCommand(new BallIntakeCmd(m_ballIntake, m_oi));
		m_elevator.setDefaultCommand(new ManualElevateCmd(m_elevator, m_oi));
		m_pivot.setDefaultCommand(new ManualPivotCmd(m_pivot, m_oi));

		m_oi.getButton(1, 1).whenPressed(new HatchIntakeCmd(m_hatchIntake, Hatch.SIDE));
		m_oi.getButton(1, 4).whenPressed(new HatchIntakeCmd(m_hatchIntake, Hatch.UP));

		m_oi.getButton(1, 3).whenPressed(new ElevateCmd(m_elevator, Height.LOAD));
		m_oi.getButton(1, 2).whenPressed(new ElevateCmd(m_elevator, Height.LOW_HATCH));

		for (Height h : Height.values()) {
			SmartDashboard.putData(h.name(), new ElevateCmd(m_elevator, h));
		}
		CameraServer.getInstance().startAutomaticCapture();

		od = new Odometry();
	}

	@Override
	public void robotPeriodic() {
		m_elevator.a();
	}

	@Override
	public void disabledInit() {
		if (elevatorCommand != null) {
			elevatorCommand.cancel();
		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void autonomousInit() {
		m_hatchIntake.setServo(Hatch.UP);
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		m_hatchIntake.setServo(Hatch.UP);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
