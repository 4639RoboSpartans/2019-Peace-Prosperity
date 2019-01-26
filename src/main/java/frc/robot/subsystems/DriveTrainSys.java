package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrainSys extends InjectedSubsystem {
    
    private static int frontLeftMotor = 1;
    private static int frontRightMotor = 2;
    private static int rearLeftMotor = 3;
    private static int rearRightMotor = 4;

    private static WPI_TalonSRX frontLeft;
    private static WPI_TalonSRX frontRight;
    private static WPI_TalonSRX rearLeft;
    private static WPI_TalonSRX rearRight;
    private static MecanumDrive drive;

    public DriveTrainSys() {
        frontLeft = new WPI_TalonSRX(frontLeftMotor);
        frontRight = new WPI_TalonSRX(frontRightMotor);
        rearLeft = new WPI_TalonSRX(rearLeftMotor);
        rearRight = new WPI_TalonSRX(rearRightMotor);

        frontLeft.setInverted(true);
        rearLeft.setInverted(true);

        drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void drive(double xSpeed, double ySpeed, double zRotation) {
        double gyroAngle = 0.0;
        drive.driveCartesian(xSpeed, ySpeed, zRotation, gyroAngle);
    }

    public void stop() {
        drive.stopMotor();
    }
}