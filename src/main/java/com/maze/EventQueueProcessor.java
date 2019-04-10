package com.maze;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventQueueProcessor implements Runnable {
	private final Users users;
	private final Queue<Event> eventQueue;
	private final AtomicBoolean wasCancelled;
	private int sequenceNumber = 1;

	public EventQueueProcessor(Users users, Queue<Event> eventQueue, AtomicBoolean wasCancelled) {
		this.users = users;
		this.eventQueue = eventQueue;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		while (!wasCancelled.get()) {
			Event event = eventQueue.peek();
			if (event != null && event.getSequenceNumber() <= sequenceNumber) {
				event = eventQueue.poll();
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
