package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeUnfollowEventTests {

	private final String raw = "1|U|12|9";
	
	@Test
	public void Providing_event_type_should_succeed() {
		Event event = new Event(raw);
		assertEquals(EventType.UNFOLLOW, event.getEventType());
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = new Event(raw);
		assertEquals(1, event.getSequenceNumber());
	}
	
	@Test
	public void Providing_from_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(12, event.getFromUserId());
	}
	
	@Test
	public void Providing_to_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(9, event.getToUserId());
	}
}