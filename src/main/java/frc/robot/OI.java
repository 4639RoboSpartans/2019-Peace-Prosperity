/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  private Joystick[] sticks;
  private JoystickButton[][] buttons;

  // port values of the axes/buttons
	private static int leftJoystickX = 0, leftJoystickY = 1, leftTrigger = 2, rightTrigger = 3, rightJoystickX = 4,
  rightJoystickY = 5;

  private static double deadzoneValue = 0.01;

  public OI() {
    buttons = new JoystickButton[6][8];
    for (int i = 0; i < 6; i++) {
      sticks[i] = new Joystick(i);
      for (int j = 0; j < 8; j++) {
        buttons[i][j] = new JoystickButton(sticks[i], j);
      }
    }
  }

  /**
	 * Creates a deadzone for joysticks, the controllers sticks are a little loose
	 * and so there is a margin of error for where they should be considered
	 * "neutral/not pushed"
	 *
	 * @param d Double between -1 and 1 to be deadzoned
	 * @return The deadzone value
	 */
	private static double deadzone(double value) {
		// whenever the controller moves LESS than the magic number, the
		// joystick is in the loose position so return zero - as if the
		// joystick was not moved
		if (Math.abs(value) < deadzoneValue) {
			return 0;
		}

		// When the joystick is greater than the margin of error, scale the value so
		// that the point right after the deadzone
		// is 0 so the robot does not jerk forward when it passes the deadzone, this is
		// genius
		// It properly scales the controls to the new workable zone
		return (value / Math.abs(value)) * ((Math.abs(value) - deadzoneValue) / (1 - deadzoneValue));
  }
  
  public double getLeftX(int joystick) {
    double valueLeftX = sticks[joystick].getRawAxis(leftJoystickX);
    return deadzone(valueLeftX);
  }

  public double getLeftY(int joystick) {
    double valueLeftY = sticks[joystick].getRawAxis(leftJoystickY);
    return deadzone(-valueLeftY);
  }

  public double getRightX(int joystick) {
    double valueRightX = sticks[joystick].getRawAxis(rightJoystickX);
    return deadzone(valueRightX);
  }

  public double getRightY(int joystick) {
    double valueRightY = sticks[joystick].getRawAxis(rightJoystickY);
    return deadzone(-valueRightY);
  }

  public double getLeftTrigger(int joystick) {
    double valueLeftTrigger = sticks[joystick].getRawAxis(leftTrigger);
    return deadzone(valueLeftTrigger);
  }

  public double getRightTrigger(int joystick) {
    double valueRightTrigger = sticks[joystick].getRawAxis(rightTrigger);
    return deadzone(valueRightTrigger);
  }

  public boolean getButton(int joystick, int button) {
    return buttons[joystick][button].get();
  }
}