package com.maze;

public class StatusUpdateEvent extends Event {
	private final int fromUserId;

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
			Logger.logEvent(this);
		} catch (RuntimeException ex) {
			Logger.logException("notifyFollowers() has thrown", ex);
		}
	}

	private void notifyFollowers(User from, IUsersBrowser users) {
		for (Integer id : from.getFollowerIds()) {
			User follower = users.get(id);
			boolean success = follower.consumeEvent(this);
			if (!success) {
				Logger.logErrorNotifyUser(follower);
				from.removeFollower(follower.getId());
			}
		}
	}
}
