package com.maze;

import com.maze.diagnostics.Logger;
import com.maze.events.Event;
import com.maze.events.IEventQueue;
import com.maze.users.IUsersBrowser;

import java.util.concurrent.atomic.AtomicBoolean;

public class EventQueueProcessor implements Runnable {
	private final IUsersBrowser users;
	private final IEventQueue eventQueue;
	private final AtomicBoolean wasCancelled;
	private int sequenceNumber = 1;

	public EventQueueProcessor(IUsersBrowser users, IEventQueue eventQueue, AtomicBoolean wasCancelled) {
		this.users = users;
		this.eventQueue = eventQueue;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		while (!wasCancelled.get()) {
			Event event = eventQueue.peek();
			if (event != null && event.sequenceNumber <= sequenceNumber) {
				event = eventQueue.dequeue();
				sequenceNumber++;

				try {
					event.raiseEvent(users);
				} catch (RuntimeException ex) {
					Logger.logException("raiseEvent() has thrown", ex);
				}
			}
		}
	}
}
