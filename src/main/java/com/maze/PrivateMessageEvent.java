package com.maze;

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
		User user = users.get(toUserId);
		user.consumeEvent(this);
		Logger.logEvent(this);
	}
}
