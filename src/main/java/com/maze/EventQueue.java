package com.maze;

import java.util.concurrent.PriorityBlockingQueue;

public class EventQueue implements IEventQueue {
    PriorityBlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();

    @Override
    public void enqueue(Event event) {
        eventQueue.add(event);
    }

    @Override
    public Event peek() {
        return eventQueue.peek();
    }

    @Override
    public Event dequeue() {
        return eventQueue.poll();
    }
}
