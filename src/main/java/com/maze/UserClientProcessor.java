package com.maze;

import java.io.*;
import java.net.Socket;

public class UserClientProcessor implements Runnable {
	private final Socket socket;
	private final UsersRepository users;

	public UserClientProcessor(Socket socket, UsersRepository users) {
		this.socket = socket;
		this.users = users;
	}

	@Override
	public void run() {
		try {
			process();
		} catch (IOException ex) {
			Logger.logException("Caught exception while accepting user client!", ex);
		}
	}

	private void process() throws IOException {
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
