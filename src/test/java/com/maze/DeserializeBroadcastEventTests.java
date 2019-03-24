package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeBroadcastEventTests {

	private final String raw = "542532|B";
	
	@Test
	public void Providing_event_type_should_succeed() {
		Event event = new Event(raw);
		assertEquals(EventType.BROADCAST, event.getEventType());
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = new Event(raw);
		assertEquals(542532, event.getSequenceNumber());
	}
	
	@Test(expected = InvalidEventTypeException.class)
	public void Providing_from_user_id_should_throw() {
		Event event = new Event(raw);
		int fromUserId = event.getFromUserId();
	}

	@Test(expected = InvalidEventTypeException.class)
	public void Providing_to_user_id_should_throw() {
		Event event = new Event(raw);
		int toUserId = event.getToUserId();
	}
}