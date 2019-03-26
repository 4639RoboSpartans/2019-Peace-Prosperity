/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.motion;

public class Odometry {
	// Make all distances in Feet
	public double currentPosX = 0, currentPosY = 0; // Set robot position based on starting configuration, chosen
													// through smart dashboardb
	private double newPosX = 0, newPosY = 0;
	private double intersectX = 0, intersectY = 0, distIntersect = 4 / 12;
	private double Etick = 4096, wheelDiam = 6;
	private double leftEncX = 0, leftEncY = 0, distLeft = 13.5 / 12; // Change all distances to feet
	private double rightEncX = 0, rightEncY = 0, distRight = 13.5 / 12;
	private double backEncX = 0, backEncY = 0, distBack = 9.5 / 12;

	public Odometry() {
		leftEncX = 0;
		leftEncY = 0;

		rightEncX = 0;
		rightEncY = 0;

		backEncX = 0;
		backEncY = 0;
	}

	// General Calculations
	public void calculate(double leftEnc, double rightEnc, double backEnc, double gyro) {
		double leftEncXChange = 0, leftEncYChange = 0, rightEncXChange = 0, rightEncYChange = 0, backEncXChange = 0,
				backEncYChange = 0;

		// Average the Left and Right Encoder tick values. Try changing this to average
		// distance later
		double avgLRTick = (leftEnc + rightEnc) / 2;

		// This is assuming that everything is in degrees, might have to change to
		// radians
		if (leftEnc > 0) {
			leftEncXChange = distanceTraveled(leftEnc) * Math.cos(gyro);
		} else if (leftEnc < 0) {
			leftEncYChange = distanceTraveled(leftEnc) * Math.sin(gyro + 180);
		}

		if (rightEnc > 0) {
			rightEncXChange = distanceTraveled(rightEnc) * Math.cos(gyro);
		} else if (leftEnc < 0) {
			rightEncYChange = distanceTraveled(rightEnc) * Math.sin(gyro + 180);
		}

		if (backEnc > 0) {
			backEncXChange = distanceTraveled(backEnc) * Math.cos(gyro - 90);
		} else if (leftEnc < 0) {
			backEncYChange = distanceTraveled(backEnc) * Math.sin(gyro + 90);
		}

		calcEncPos(leftEncXChange, leftEncYChange, rightEncXChange, rightEncYChange, backEncXChange, backEncYChange);
		calcRobotPos(gyro);
	}

	// Calculates the distance travled based on tick and wheel diameter
	public double distanceTraveled(double ticks) {
		double distance = 0;

		distance = (ticks * Math.PI * wheelDiam) / (12 * Etick);

		return distance;
	}

	public void calcRobotPos(double gyro) {
		// Robots position based on the left encoder
		double robotPosX_LeftEnc = leftEncX + (distLeft * Math.cos(gyro - 90));
		double robotPosY_LeftEnc = leftEncY + (distLeft * Math.sin(gyro - 90));

		// Robots position based on the right encoder
		double robotPosX_RightEnc = rightEncX + (distRight * Math.cos(gyro + 90));
		double robotPosY_RightEnc = rightEncY + (distRight * Math.sin(gyro + 90));

		// Robots position based on the back encoder
		double robotPosX_BackEnc = backEncX + (distBack * Math.cos(gyro));
		double robotPosY_BackEnc = backEncY + (distBack * Math.sin(gyro));

		intersectX = (robotPosX_LeftEnc + robotPosX_RightEnc + robotPosX_BackEnc) / 3; // Actually equal to intersectX
		intersectY = (robotPosY_LeftEnc + robotPosY_RightEnc + robotPosY_BackEnc) / 3; // Actually equal to intersectY

		newPosX = intersectX + (distIntersect * Math.cos(gyro));
		newPosY = intersectY + (distIntersect * Math.sin(gyro));
	}

	// Calculates the encoder position based on the change in position
	public void calcEncPos(double leftEncXChange, double leftEncYChange, double rightEncXChange, double rightEncYChange,
			double backEncXChange, double backEncYChange) {
		leftEncX += leftEncXChange;
		leftEncY += leftEncYChange;

		rightEncX += rightEncXChange;
		rightEncY += rightEncYChange;

		backEncX += backEncXChange;
		backEncY += (leftEncYChange + rightEncYChange) / 2;
	}

	public double getXPos() {
		return newPosX;
	}

	public double getYPos() {
		return newPosY;
	}

	public void setRobotPos(double xPos, double yPos, double gyro) {
		newPosX = xPos;
		newPosY = yPos;
		// Set position of encoders as well

		leftEncX = xPos + (distBack * Math.cos(gyro + 90));
		leftEncY = yPos + (distBack * Math.sin(gyro + 90));

		rightEncX = xPos + (distBack * Math.cos(gyro - 90));
		rightEncY = yPos + (distBack * Math.sin(gyro - 90));

		backEncX = xPos + (distBack * Math.cos(gyro + 180));
		backEncY = yPos + (distBack * Math.sin(gyro + 180));
	}
}
