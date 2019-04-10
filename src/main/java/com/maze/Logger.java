package com.maze;

public class Logger {
	public static void LogFollow(FollowEvent event) {
		System.out.println(String.format("User %d started following %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void LogUnfollow(UnfollowEvent event) {
		System.out.println(String.format("User %d stopped following %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void LogException(String message, Exception ex) {
		System.err.println(message);
		System.err.println(ex);
	}

	public static void LogUserConsumedEvent(Integer id, Event event) {
		System.out.println(String.format("User %d writes event: %s.", id, event));
	}

	public static void LogReceivedEvent(Event event) {
		System.out.println(String.format("Received event %s.", event));
	}

	public static void LogAcceptedUser(int id) {
		System.out.println(String.format("Accepted user %d", id));
	}

	public static void LogErrorNotifyFollower(User follower) {
		System.out.println(String.format("Error while notifying follower! Removing follower %d", follower.getId()));
	}
}
