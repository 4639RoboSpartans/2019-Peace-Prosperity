/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util.enums;

public enum Hatch {
	UP(0.1), SIDE(0.45);

	private final double amount;
	private Hatch(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
}