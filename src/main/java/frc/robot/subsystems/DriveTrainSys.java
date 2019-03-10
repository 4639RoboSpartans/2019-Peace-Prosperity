/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrainSys extends InjectedSubsystem {
	private static final int frontLeftPort = 9;
	private static final int frontRightPort = 2;
	private static final int rearLeftPort = 8;
	private static final int rearRightPort = 1;

	private final WPI_TalonSRX frontLeft;
	private final WPI_TalonSRX frontRight;
	private final WPI_TalonSRX rearLeft;
	private final WPI_TalonSRX rearRight;
	private final MecanumDrive drive;
	private final AHRS navx;

	public DriveTrainSys() {
		frontLeft = new WPI_TalonSRX(frontLeftPort);
		frontRight = new WPI_TalonSRX(frontRightPort);
		rearLeft = new WPI_TalonSRX(rearLeftPort);
		rearRight = new WPI_TalonSRX(rearRightPort);
		navx = new AHRS(Port.kMXP);

		frontLeft.setInverted(InvertType.InvertMotorOutput);
		rearLeft.setInverted(InvertType.InvertMotorOutput);
		frontRight.setInverted(InvertType.InvertMotorOutput);
		rearRight.setInverted(InvertType.InvertMotorOutput);

		drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
	}

	public void driveField(double xSpeed, double ySpeed, double zRotation) {
		drive.driveCartesian(xSpeed, ySpeed, zRotation, navx.getYaw());
	}

	public void drive(double xSpeed, double ySpeed, double zRotation) {
		drive.driveCartesian(xSpeed, ySpeed, zRotation);
	}

	public void stop() {
		drive.stopMotor();
	}
}
