/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import frc.robot.util.enums.Height;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSys extends InjectedSubsystem {
	private static final int motorPort = 1;
	private static final double kG = 0.531, kFr = 1.14, kV = 0.26, kP = 3.5, kD = 250;

	private final WPI_TalonSRX motor;
	private Height curHeight;

	public ElevatorSys() {
		this.motor = new WPI_TalonSRX(motorPort);
		motor.configFactoryDefault();
		motor.setInverted(true);

		motor.setNeutralMode(NeutralMode.Brake);
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		motor.configClearPositionOnLimitR(true, 0);
		motor.setSensorPhase(false);

		motor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
		motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);

		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

		motor.configClearPositionOnLimitR(true, 0);

		motor.selectProfileSlot(0, 0);
		motor.config_kP(0, kP);
		motor.config_kD(0, kD);

		motor.configMotionAcceleration(4000);
		motor.configMotionCruiseVelocity(4000);
	}

	private double getFeedforward() {
		return kG + Math.signum(motor.getActiveTrajectoryVelocity()) * kFr
				+ motor.getActiveTrajectoryVelocity() * 10.0 / 4096.0 * 3.926 * Math.PI * kV;
	}

	public void move(Height height) {
		motor.set(ControlMode.MotionMagic, height.getHeight(), DemandType.ArbitraryFeedForward, getFeedforward());
		curHeight = height;
	}

	public void drop() {
		switch (curHeight) {
			case HIGH_HATCH :
			case MIDDLE_HATCH :
			case LOW_HATCH :
				motor.set(ControlMode.MotionMagic,
						curHeight.getHeight() - (Height.LOW_HATCH.getHeight() - Height.LOAD.getHeight()),
						DemandType.ArbitraryFeedForward, getFeedforward());
				break;
			default :
				break;
		}
	}

	public void undrop() {
		if (curHeight == Height.LOAD) {
			move(Height.LOW_HATCH);
		} else {
			move(curHeight);
		}
	}

	public void manual(double num) {
		motor.set(ControlMode.PercentOutput, num);
	}

	public ControlMode getControlMode() {
		return motor.getControlMode();
	}
}
