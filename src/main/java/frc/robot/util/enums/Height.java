/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util.enums;

public enum Height {
	GROUND(0), LOAD(551), LOW_HATCH(1065), MIDDLE_HATCH(5285), HIGH_HATCH(9686), LOW_BALL(4350), MIDDLE_BALL(
			8600), HIGH_BALL(11000);

	private final double height;

	private Height(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
}
