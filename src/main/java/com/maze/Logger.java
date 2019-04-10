package com.maze;

public class Logger {
	public static void logEvent(FollowEvent event) {
		System.out.println(String.format("User %d started following %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void logEvent(UnfollowEvent event) {
		System.out.println(String.format("User %d stopped following %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void logEvent(BroadcastEvent event) {
		System.out.println("Broadcast event.");
	}

	public static void logEvent(PrivateMessageEvent event) {
		System.out.println(String.format("User %d has sent private message to %d.", event.getToUserId(), event.getFromUserId()));
	}

	public static void logEvent(StatusUpdateEvent event) {
		System.out.println(String.format("User %d has requested a status update from the followers.", event.getFromUserId()));
	}

	public static void logException(String message, Exception ex) {
		System.err.println(message);
		System.err.println(ex);
	}

	public static void logReceivedEvent(Event event) {
		//System.out.println(String.format("Received event %s.", event));
	}

	public static void logAcceptedUser(int id) {
		//System.out.println(String.format("Accepted user %d", id));
	}

	public static void logErrorNotifyUser(User follower) {
		System.out.println(String.format("Error while notifying follower! Removing follower %d", follower.getId()));
	}
}
