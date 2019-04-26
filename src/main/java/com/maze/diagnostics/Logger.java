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
		//System.err.println(String.format("Exception caught while processing incoming events: %s", exception));
	}

	public static void logExceptionProcessingClientConnections(IOException exception) {
		//System.err.println(String.format("Exception caught while processing client connections: %s", exception));
	}

	public static void logExceptionNotifyFollowers(RuntimeException exception) {
		//System.err.println(String.format("Exception caught while notifying followers: %s", exception));
	}

	public static void logEventQueueProcessingStarted() {
		System.out.println("Start processing event queue.");
	}

	public static void logShutdownInitiated() {
		System.out.println("Shutting down ...");
	}

	public static void logShutdownFinished() {
		System.out.println("Shutdown finished.");
	}

	public static void logStartAcceptingUserConnections() {
		System.out.println("Start accepting user connections.");
	}

	public static void logStartProcessingIncomingEvents() {
		System.out.println("Start processing incoming events.");
	}

	public static void logEventQueueProcessingFinished() {
		System.out.println("Event queue processing finished.");
	}

	public static void logProcessingIncomingEventsFinished() {
		System.out.println("Event processing incoming events finished.");
	}

	public static void logAcceptingUserConnectionsFinished() {
		System.out.println("Accepting user connections finished.");
	}
}
