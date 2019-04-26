package com.maze;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class UsersRepository implements IUsersBrowser, IUsersRepository {
	private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

	public synchronized User get(int id) {
		if (!users.containsKey(id)) {
			User offlineUser = new User(id, new PrintWriter(System.out));
			users.put(id, offlineUser);
			return offlineUser;
		}

		return users.get(id);
	}

	public synchronized Collection<User> getAll() {
		return users.values();
	}

	public synchronized void add(int id, Socket socket) throws IOException {
		OutputStream stream = socket.getOutputStream();
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		PrintWriter writer = new PrintWriter(streamWriter);

		users.put(id, new User(id, writer));
	}
}
