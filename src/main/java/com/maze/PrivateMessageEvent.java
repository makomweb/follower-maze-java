package com.maze;

import java.io.IOException;

public class PrivateMessageEvent extends Event {
	private final int fromUserId;
	private final int toUserId;

	public PrivateMessageEvent(int sequenceNumber, int fromUserId, int toUserId) {
		super(sequenceNumber);
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	public int getToUserId() {
		return toUserId;
	}

	@Override
	public String toString() {
		return String.format("%d|P|%d|%d", getSequenceNumber(), getFromUserId(), getToUserId());
	}

	@Override
	public void raiseEvent(Users users) {
		try {
			User user = users.get(toUserId);
			user.consumeEvent(this);
		} catch (IOException ex) {
			Logger.LogException("concumeEvent() has thrown", ex);
		}
	}
}
