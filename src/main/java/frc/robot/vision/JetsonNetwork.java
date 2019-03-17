/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.vision;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import frc.robot.util.MovingAverageFilter;

public class JetsonNetwork implements AutoCloseable, Runnable {

	private final ZContext zcontext;
	private final ZMQ.Socket socket;
	private final Gson gson;
	private final MovingAverageFilter filter;

	private volatile VisionData data;
	private long timeDiff;

	public JetsonNetwork(String zmqIP) {
		gson = new GsonBuilder().registerTypeAdapter(VisionData.class, new VisionData.Derserializer()).create();
		filter = new MovingAverageFilter(5);
		zcontext = new ZContext();
		socket = zcontext.createSocket(SocketType.REQ);
		socket.connect(zmqIP);
	}

	public VisionData getData() {
		return data;
	}

	public long getTimeDiff() {
		return timeDiff;
	}

	@Override
	public void close() throws Exception {
		zcontext.close();
	}

	@Override
	public void run() {
		if (timeDiff == 0) {
			long start = System.nanoTime();
			socket.send("sync");
			long jetson = Long.parseLong(socket.recvStr());
			long end = System.nanoTime();

			long networkTime = Math.abs((end - start) / 2);

			timeDiff = Math.abs(jetson - (start + networkTime));
		}
		socket.send("data");
		String json = socket.recvStr();
		VisionData temp = gson.fromJson(json, VisionData.class);
		double angle = filter.next(temp.getAngle());
		data = temp.withAngle(angle);
	}
}
