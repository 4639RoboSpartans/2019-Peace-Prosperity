/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


/**
 * Add your docs here.
 */
public class BallIntakeSys extends InjectedSubsystem {
  private static WPI_VictorSPX intakeLeftMotor;
  private static WPI_VictorSPX intakeRightMotor;
  private static WPI_VictorSPX intakeTopMotor;

  
  // temporary port numbers
  public static final int intakeRight = 0;
  public static final int intakeLeft = 1;
  public static final int intakeTop = 2;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.



	public BallIntakeSys() {
		intakeLeftMotor = new WPI_VictorSPX(intakeLeft);
		intakeRightMotor = new WPI_VictorSPX(intakeRight);
		intakeTopMotor = new WPI_VictorSPX(intakeTop);
		// makes right and top motor opposite to left
		intakeRightMotor.setInverted(true);
		intakeTopMotor.setInverted(true);
	}

	 // Decides whether to intake or outtake
	public void ballIntake(double intakeSpeed, double outtakeSpeed) {
		if (intakeSpeed > outtakeSpeed) {
			intake(intakeSpeed);
		} else if (outtakeSpeed > intakeSpeed) {
			outtake(outtakeSpeed);
		}
	}

	public void intake(double intakeSpeed) {
		intakeLeftMotor.set(intakeSpeed);
		intakeRightMotor.set(intakeSpeed);
		intakeTopMotor.set(intakeSpeed);
	}

	public void outtake(double outtakeSpeed) {
		intakeLeftMotor.set(-outtakeSpeed);
		intakeRightMotor.set(-outtakeSpeed);
		intakeTopMotor.set(-outtakeSpeed);
	}

	public void stop() {
		intakeLeftMotor.set(0);
		intakeRightMotor.set(0);
		intakeTopMotor.set(0);
	}
}
