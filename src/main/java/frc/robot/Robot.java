/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private DriveTrainSys m_drive;
  private OI m_oi;
  private SendableChooser<ElevateCommand> chooser;
  private ElevateCommand elevatorCommand;
  private ElevatorSys m_elevator;
  private AHRS navx;
  private BallIntakeSys m_ballIntake;
  private HatchIntakeSys m_hatchIntake;
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    navx = new AHRS(SPI.Port.kMXP);
    m_oi = new OI();
    m_ballIntake = new BallIntakeSys();
    m_hatchIntake = new HatchIntakeSys();
    m_drive = new DriveTrainSys(navx);
    m_elevator = new ElevatorSys();
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

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    elevatorCommand.cancel();
    m_elevator.resetPid();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //check to see if selected command is changed
    ElevateCommand tempCommand = chooser.getSelected();
    if (!tempCommand.equals(elevatorCommand)) {
      elevatorCommand = tempCommand;
      elevatorCommand.start();
      Scheduler.getInstance().run();
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
