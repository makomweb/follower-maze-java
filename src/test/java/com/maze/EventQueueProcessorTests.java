package com.maze;

import com.maze.events.*;
import com.maze.users.UsersRepository;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class EventQueueProcessorTests {
    @Test
    public void Processing_events_from_queue_should_succeed() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        UsersRepository users = new UsersRepository();

        users.add(1, stream);
        users.add(2, stream);
        users.add(3, stream);

        EventQueue eventQueue = initializeEventQueue();

        EventQueueProcessor processor = new EventQueueProcessor(users, eventQueue);
        processor.processQueue();

        byte[] bytes = stream.toByteArray();
        String str = new String(bytes);

        String expected = "1|F|1|2\r\n" +
                "2|F|2|3\r\n" +
                "3|F|2|1\r\n" +
                "4|B\r\n" +
                "4|B\r\n" +
                "4|B\r\n" +
                "5|B\r\n" +
                "5|B\r\n" +
                "5|B\r\n" +
                "6|B\r\n" +
                "6|B\r\n" +
                "6|B\r\n" +
                "7|S|1\r\n" +
                "8|S|2\r\n" +
                "9|S|3\r\n" +
                "10|P|1|2\r\n" +
                "11|P|2|3\r\n" +
                "12|P|2|1\r\n";

        assertEquals(expected, str);
    }

    private EventQueue initializeEventQueue() {
        EventQueue eventQueue = new EventQueue();

        eventQueue.enqueue(new PrivateMessageEvent(10, 1, 2));
        eventQueue.enqueue(new PrivateMessageEvent(11, 2, 3));
        eventQueue.enqueue(new PrivateMessageEvent(12, 2, 1));

        eventQueue.enqueue(new FollowEvent(1, 1, 2));
        eventQueue.enqueue(new FollowEvent(2, 2, 3));
        eventQueue.enqueue(new FollowEvent(3, 2, 1));

        eventQueue.enqueue(new BroadcastEvent(4));
        eventQueue.enqueue(new BroadcastEvent(5));
        eventQueue.enqueue(new BroadcastEvent(6));

        eventQueue.enqueue(new StatusUpdateEvent(7, 1));
        eventQueue.enqueue(new StatusUpdateEvent(8, 2));
        eventQueue.enqueue(new StatusUpdateEvent(9, 3));
        return eventQueue;
    }
}
