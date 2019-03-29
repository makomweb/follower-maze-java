package com.maze;

import com.maze.Event;
import com.maze.EventDeserializer;

import java.io.*;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class IncomingEventProcessor implements Runnable {
	private final Socket socket;
	private final Queue<Event> eventQueue;
	private final AtomicBoolean wasCancelled;

	public IncomingEventProcessor(Socket socket, Queue<Event> eventQueue, AtomicBoolean wasCancelled) {
		this.socket = socket;
		this.eventQueue = eventQueue;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		try {
			process();
		} catch (IOException ex) {
			Logger.LogException("Caught exception while processing incoming events!", ex);
		}
	}

	public void process() throws IOException {
		InputStream stream = socket.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(streamReader);

		while (!wasCancelled.get()) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			Event event = EventDeserializer.Deserialize(line);
			// Logger.LogReceivedEvent(event);
			eventQueue.add(event);
		}
	}
}
