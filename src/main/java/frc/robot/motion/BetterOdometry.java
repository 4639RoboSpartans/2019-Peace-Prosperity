/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.motion;

public class BetterOdometry {
	private static final double disCenterToBack = 5, disCenterToSide = 6;
	private Pose current = new Pose(0, 0, 0);
	private double leftEnc, rightEnc, backEnc, gyro;

	public void calcEncoders(double nLeftEnc, double nRightEnc, double nBackEnc, double nGyro) {
		double forwardEnc = (nLeftEnc - leftEnc + nRightEnc - rightEnc) / 2;
		forwardEnc -= disCenterToSide * 2 * Math.PI / (nGyro - gyro);

		double xChange = forwardEnc * Math.cos(Math.toRadians(nGyro));
		double yChange = forwardEnc * Math.sin(Math.toRadians(nGyro));

		double backChange = nBackEnc - backEnc - (disCenterToBack * 2 * Math.PI / (nGyro - gyro));
		xChange += backChange * Math.sin(Math.toRadians(nGyro));
		yChange += backChange * Math.cos(Math.toRadians(nGyro));

		current = current.translate(xChange, yChange).withAngle(nGyro);
	}
}
