package com.maze.events;

import com.maze.diagnostics.Logger;
import com.maze.users.IUsersBrowser;
import com.maze.users.User;

public class FollowEvent extends Event {
	public final int fromUserId;
	public final int toUserId;

	public FollowEvent(int sequenceNumber, int fromUserId, int toUserId) {
		super(sequenceNumber);
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
	}

	@Override
	public String toString() {
		return String.format("%d|F|%d|%d", sequenceNumber, fromUserId, toUserId);
	}

	@Override
	public void raiseEvent(IUsersBrowser users) {
		User to = users.get(toUserId);
		to.addFollower(fromUserId);
		to.consumeEvent(this);
		Logger.logEventConsumed(to, this);
	}
}
