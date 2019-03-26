/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.motion.MotorStore;

public class PivotSys extends InjectedSubsystem {
	private static final int motorPort = 7;

	private static final double vStatic = 0.125, P = 0.125, I = 0.05, D = 0.02;

	private final WPI_TalonSRX motor;

	public PivotSys(MotorStore ms) {
		motor = new WPI_TalonSRX(motorPort);
		ms.addMotor(motorPort, motor);
		motor.configFactoryDefault();
	}

	public void set(double speed) {
		motor.set(speed);
	}
}
