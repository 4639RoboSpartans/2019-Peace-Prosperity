/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FRC Team 4639. All Rights Reserved.                     */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util.enums;

public enum Height {
	GROUND(0), LOAD(1478), LOW_HATCH(2266), MIDDLE_HATCH(17000), HIGH_HATCH(19000), LOW_BALL(8000), MIDDLE_BALL(
			8600), HIGH_BALL(20000);

	private final double height;

	private Height(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
}
