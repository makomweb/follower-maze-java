package com.maze;

public class UnfollowEvent extends Event {
	private final int fromUserId;
	private final int toUserId;

	public UnfollowEvent(int sequenceNumber, int fromUserId, int toUserId) {
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
		return String.format("%d|U|%d|%d", getSequenceNumber(), getFromUserId(), getToUserId());
	}

	@Override
	public void raiseEvent(Users users) {
		try {
			users.unfollow(fromUserId, toUserId);
		} catch (RuntimeException ex) {
			Logger.logException("unfollow() has thrown", ex);
		}
	}
}
