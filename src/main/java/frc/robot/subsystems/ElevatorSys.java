/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import frc.robot.util.enums.Height;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSys extends InjectedSubsystem {
	private static final int motorPort = 1;
	private static final double vStatic = 112, P = 0.125, I = 0.05, D = 0.02;

	private final WPI_TalonSRX motor;

	public ElevatorSys() {
		this.motor = new WPI_TalonSRX(motorPort);
		motor.configFactoryDefault();

		motor.setNeutralMode(NeutralMode.Brake);
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		motor.configClearPositionOnLimitR(true, 0);
		motor.setSensorPhase(false);
		motor.setInverted(InvertType.InvertMotorOutput);

		motor.configPeakOutputForward(1);
		motor.configPeakOutputReverse(-1);
		motor.configNominalOutputForward(0);
		motor.configNominalOutputReverse(0);

		motor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
		motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);

		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

		motor.selectProfileSlot(0, 0);
		motor.config_kP(0, P);
		motor.config_kI(0, I);
		motor.config_kD(0, D);

		motor.configMotionAcceleration(1500);
		motor.configMotionCruiseVelocity(3000);
	}

	public void move(Height height) {
		motor.set(ControlMode.MotionMagic, height.getHeight(), DemandType.ArbitraryFeedForward, vStatic);
	}

	public void manual(double num) {
		motor.set(ControlMode.PercentOutput, num);
	}

	public void a() {
		System.out.println(motor.getSelectedSensorPosition(0));
		System.out.println(motor.getSelectedSensorVelocity(0));
	}
}
