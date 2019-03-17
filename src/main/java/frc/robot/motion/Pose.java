/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.motion;

public class Pose {
	private final double x, y;
	private final double angle;

	public Pose(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	/**
	 * @return left-right (along driver station) distance in centimeters
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return forward-backward (from one driver station to another) distance in
	 *         centimeters
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return currnet angle in degrees: 0 is forward, -90 is left, 90 is right
	 */
	public double getAngle() {
		return angle;
	}

	public Pose translate(double x, double y, double angle) {
		return new Pose(this.x + x, this.y + y, this.angle + angle);
	}

	public Pose translate(double x, double y) {
		return translate(x, y, 0);
	}

	public Pose withAngle(double angle) {
		return new Pose(this.x, this.y, angle);
	}
}
