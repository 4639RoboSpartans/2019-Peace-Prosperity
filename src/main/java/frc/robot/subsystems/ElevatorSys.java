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
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSys extends InjectedSubsystem {
	private static final int motorPort = 1;
	private static final double vStatic = 0, P = 0.55, I = 0.001, D = 0.025;

	private final WPI_TalonSRX motor;
	private Height curHeight;

	public ElevatorSys() {
		this.motor = new WPI_TalonSRX(motorPort);
		motor.configFactoryDefault();

		motor.setNeutralMode(NeutralMode.Brake);
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		motor.configClearPositionOnLimitR(true, 0);
		motor.setSensorPhase(true);

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

		motor.configMotionAcceleration(3000);
		motor.configMotionCruiseVelocity(4000);
	}

	public void move(Height height) {
		motor.set(ControlMode.MotionMagic, height.getHeight(), DemandType.ArbitraryFeedForward, vStatic);
		curHeight = height;
	}

	public void drop() {
		switch (curHeight) {
			case HIGH_HATCH :
			case MIDDLE_HATCH :
			case LOW_HATCH :
				motor.set(ControlMode.MotionMagic, curHeight.getHeight() - 939, DemandType.ArbitraryFeedForward,
						vStatic);
				break;
			default :
				break;
		}
	}

	public void manual(double num) {
		motor.set(ControlMode.PercentOutput, num);
	}

	public void a() {
		System.out.println(motor.getSelectedSensorPosition());
	}

	public ControlMode getControlMode() {
		return motor.getControlMode();
	}
}
