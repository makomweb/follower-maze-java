package com.maze;

import java.util.*;

public class Event {
	private final String[] parts;

	private static final HashMap<String, EventType> eventTypes = new HashMap<String, EventType>() {
		{
			put("F", EventType.FOLLOW);
			put("U", EventType.UNFOLLOW);
			put("B", EventType.BROADCAST);
			put("P", EventType.PRIVATE_MSG);
			put("S", EventType.STATUS_UPDATE);
		}
	};

	public Event(String raw) {
		this(raw.split("\\|"));
	}

	public Event(String[] parts) {
		this.parts = parts;
	}

	public int getSequenceNumber() {
		return Integer.parseInt(parts[0]);
	}

	public EventType getEventType() {
		return eventTypes.get(parts[1]);
	}

	public int getFromUserId() {
		EventType eventType = getEventType();
		
		if (eventType == EventType.BROADCAST) {
			throw new InvalidEventTypeException(eventType);
		}

		return Integer.parseInt(parts[2]);
	}

	public int getToUserId() {
		EventType eventType = getEventType();
		
		switch (eventType) {
			case BROADCAST:
			case STATUS_UPDATE:
				throw new InvalidEventTypeException(eventType);
			default:
				return Integer.parseInt(parts[3]);
		}
	}
}
