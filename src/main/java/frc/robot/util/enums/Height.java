/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util.enums;

public enum Height {
	GROUND(0), LOAD(640), LOW_HATCH(1466), MIDDLE_HATCH(10085), HIGH_HATCH(18383), LOW_BALL(6323), MIDDLE_BALL(15004), HIGH_BALL(
			18000);

	private final double height;

	private Height(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
}
