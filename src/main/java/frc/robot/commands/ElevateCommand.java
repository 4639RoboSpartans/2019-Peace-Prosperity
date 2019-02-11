/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Height;
import frc.robot.subsystems.ElevatorSys;

public class ElevateCommand extends PIDCommand {
  private ElevatorSys m_elevator;
  private Height height;

  //placeholder values
  public static double[] heights = {0, 1, 2, //hatch heights
    0, 1, 2}; //ball heights

  private static int P = 0, I = 0, D = 0;

  public ElevateCommand(ElevatorSys es, Height height) {
    super(P, I, D, es);
    this.m_elevator = es;
    this.height = height;

    setSetpoint(heights[Height.getIndex(height)]);
  }

  @Override
  protected void usePIDOutput(double output) {
    m_elevator.move(output);
  }

  @Override
  protected double returnPIDInput() {
    return m_elevator.getEncoder().get();
  }
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean equals(ElevateCommand e) {
    return height == e.height;
  }
}
