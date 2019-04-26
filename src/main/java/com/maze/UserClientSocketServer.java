package com.maze;

import com.maze.diagnostics.Logger;
import com.maze.tools.BufferedReaderFrom;
import com.maze.tools.CancellationRunnable;
import com.maze.users.IUsersRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserClientSocketServer extends CancellationRunnable {
	private final ServerSocket serverSocket;
	private final IUsersRepository users;

	UserClientSocketServer(ServerSocket serverSocket, IUsersRepository users) {
		this.serverSocket = serverSocket;
		this.users = users;
	}

	@Override
	public void run() {
		Logger.logStartAcceptingUserConnections();
		while (!cancellationRequested) {
			try {
				Socket socket = serverSocket.accept();
				process(socket);
			} catch (IOException ex) {
				Logger.logExceptionProcessingClientConnections(ex);
			}
		}
		Logger.logAcceptingUserConnectionsFinished();
	}

	private void process(Socket socket) throws IOException {
		BufferedReader reader = BufferedReaderFrom.Socket(socket);
		String line = reader.readLine();
		if (line != null) {
			int id = Integer.parseInt(line);
			users.add(id, socket);
		}
	}
}