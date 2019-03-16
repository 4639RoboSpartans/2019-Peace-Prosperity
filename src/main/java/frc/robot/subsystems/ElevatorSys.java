/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import frc.robot.enums.Height;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorSys extends InjectedSubsystem {
	private static final int motorPort = 1;
	private static final double vStatic = 112, P = 0.2, I = 0.01, D = 0;

	private final WPI_TalonSRX motor;

	public ElevatorSys() {
		this.motor = new WPI_TalonSRX(motorPort);
		motor.configFactoryDefault();

		motor.setNeutralMode(NeutralMode.Brake);
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		motor.setSensorPhase(true);

		motor.configPeakOutputForward(0.8);
		motor.configPeakOutputReverse(-0.8);
		motor.configNominalOutputForward(0);
		motor.configNominalOutputReverse(0);

		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

		motor.selectProfileSlot(0, 0);
		motor.config_kP(0, P);
		motor.config_kI(0, I);
		motor.config_kD(0, D);

		// motor.configMotionAcceleration(sensorUnitsPer100msPerSec);
		// motor.configMotionCruiseVelocity(sensorUnitsPer100ms);
	}

	public void move(Height height) {
		motor.set(ControlMode.MotionMagic, height.getHeight(), DemandType.ArbitraryFeedForward, vStatic);
	}
	
	public void manual(double num) {
		motor.set(ControlMode.PercentOutput, num);
	}
}