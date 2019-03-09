/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrainSys extends InjectedSubsystem {

	private static final int frontLeftMotor = 1;
	private static final int frontRightMotor = 2;
	private static final int rearLeftMotor = 3;
	private static final int rearRightMotor = 4;

	private final WPI_TalonSRX frontLeft;
	private final WPI_TalonSRX frontRight;
	private final WPI_TalonSRX rearLeft;
	private final WPI_TalonSRX rearRight;
	private final MecanumDrive drive;

	public DriveTrainSys(AHRS navx) {
		this.frontLeft = new WPI_TalonSRX(frontLeftMotor);
		this.frontRight = new WPI_TalonSRX(frontRightMotor);
		this.rearLeft = new WPI_TalonSRX(rearLeftMotor);
		this.rearRight = new WPI_TalonSRX(rearRightMotor);

		this.frontLeft.setInverted(true);
		this.rearLeft.setInverted(true);

		this.drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
	}

	public void drive(double xSpeed, double ySpeed, double zRotation, double gyroAngle) {
		drive.driveCartesian(xSpeed, ySpeed, zRotation, gyroAngle);
	}

	public void drive(double xSpeed, double ySpeed, double zRotation) {
		drive.driveCartesian(xSpeed, ySpeed, zRotation);
	}

	public void stop() {
		drive.stopMotor();
	}
}
