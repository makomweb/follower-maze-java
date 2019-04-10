package com.maze;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class EventDispatcherTests {
	private Queue<Event> incomingEvents = new PriorityQueue<Event>();
	private List<Event> rawEvents = new ArrayList() {
		{
			add(EventDeserializer.Deserialize("1|B|2|3"));
			add(EventDeserializer.Deserialize("3|B|2|3"));
			add(EventDeserializer.Deserialize("5|B|2|3"));
			add(EventDeserializer.Deserialize("6|B|2|3"));
			add(EventDeserializer.Deserialize("4|B|2|3"));
			add(EventDeserializer.Deserialize("2|B|2|3"));
		}
	};

	public EventDispatcherTests() {
		for (Event e : rawEvents) {
			incomingEvents.add(e);
		}
	}

	@Test
	public void Incoming_events_should_be_sorted() {
		List<Event> collection = new ArrayList<Event>();

		while (true) {
			Event peek = incomingEvents.peek();
			if (peek == null) {
				break;
			}

			Event event = incomingEvents.poll();
			collection.add(event);
		}

		Collections.sort(rawEvents);
		assertCollection(collection, rawEvents);
	}

	private static void assertCollection(List<Event> actual, List<Event> expected) {
		assertEquals(actual, expected);
	}
}