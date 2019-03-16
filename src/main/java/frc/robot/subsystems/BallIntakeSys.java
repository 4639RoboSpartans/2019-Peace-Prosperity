/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class BallIntakeSys extends InjectedSubsystem {
	private static final int leftPort = 12;
	private static final int rightPort = 8;
	private static final int topPort = 11;
	private static final double topMotorFactor = 0.8;

	private final WPI_VictorSPX intakeLeftMotor;
	private final WPI_VictorSPX intakeRightMotor;
	private final WPI_VictorSPX intakeTopMotor;

	public BallIntakeSys() {
		this.intakeLeftMotor = new WPI_VictorSPX(leftPort);
		this.intakeRightMotor = new WPI_VictorSPX(rightPort);
		this.intakeTopMotor = new WPI_VictorSPX(topPort);
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
