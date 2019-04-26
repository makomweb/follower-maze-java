package com.maze;

public interface IEventQueue {
    void enqueue(Event event);
    Event peek();
    Event dequeue();
}
