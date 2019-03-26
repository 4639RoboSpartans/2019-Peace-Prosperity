/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.motion;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class MotorStore {
	private final Map<Integer, WPI_TalonSRX> motors;

	public MotorStore() {
		motors = new HashMap<>();
	}

	public void addMotor(int id, WPI_TalonSRX motor) {
		motors.put(id, motor);
	}

	public WPI_TalonSRX getMotor(int id) {
		return motors.get(id);
	}
}
