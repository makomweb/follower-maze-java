package com.maze.events;

import com.maze.diagnostics.Logger;
import com.maze.users.IUsersBrowser;
import com.maze.users.User;

public class StatusUpdateEvent extends Event {
	public final int fromUserId;

	public StatusUpdateEvent(int sequenceNumber, int fromUserId) {
		super(sequenceNumber);
		this.fromUserId = fromUserId;
	}

	@Override
	public String toString() {
		return String.format("%d|S|%d", sequenceNumber, fromUserId);
	}

	@Override
	public void raiseEvent(IUsersBrowser users) {
		try {
			User from = users.get(fromUserId);
			notifyFollowers(from, users);
		} catch (RuntimeException ex) {
			Logger.logExceptionNotifyFollowers(ex);
		}
	}

	private void notifyFollowers(User from, IUsersBrowser users) {
		for (Integer id : from.getFollowerIds()) {
			User follower = users.get(id);
			boolean success = follower.consumeEvent(this);
			Logger.logEventConsumed(follower, this);
			if (!success) {
				from.removeFollower(follower.getId());
			}
		}
	}
}
