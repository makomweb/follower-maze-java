package com.maze.events;

import com.maze.diagnostics.Logger;
import com.maze.users.IUsersBrowser;
import com.maze.users.User;

public class PrivateMessageEvent extends Event {
	private final int fromUserId;
	private final int toUserId;

	public PrivateMessageEvent(int sequenceNumber, int fromUserId, int toUserId) {
		super(sequenceNumber);
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
	}

	@Override
	public String toString() {
		return String.format("%d|P|%d|%d", sequenceNumber, fromUserId, toUserId);
	}

	@Override
	public void raiseEvent(IUsersBrowser users) {
		User user = users.get(toUserId);
		user.consumeEvent(this);
		Logger.logEventConsumed(user, this);
	}
}
