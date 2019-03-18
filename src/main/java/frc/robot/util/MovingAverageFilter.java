/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.util;

public class MovingAverageFilter {
	private final double[] nums;
	private int size, index;
	private double sum;

	public MovingAverageFilter(int size) {
		nums = new double[size];
	}

	public double next(double val) {
		if (size < nums.length) {
			size++;
		} else {
			sum -= nums[index];
		}
		sum += val;
		nums[index++] = val;
		if (index == nums.length) {
			index = 0;
		}

		return get();
	}

	public double get() {
		return sum / size;
	}
}
