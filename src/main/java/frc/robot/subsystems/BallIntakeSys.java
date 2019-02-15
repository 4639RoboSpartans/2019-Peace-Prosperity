/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * Add your docs here.
 */
public class BallIntakeSys extends InjectedSubsystem {
	private static WPI_TalonSRX intakeLeftMotor;
	private static WPI_TalonSRX intakeRightMotor;
	private static WPI_TalonSRX intakeTopMotor;
	private static WPI_TalonSRX intakePivotMotor;
	
	private static Encoder encoder;

  	// temporary port numbers
	private static final int intakeRight = 0;
	private static final int intakeLeft = 1;
	private static final int intakeTop = 2;
	private static final int intakePivot = 3;
	private static final double topMotorFactor = 0.8;

	private static final int loweredSetpoint = 0;
	private static final int raisedSetpoint = 256;
	public boolean isLowered = true;
	private static final int P = 0, I = 0, D = 0;
	private int integral, previous_error = 0;
	private double rcw = 0;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public BallIntakeSys() {
		intakeLeftMotor = new WPI_TalonSRX(intakeLeft);
		intakeRightMotor = new WPI_TalonSRX(intakeRight);
		intakeTopMotor = new WPI_TalonSRX(intakeTop);
		intakePivotMotor = new WPI_TalonSRX(intakePivot);
		// makes right and top motor opposite to left
		intakeRightMotor.setInverted(true);
		intakeTopMotor.setInverted(true);

		encoder = new Encoder(0, 1, false, EncodingType.k4X);
	}

	 // Decides whether to intake or outtake
	public void ballIntake(double intakeSpeed, double outtakeSpeed) {
		if (intakeSpeed > outtakeSpeed) {
			intake(intakeSpeed);
		} else if (outtakeSpeed > intakeSpeed) {
			outtake(outtakeSpeed);
		}
	}

	public void intake(double intakeSpeed) {
		intakeLeftMotor.set(intakeSpeed);
		intakeRightMotor.set(intakeSpeed);
		intakeTopMotor.set(intakeSpeed * topMotorFactor);
	}

	public void outtake(double outtakeSpeed) {
		intakeLeftMotor.set(-outtakeSpeed);
		intakeRightMotor.set(-outtakeSpeed);
		intakeTopMotor.set(-outtakeSpeed * topMotorFactor);
	}

	public void stop() {
		intakeLeftMotor.set(0);
		intakeRightMotor.set(0);
		intakeTopMotor.set(0);
	}

	public void PID(int setpoint) {
		double error = setpoint - encoder.get();
		this.integral += (error * .02);
		double derivative = (error - this.previous_error) / .02;
		this.rcw = P*error + I*this.integral + D*derivative;
	} 
	
	public void pivotIntake() {
		PID(isLowered ? raisedSetpoint : loweredSetpoint);
		intakePivotMotor.set(rcw);
	}
}
