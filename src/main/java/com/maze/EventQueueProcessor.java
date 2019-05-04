package com.maze;

import com.maze.diagnostics.Logger;
import com.maze.events.Event;
import com.maze.events.IEventQueue;
import com.maze.tools.CancellationRunnable;
import com.maze.users.IUsersBrowser;

public class EventQueueProcessor extends CancellationRunnable {
	private final IUsersBrowser users;
	private final IEventQueue eventQueue;
	private int sequenceNumber = 1;

	public EventQueueProcessor(IUsersBrowser users, IEventQueue eventQueue) {
		this.users = users;
		this.eventQueue = eventQueue;
	}

	@Override
	public void run() {
		Logger.logEventQueueProcessingStarted();
		while (!cancellationRequested) {
			process();
		}

		Logger.logEventQueueProcessingFinished();
	}

	public void processQueue() {
		while (eventQueue.peek() != null) {
			process();
		}
	}

	private void process() {
		Event event = eventQueue.peek();
		if (event != null && event.sequenceNumber <= sequenceNumber) {
			event = eventQueue.dequeue();
			sequenceNumber++;

			try {
				event.raiseEvent(users);
			} catch (RuntimeException ex) {
				Logger.logExceptionRaisingEvent(ex);
			}
		}
	}
}
