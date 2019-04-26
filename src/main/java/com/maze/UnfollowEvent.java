package com.maze;

public class UnfollowEvent extends Event {
	private final int fromUserId;
	private final int toUserId;

	public UnfollowEvent(int sequenceNumber, int fromUserId, int toUserId) {
		super(sequenceNumber);
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
	}

	@Override
	public String toString() {
		return String.format("%d|U|%d|%d", sequenceNumber, fromUserId, toUserId);
	}

	@Override
	public void raiseEvent(IUsersBrowser users) {
		User to = users.get(toUserId);
		to.removeFollower(fromUserId);
	}
}
