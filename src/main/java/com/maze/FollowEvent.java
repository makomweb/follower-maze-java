package com.maze;

import java.io.IOException;

public class FollowEvent extends Event {
	private final int fromUserId;
	private final int toUserId;

	public FollowEvent(int sequenceNumber, int fromUserId, int toUserId) {
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
		return String.format("%d|F|%d|%d", getSequenceNumber(), getFromUserId(), getToUserId());
	}

	@Override
	public void raiseEvent(Users users) {
		try {
			users.follow(fromUserId, toUserId, this);
		} catch (IOException ex) {
			Logger.LogException("follow() has thrown", ex);
		}
	}
}
