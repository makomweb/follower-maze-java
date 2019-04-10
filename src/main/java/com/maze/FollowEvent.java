package com.maze;

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
		users.follow(fromUserId, toUserId, this);
		Logger.logEvent(this);
	}
}
