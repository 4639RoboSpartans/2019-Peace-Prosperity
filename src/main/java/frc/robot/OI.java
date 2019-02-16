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
  private static int leftTrigger = 2;
  private static int rightTrigger = 3;

  private static final int leftButton = 5;
  private static final int rightButton = 6;


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
    if (Math.abs(value) < deadzoneValue) {
      return 0;
    }
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

  public double getLeftTrigger() {
    return deadzone(main.getRawAxis(leftTrigger)); 
  }

  public double getRightTrigger() {
    return deadzone(main.getRawAxis(rightTrigger));
  }

  // public boolean leftButtonPressed() {
  //   if(main.getRawButtonPressed(leftButton)) {
  //     leftButtonPressed = true;
  //   } else if(main.getRawButtonReleased(leftButton)) {
  //     leftButtonPressed = false;
  //   }
  //   return leftButtonPressed;
  // }
  
  // public boolean rightButtonPressed() {
  //   if(main.getRawButtonPressed(rightButton)) {
  //     rightButtonPressed = true;
  //   } else if(main.getRawButtonReleased(leftButton)) {
  //     rightButtonPressed = false;
  //   }
  //   return rightButtonPressed;
  // }
}
