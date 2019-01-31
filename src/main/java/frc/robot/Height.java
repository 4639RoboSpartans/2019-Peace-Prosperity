package frc.robot;

public enum Height {
    DEFAULT, MIDDLE_HATCH, HIGH_HATCH, LOW_BALL, MIDDLE_BALL, HIGH_BALL;

    private static Height[] list = Height.values();

    public static Height getHeight(int i) {
        return list[i];
    }

    public static int getIndex(Height h) {
        for (int i = 0; i < list.length; i++)
            if (h == list[i])
                return i;
        throw new IllegalArgumentException("invalid height value");
    }
}