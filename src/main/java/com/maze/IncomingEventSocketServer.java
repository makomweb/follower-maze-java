package com.maze;

import com.maze.diagnostics.Logger;
import com.maze.events.*;
import com.maze.tools.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IncomingEventSocketServer extends CancellationRunnable {
	private final ServerSocket serverSocket;
	private final IEventQueue eventQueue;

	IncomingEventSocketServer(ServerSocket serverSocket, IEventQueue eventQueue) {
		this.serverSocket = serverSocket;
		this.eventQueue = eventQueue;
	}

	@Override
	public void run() {
		Logger.logStartProcessingIncomingEvents();
		while (!cancellationRequested) {
			try {
				Socket socket = serverSocket.accept();
				process(socket);
			} catch (IOException ex) {
				Logger.logExceptionProcessingIncomingEvents(ex);
			}
		}
		Logger.logProcessingIncomingEventsFinished();
	}

	private void process(Socket socket) throws IOException {
		BufferedReader reader = BufferedReaderFrom.Socket(socket);
		while (!cancellationRequested) {
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