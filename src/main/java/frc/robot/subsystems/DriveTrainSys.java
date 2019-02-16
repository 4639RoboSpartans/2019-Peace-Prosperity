package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrainSys extends InjectedSubsystem {
    
    private static int frontLeftMotor = 1;
    private static int frontRightMotor = 2;
    private static int rearLeftMotor = 3;
    private static int rearRightMotor = 4;

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