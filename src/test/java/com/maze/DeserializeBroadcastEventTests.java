package com.maze;

import com.maze.events.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeBroadcastEventTests {

	private final String raw = "542532|B";

	@Test
	public void Event_should_be_of_type_broadcast_event() {
		Event event = EventDeserializer.Deserialize(raw);
		assertTrue(event instanceof BroadcastEvent);
	}

	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals(542532, event.sequenceNumber);
	}

	@Test
	public void Stringify_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals("542532|B", event.toString());
	}
}