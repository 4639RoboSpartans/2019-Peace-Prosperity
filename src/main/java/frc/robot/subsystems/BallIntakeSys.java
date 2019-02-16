/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
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
	private static final int intakeRight = 0;
	private static final int intakeLeft = 1;
	private static final int intakeTop = 2;
	private static final double topMotorFactor = 0.8;

	private final WPI_TalonSRX intakeLeftMotor;
	private final WPI_TalonSRX intakeRightMotor;
	private final WPI_TalonSRX intakeTopMotor;

	public boolean isLowered = true;
	
	public BallIntakeSys() {
		this.intakeLeftMotor = new WPI_TalonSRX(intakeLeft);
		this.intakeRightMotor = new WPI_TalonSRX(intakeRight);
		this.intakeTopMotor = new WPI_TalonSRX(intakeTop);
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
