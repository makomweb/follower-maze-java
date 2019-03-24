package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeFollowEventTests {

	private final String raw = "666|F|60|50";
	
	@Test
	public void Providing_event_type_should_succeed() {
		Event event = new Event(raw);
		assertEquals(EventType.FOLLOW, event.getEventType());
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = new Event(raw);
		assertEquals(666, event.getSequenceNumber());
	}
	
	@Test
	public void Providing_from_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(60, event.getFromUserId());
	}
	
	@Test
	public void Providing_to_user_id_should_succeed() {
		Event event = new Event(raw);
		assertEquals(50, event.getToUserId());
	}
}