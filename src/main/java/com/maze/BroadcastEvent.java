package com.maze;

public class BroadcastEvent extends Event {
	public BroadcastEvent(int sequenceNumber) {
		super(sequenceNumber);
	}

	@Override
	public String toString() {
		return String.format("%d|B", getSequenceNumber());
	}

	@Override
	public void raiseEvent(Users users) {
		for (User user : users.getAll()) {
			user.consumeEvent(this);
		}
	}
}
