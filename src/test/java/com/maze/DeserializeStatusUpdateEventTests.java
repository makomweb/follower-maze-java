package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeStatusUpdateEventTests {

	private final String raw = "634|S|32";
	
	@Test
	public void Providing_event_type_should_succeed() {
		Event event = new Event(raw);
		assertEquals(EventType.STATUS_UPDATE, event.getEventType());
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = new Event(raw);
		assertEquals(634, event.getSequenceNumber());
	}
	
	@Test
	public void Providing_from_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(32, event.getFromUserId());
	}

	@Test(expected = InvalidEventTypeException.class)
	public void Providing_to_user_id_should_throw() {
		Event event = new Event(raw);
		int toUserId = event.getToUserId();
	}
	
	@Test
	public void Stringify_should_succeed() {
		Event event = new Event(raw);
		assertEquals("634|STATUS_UPDATE|32|-", event.toString());
	}
}