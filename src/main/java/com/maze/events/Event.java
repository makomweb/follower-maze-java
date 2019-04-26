package com.maze.events;

import com.maze.users.IUsersBrowser;

public abstract class Event implements Comparable<Event> {
	public final int sequenceNumber;

	public Event(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@Override
	public int compareTo(Event other) {
		return Integer.compare(sequenceNumber, other.sequenceNumber);
	}

	public abstract void raiseEvent(IUsersBrowser users);
}
