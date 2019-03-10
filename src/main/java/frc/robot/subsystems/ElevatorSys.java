/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

import frc.robot.enums.Height;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//pitch diam 1.432 inches
//use that diameter to find the arc length
//18 tooth from vex
public class ElevatorSys extends InjectedSubsystem {
	private static final int motorPort = 0;
	private static final double P = 0, I = 0, D = 0;

	private final WPI_TalonSRX motor;
	private final PIDController pid;

	public ElevatorSys() {
		this.motor = new WPI_TalonSRX(motorPort);
		this.pid = new PIDController(P, I, D, new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}
			@Override
			public double pidGet() {
				return motor.getSensorCollection().getQuadraturePosition();
			}
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kRate;
			}
		}, motor);
		this.pid.setOutputRange(-1, 1);
		this.pid.enable();
	}

	public void move(Height height) {
		pid.setSetpoint(height.getHeight());
	}

	public void move(double speed) {
		motor.set(speed);
	}

	public void resetPid() {
		pid.reset();
	}
}
