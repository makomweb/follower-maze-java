package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeFollowEventTests {

	private final String raw = "666|F|60|50";
	
	@Test
	public void Event_should_be_of_type_follow_event() {
		Event event = EventDeserializer.Deserialize(raw);
		assertTrue(event instanceof FollowEvent);
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		FollowEvent event = (FollowEvent) EventDeserializer.Deserialize(raw);
		assertEquals(666, event.getSequenceNumber());
	}
	
	@Test
	public void Providing_from_user_id_should_succeed() {
		FollowEvent event = (FollowEvent) EventDeserializer.Deserialize(raw);
		assertEquals(60, event.getFromUserId());
	}
	
	@Test
	public void Providing_to_user_id_should_succeed() {
		FollowEvent event = (FollowEvent) EventDeserializer.Deserialize(raw);
		assertEquals(50, event.getToUserId());
	}
	
	@Test
	public void Stringify_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals("666|F|60|50", event.toString());
	}
}