/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.enums;

public enum Pivot {
	UP(0), DOWN(0.5);

	private final double amount;
	private Pivot(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
}
