package com.maze.diagnostics;

import com.maze.events.*;
import com.maze.users.User;

import java.io.IOException;

public class Logger {
	public static void logEventConsumed(User user, Event event) {
		//System.out.println(String.format("User %s has consumed event %s.", user, event));
	}

	public static void logReceivedEvent(Event event) {
		//System.out.println(String.format("Received event %s.", event));
	}

	public static void logUserAdded(User user) {
		System.out.println(String.format("Accepted user %s", user));
	}

	public static void logExceptionRaisingEvent(RuntimeException exception) {
		System.err.println(String.format("Exception caught while raising event: %s", exception));
	}

	public static void logExceptionProcessingIncomingEvents(IOException exception) {
		System.err.println(String.format("Exception caught while processing incoming events: %s", exception));
	}

	public static void logExceptionProcessingClientConnections(IOException exception) {
		System.err.println(String.format("Exception caught while processing client connections: %s", exception));
	}

	public static void logExceptionNotifyFollowers(RuntimeException exception) {
		System.err.println(String.format("Exception caught while notifying followers: %s", exception));
	}
}
