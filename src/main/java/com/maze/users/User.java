package com.maze.users;

import com.maze.events.Event;

import java.io.PrintWriter;
import java.util.Collection;
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

	public boolean consumeEvent(Event event) {
		writer.println(event);
		return !writer.checkError();
	}

	public Collection<Integer> getFollowerIds () {
		return followerIds;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int compareTo(User other) { return id.compareTo(other.id); }

	@Override
	public String toString() {
		return String.format("%d", id);
	}
}
