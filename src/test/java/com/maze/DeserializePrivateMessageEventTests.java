package com.maze;

import com.maze.events.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeserializePrivateMessageEventTests {

	private final String raw = "43|P|32|56";

	@Test
	public void Event_should_be_of_type_private_message_event() {
		Event event = EventDeserializer.Deserialize(raw);
		assertTrue(event instanceof PrivateMessageEvent);
	}

	@Test
	public void Providing_sequence_number_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals(43, event.sequenceNumber);
	}

	@Test
	public void Providing_from_user_id_should_succeed() {
		PrivateMessageEvent event = (PrivateMessageEvent) EventDeserializer.Deserialize(raw);
		assertEquals(32, event.fromUserId);
	}

	@Test
	public void Providing_to_user_id_should_succeed() {
		PrivateMessageEvent event = (PrivateMessageEvent) EventDeserializer.Deserialize(raw);
		assertEquals(56, event.toUserId);
	}

	@Test
	public void Stringify_should_succeed() {
		Event event = EventDeserializer.Deserialize(raw);
		assertEquals("43|P|32|56", event.toString());
	}
}