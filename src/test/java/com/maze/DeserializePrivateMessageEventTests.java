package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializePrivateMessageEventTests {

	private final String raw = "43|P|32|56";
	
	@Test
	public void Providing_event_type_should_succeed() {
		Event event = new Event(raw);
		assertEquals(EventType.PRIVATE_MSG, event.getEventType());
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = new Event(raw);
		assertEquals(43, event.getSequenceNumber());
	}
	
	@Test
	public void Providing_from_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(32, event.getFromUserId());
	}
	
	@Test
	public void Providing_to_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(56, event.getToUserId());
	}
}