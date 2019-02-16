package frc.robot.enums;

public enum Hatch {
    UP(0), DOWN(0.5);

    private final double amount;
    private Hatch(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}