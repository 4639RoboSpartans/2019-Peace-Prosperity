package frc.robot.enums;

public enum Pivot {
    UP(0), DOWN(0.5);

    private final double amount;
    private Pivot(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}