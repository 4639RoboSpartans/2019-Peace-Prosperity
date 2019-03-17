package frc.robot.util;


public class MovingAverageFilter {
    private final double[] nums;
    private int size, index;
    private double sum;

    public MovingAverageFilter(int size) {
        nums = new double[size];
    }

    public double next(double val) {
        if(size < nums.length) {
            size++;
        } else {
            sum -= nums[index];
        }
        sum += val;
        nums[index++] = val;
        if(index == nums.length) {
            index = 0;
        }

        return get();
    }

    public double get() {
        return sum / size;
    }
}