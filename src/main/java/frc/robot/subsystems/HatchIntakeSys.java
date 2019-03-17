/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

import frc.robot.util.enums.Hatch;

public class HatchIntakeSys extends InjectedSubsystem {
	private static final int servoPort = 0;

	private final Servo servo;

	public HatchIntakeSys() {
		servo = new Servo(servoPort);
	}

	public void setServo(Hatch hatch) {
		servo.set(hatch.getAmount());
	}
}
