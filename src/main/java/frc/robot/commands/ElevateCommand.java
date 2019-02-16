/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.Height;
import frc.robot.subsystems.ElevatorSys;

public class ElevateCommand extends InstantCommand {
  private final ElevatorSys m_elevator;
  private final Height height;

  public ElevateCommand(ElevatorSys es, Height height) {
    this.m_elevator = es;
    this.height = height;
    requires(m_elevator);
  }

  @Override
  protected void initialize() {
    m_elevator.move(height);
  }

  public boolean equals(ElevateCommand e) {
    return height == e.height;
  }
}
