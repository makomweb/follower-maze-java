package com.maze;

public class Logger {
	public static void logFollow(FollowEvent event) {
		System.out.println(String.format("User %d started following %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void logUnfollow(UnfollowEvent event) {
		System.out.println(String.format("User %d stopped following %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void logException(String message, Exception ex) {
		System.err.println(message);
		System.err.println(ex);
	}

	public static void logUserConsumedEvent(Integer id, Event event) {
		System.out.println(String.format("User %d consumed event: %s.", id, event));
	}

	public static void logReceivedEvent(Event event) {
		System.out.println(String.format("Received event %s.", event));
	}

	public static void logAcceptedUser(int id) {
		System.out.println(String.format("Accepted user %d", id));
	}

	public static void LogErrorNotifyUser(User follower) {
		System.out.println(String.format("Error while notifying follower! Removing follower %d", follower.getId()));
	}
}
