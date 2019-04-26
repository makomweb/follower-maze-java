package com.maze;

import com.maze.events.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeStatusUpdateEventTests {

	private final String raw = "634|S|32";

	@Test
	public void Event_should_be_of_type_status_update_event() {
		Event event = EventDeserializer.Deserialize(raw);
		assertTrue(event instanceof StatusUpdateEvent);
	}

	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals(634, event.sequenceNumber);
	}

	@Test
	public void Providing_from_user_id_should_succeed() {
		StatusUpdateEvent event = (StatusUpdateEvent) EventDeserializer.Deserialize(raw);
		assertEquals(32, event.fromUserId);
	}

	@Test
	public void Stringify_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals("634|S|32", event.toString());
	}
}