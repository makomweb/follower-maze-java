package com.maze;

public class InvalidEventTypeException extends RuntimeException {
	public InvalidEventTypeException(EventType eventType) {
		super(String.format("This method is not allowed for an event of type '%s'", eventType));
	}
}