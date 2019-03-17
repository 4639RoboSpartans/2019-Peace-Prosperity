/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util.enums;

public enum Height {
	DEFAULT(8192), MIDDLE_HATCH(1), HIGH_HATCH(2), LOW_BALL(0), MIDDLE_BALL(1), HIGH_BALL(2);

	private final double height;

	private Height(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
}
