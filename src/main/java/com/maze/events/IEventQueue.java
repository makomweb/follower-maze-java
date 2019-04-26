package com.maze.events;

public interface IEventQueue {
    void enqueue(Event event);
    Event peek();
    Event dequeue();
}
