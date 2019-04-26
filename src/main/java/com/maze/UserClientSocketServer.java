package com.maze;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserClientSocketServer implements Runnable {
	private final ServerSocket serverSocket;
	private final ExecutorService threadPool;
	private final UsersRepository users;
	private final AtomicBoolean wasCancelled;

	UserClientSocketServer(ServerSocket serverSocket, ExecutorService threadPool, UsersRepository users, AtomicBoolean wasCancelled) {
		this.serverSocket = serverSocket;
		this.threadPool = threadPool;
		this.users = users;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		while (!wasCancelled.get()) {
			try {
				Socket socket = serverSocket.accept();
				UserClientProcessor processor = new UserClientProcessor(socket, users);
				threadPool.submit(processor);
			} catch (IOException ex) {
				Logger.logException("Caught exception while accepting client connections!", ex);
			}
		}
	}
}