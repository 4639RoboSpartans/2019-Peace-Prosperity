/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import frc.robot.enums.Pivot;

/**
 * Add your docs here.
 */
public class BallIntakeSys extends InjectedSubsystem {
	private static final int intakeRight = 0;
	private static final int intakeLeft = 1;
	private static final int intakeTop = 2;
	private static final int intakePivot = 3;
	private static final double topMotorFactor = 0.8;
	private static final double P = 0, I = 0, D = 0;

	private static final int loweredSetpoint = 0;
	private static final int raisedSetpoint = 256;

	private final WPI_TalonSRX intakeLeftMotor;
	private final WPI_TalonSRX intakeRightMotor;
	private final WPI_TalonSRX intakeTopMotor;
	private final WPI_TalonSRX intakePivotMotor;
	private final Encoder encoder;
	private final PIDController pid;

	public boolean isLowered = true;
	
	public BallIntakeSys() {
		this.intakeLeftMotor = new WPI_TalonSRX(intakeLeft);
		this.intakeRightMotor = new WPI_TalonSRX(intakeRight);
		this.intakeTopMotor = new WPI_TalonSRX(intakeTop);
		this.intakePivotMotor = new WPI_TalonSRX(intakePivot);
		this.intakeRightMotor.setInverted(true);
		this.intakeTopMotor.setInverted(true);

		this.encoder = new Encoder(0, 1, false, EncodingType.k4X);
		this.pid = new PIDController(P, I, D, encoder, intakePivotMotor);
		this.pid.setOutputRange(-1, 1);
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

	public void pivot(Pivot pivot) {
		pid.setSetpoint(pivot.getAmount());
	}
}
