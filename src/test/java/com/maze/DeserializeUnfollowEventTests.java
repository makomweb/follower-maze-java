package com.maze;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializeUnfollowEventTests {

	private final String raw = "1|U|12|9";
	
	@Test
	public void Event_should_be_of_type_unfollow_event() {
		Event event = EventDeserializer.Deserialize(raw);
		assertTrue(event instanceof UnfollowEvent);
	}
	
	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals(1, event.getSequenceNumber());
	}
	
	@Test
	public void Providing_from_user_id_should_succeed() {
		UnfollowEvent event = (UnfollowEvent) EventDeserializer.Deserialize(raw);
		assertEquals(12, event.getFromUserId());
	}
	
	@Test
	public void Providing_to_user_id_should_succeed() {
		UnfollowEvent event = (UnfollowEvent) EventDeserializer.Deserialize(raw);
		assertEquals(9, event.getToUserId());
	}
	
	@Test
	public void Stringify_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals("1|U|12|9", event.toString());
	}
}