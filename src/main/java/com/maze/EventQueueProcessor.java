package com.maze;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventQueueProcessor implements Runnable {
	private final Users users;
	private final Queue<Event> eventQueue;
	private final AtomicBoolean wasCancelled;
	int sequenceNumer = 1;

	public EventQueueProcessor(Users users, Queue<Event> eventQueue, AtomicBoolean wasCancelled) {
		this.users = users;
		this.eventQueue = eventQueue;
		this.wasCancelled = wasCancelled;
	}

	@Override
	public void run() {
		while (!wasCancelled.get()) {
			Event event = eventQueue.peek();
			if (event != null && event.getSequenceNumber() <= sequenceNumer) {
				event = eventQueue.poll();
				sequenceNumer++;

				try {
					event.raiseEvent(users);
				} catch (RuntimeException ex) {
					Logger.LogException("raiseEvent() has thrown", ex);
				}
			}
		}
	}
}
