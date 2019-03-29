package com.maze;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class AcceptUserClientServer implements Runnable {
	private final ServerSocket serverSocket;
	private final ExecutorService threadPool;
	private final Users users;
	private final AtomicBoolean wasCancelled;

	AcceptUserClientServer(ServerSocket serverSocket, ExecutorService threadPool, Users users, AtomicBoolean wasCancelled) {
		this.serverSocket = serverSocket;
		this.threadPool = threadPool;
		this.users = users;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				AcceptUserClientProcessor processor = new AcceptUserClientProcessor(socket, users, wasCancelled);
				threadPool.submit(processor);
			} catch (IOException ex) {
				Logger.LogException("Caught exception while accepting client connections!", ex);
			}
		}
	}
}