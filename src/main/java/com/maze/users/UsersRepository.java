package com.maze.users;

import com.maze.diagnostics.Logger;
import com.maze.tools.PrintWriterFrom;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class UsersRepository implements IUsersBrowser, IUsersRepository {
	private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

	public User get(int id) {
		if (!users.containsKey(id)) {
			User offlineUser = new User(id, PrintWriterFrom.NullStream());
			users.put(id, offlineUser);
			return offlineUser;
		}

		return users.get(id);
	}

	public Collection<User> getAll() {
		return users.values();
	}

	public void add(int id, Socket socket) throws IOException {
		add(id, socket.getOutputStream());
	}

	public void add(int id, OutputStream stream) {
		User user = new User(id, PrintWriterFrom.Stream(stream));
		users.put(id, user);
		Logger.logUserAdded(user);
	}
}
