package frc.robot.enums;

public enum Height {
  DEFAULT(0), MIDDLE_HATCH(1), HIGH_HATCH(2), LOW_BALL(0), MIDDLE_BALL(1), HIGH_BALL(2);

  private final double height;

  private Height(double height) {
    this.height = height;
  }

  public double getHeight() {
    return height;
  }
}