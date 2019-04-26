package com.maze;

public class BroadcastEvent extends Event {
	public BroadcastEvent(int sequenceNumber) {
		super(sequenceNumber);
	}

	@Override
	public String toString() {
		return String.format("%d|B", sequenceNumber);
	}

	@Override
	public void raiseEvent(IUsersBrowser users) {
		for (User user : users.getAll()) {
			user.consumeEvent(this);
		}
		Logger.logEvent(this);
	}
}
