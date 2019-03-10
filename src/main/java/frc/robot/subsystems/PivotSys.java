/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class PivotSys extends InjectedSubsystem {
	private static final int motorPort = 0;

	private WPI_VictorSPX motor;
	private AnalogPotentiometer pot;

	public PivotSys() {
		motor = new WPI_VictorSPX(motorPort);
		pot = new AnalogPotentiometer(1, 360, 30);
	}

	public void setMotor(double speed) {
		motor.set(speed);
	}

	public AnalogPotentiometer getPotentiometer() {
		return pot;
	}
}
