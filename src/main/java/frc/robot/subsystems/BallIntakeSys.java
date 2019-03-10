/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class BallIntakeSys extends InjectedSubsystem {
	private static final int leftPort = 6;
	private static final int rightPort = 4;
	private static final int topPort = 3;
	private static final double topMotorFactor = 0.8;

	private final WPI_TalonSRX intakeLeftMotor;
	private final WPI_TalonSRX intakeRightMotor;
	private final WPI_TalonSRX intakeTopMotor;

	public BallIntakeSys() {
		this.intakeLeftMotor = new WPI_TalonSRX(leftPort);
		this.intakeRightMotor = new WPI_TalonSRX(rightPort);
		this.intakeTopMotor = new WPI_TalonSRX(topPort);
		this.intakeRightMotor.setInverted(true);
		this.intakeTopMotor.setInverted(true);
	}

	public void intake(double intakeSpeed) {
		intakeLeftMotor.set(intakeSpeed);
		intakeRightMotor.set(intakeSpeed);
		intakeTopMotor.set(intakeSpeed * topMotorFactor);
	}

	public void stopIntake() {
		intakeLeftMotor.stopMotor();
		intakeRightMotor.stopMotor();
		intakeTopMotor.stopMotor();
	}
}
