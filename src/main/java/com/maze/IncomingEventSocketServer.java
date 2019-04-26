package com.maze;

import com.maze.diagnostics.Logger;
import com.maze.events.*;
import com.maze.tools.BufferedReaderFrom;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class IncomingEventSocketServer implements Runnable {
	private final ServerSocket serverSocket;
	private final AtomicBoolean wasCancelled;
	private final IEventQueue eventQueue;

	IncomingEventSocketServer(ServerSocket serverSocket, IEventQueue eventQueue, AtomicBoolean wasCancelled) {
		this.serverSocket = serverSocket;
		this.wasCancelled = wasCancelled;
		this.eventQueue = eventQueue;
	}

	@Override
	public void run() {
		while (!wasCancelled.get()) {
			try {
				Socket socket = serverSocket.accept();
				process(socket);
			} catch (IOException ex) {
				Logger.logException("Caught exception while processing incoming events!", ex);
			}
		}
	}

	private void process(Socket socket) throws IOException {
		BufferedReader reader = BufferedReaderFrom.Socket(socket);
		while (!wasCancelled.get()) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			Event event = EventDeserializer.Deserialize(line);
			Logger.logReceivedEvent(event);
			eventQueue.enqueue(event);
		}
	}
}