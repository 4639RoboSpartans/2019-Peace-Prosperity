/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.vision;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class VisionData {
	private final double angle;
	private final double distance;
	private final long time;

	public VisionData(double angle, double distance, long time) {
		this.angle = angle;
		this.distance = distance;
		this.time = time;
	}

	public double getAngle() {
		return angle;
	}

	public double getDistance() {
		return distance;
	}

	public long getTime() {
		return time;
	}

	static class Derserializer implements JsonDeserializer<VisionData> {
		@Override
		public VisionData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject obj = json.getAsJsonObject();

			return new VisionData(obj.get("angle").getAsDouble(), obj.get("distance").getAsDouble(),
					obj.get("timestamp").getAsLong());
		}

	}
}
