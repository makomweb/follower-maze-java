package com.maze;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class User implements Comparable<User> {
	private final Integer id;
	private final Set<Integer> followerIds = new HashSet<>();
	private PrintWriter writer;

	public User(int id, PrintWriter writer) {
		this.id = id;
		this.writer = writer;
	}

	public void addFollower(int followerId) {
		followerIds.add(followerId);
	}

	public void removeFollower(int followerId) {
		followerIds.remove(followerId);
	}

	public void notifyFollowers(Event event, Users users) {
		for (Integer id : followerIds) {
			User follower = users.get(id);
			boolean success = follower.consumeEvent(event);
			if (!success) {
				Logger.logErrorNotifyUser(follower);
				followerIds.remove(follower.getId());
			}
		}
	}

	public boolean consumeEvent(Event event) {
		if (writer != null) {
			writer.println(event);
			return !writer.checkError();
		}

		return false;
	}

	public Integer getId() { return id; }

	@Override
	public int compareTo(User other) { return id.compareTo(other.id); }
}
