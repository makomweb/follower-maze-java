package com.maze;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import com.maze.Logger;
import com.maze.Users;

public class UserClientProcessor implements Runnable {
	private final Socket socket;
    private final Users users;
    private final AtomicBoolean wasCancelled;

    public UserClientProcessor(Socket socket, Users users, AtomicBoolean wasCancelled) {
    	this.socket = socket;
        this.users = users;
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

    private void process() throws IOException {
        InputStream stream = socket.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(stream);
		final BufferedReader reader = new BufferedReader(streamReader);

        while (!wasCancelled.get()) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			
			try {
				int id = Integer.parseInt(line);
				users.add(id, socket);
			} catch (RuntimeException ex) {
				Logger.LogException("add() has thrown", ex);
			}
		}
    }
}
