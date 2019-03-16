/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class PivotSys extends InjectedSubsystem {
	private static final int motorPort = 0;
	private static final int servoPort = 1;

	private WPI_VictorSPX motor;
	private Servo servo;
	// private AnalogPotentiometer pot;

	public PivotSys() {
		this.motor = new WPI_VictorSPX(motorPort);
		this.servo = new Servo(servoPort);
		// pot = new AnalogPotentiometer(1, 360, 30);
	}

	public void setMotor(double speed) {
		motor.set(speed);
	}

	// public AnalogPotentiometer getPotentiometer() {
	// return pot;
	// }
}
