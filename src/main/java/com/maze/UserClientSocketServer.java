package com.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserClientSocketServer implements Runnable {
	private final ServerSocket serverSocket;
	private final IUsersRepository users;
	private final AtomicBoolean wasCancelled;

	UserClientSocketServer(ServerSocket serverSocket, IUsersRepository users, AtomicBoolean wasCancelled) {
		this.serverSocket = serverSocket;
		this.users = users;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		while (!wasCancelled.get()) {
			try {
				Socket socket = serverSocket.accept();
				process(socket);
			} catch (IOException ex) {
				Logger.logException("Caught exception while accepting client connections!", ex);
			}
		}
	}

	private void process(Socket socket) throws IOException {
		InputStream stream = socket.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(streamReader);

		String line = reader.readLine();
		if (line != null) {
			int id = Integer.parseInt(line);
			users.add(id, socket);
			Logger.logAcceptedUser(id);
		}
	}
}