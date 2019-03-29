package com.maze;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class IncomingEventSocketServer implements Runnable {

	private final ServerSocket serverSocket;
	private final ExecutorService threadPool;
	private AtomicBoolean wasCancelled;
	private Queue<Event> incomingEvents;

	IncomingEventSocketServer(ServerSocket serverSocket, ExecutorService threadPool, Queue<Event> incomingEvents, AtomicBoolean wasCancelled) {
		this.serverSocket = serverSocket;
		this.threadPool = threadPool;
		this.wasCancelled = wasCancelled;
		this.incomingEvents = incomingEvents;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				IncomingEventProcessor processor = new IncomingEventProcessor(socket, incomingEvents, wasCancelled);
				threadPool.submit(processor);
			} catch (IOException ex) {
				Logger.LogException("Caught exception while accepting incoming events!", ex);
			}
		}
	}
}