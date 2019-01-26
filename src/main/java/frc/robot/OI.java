/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick main;
  private static double deadzoneValue = 0.01;

  //field oriented toggle
  public static boolean fieldOriented = false;

  public OI() {
    int controllerPort = 0;
    main = new Joystick(controllerPort);
    main.setXChannel(1);
    main.setYChannel(0);
    main.setZChannel(4);
  }

  /**
   * Creates a deadzone for joysticks, the controller sticks are a little
   * loose and so there is a margin of error for where they should be
   * considered "neutral/not pushed"
   * 
   * @param value Double between -1 and 1 to be deadzoned
   * @return The deadzone value
   */
  private static double deadzone(double value) {
    //whenever the controller moves LESS than the magic number, the 
    //joystick is in the loose position so return zero - as if the 
    //joystick was not moved
    if (Math.abs(value) < deadzoneValue) {
      return 0;
    }

    //When the joystick is greater than the margin of error, scale the value so that the point right after the deadzone 
    //is 0 so the robot does not jerk forward when it passes the deadzone, this is genius
    //It properly scales the controls to the new workable zone
    return (value / Math.abs(value)) * ((Math.abs(value) - deadzoneValue) / (1 - deadzoneValue));
  }

  public double getControllerX() {
    double valueX = main.getX();
    return deadzone(-valueX);
  }

  public double getControllerY() {
    double valueY = main.getY();
    return deadzone(valueY);
  }

  public double getControllerZ() {
    double valueZ = main.getZ();
    return deadzone(valueZ);
  }
}
