/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util.enums;

public enum Height {
	LOW_HATCH(1250), MIDDLE_HATCH(10085), HIGH_HATCH(18383), LOW_BALL(6323), MIDDLE_BALL(15004), HIGH_BALL(21700);

	private final double height;

	private Height(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
}
