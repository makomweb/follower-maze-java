package com.maze;

import java.io.IOException;

public class StatusUpdateEvent extends Event {
	private final int fromUserId;

	public StatusUpdateEvent(int sequenceNumber, int fromUserId) {
		super(sequenceNumber);
		this.fromUserId = fromUserId;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	@Override
	public String toString() {
		return String.format("%d|S|%d", getSequenceNumber(), getFromUserId());
	}

	@Override
	public void raiseEvent(Users users) {
		try {
			User user = users.get(fromUserId);
			user.notifyFollowers(this, users);
		} catch (RuntimeException | IOException ex) {
			Logger.LogException("notifyFollowers() has thrown", ex);
		}
	}
}
