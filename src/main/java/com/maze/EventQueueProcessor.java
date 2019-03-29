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
        	final Event peek = eventQueue.peek();
            if (peek != null && peek.getSequenceNumber() <= sequenceNumer) {
	            final Event event = eventQueue.poll();
	            sequenceNumer++;
	            
	            if (event != null) {
	                try {
	                    event.raiseEvent(users);
	                } catch (RuntimeException  ex) {
	                    Logger.LogException("raiseEvent() has thrown", ex);
	                }
	            }
	        }
        }
    }
}
