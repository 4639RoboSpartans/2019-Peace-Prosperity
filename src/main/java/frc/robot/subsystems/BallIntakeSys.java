/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class BallIntakeSys extends InjectedSubsystem {
	private static final int leftPort = 13;
	private static final int rightPort = 8;
	private static final int topPort = 11;
	private static final double topMotorFactor = 0.8;

	private final WPI_VictorSPX leftMotor;
	private final WPI_VictorSPX rightMotor;
	private final WPI_VictorSPX topMotor;

	public BallIntakeSys() {
		leftMotor = new WPI_VictorSPX(leftPort);
		rightMotor = new WPI_VictorSPX(rightPort);
		topMotor = new WPI_VictorSPX(topPort);

		leftMotor.configFactoryDefault();
		rightMotor.configFactoryDefault();
		topMotor.configFactoryDefault();

		leftMotor.setInverted(true);
		rightMotor.setInverted(true);
		topMotor.setInverted(true);
	}

	public void intake(double intakeSpeed) {
		leftMotor.set(intakeSpeed);
		rightMotor.set(intakeSpeed);
		topMotor.set(intakeSpeed * topMotorFactor);
	}

	public void stop() {
		leftMotor.stopMotor();
		rightMotor.stopMotor();
		topMotor.stopMotor();
	}
}
