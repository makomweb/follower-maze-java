package com.maze;

public class InvalidEventTypeException extends RuntimeException {
	private static final long serialVersionUID = -2846220643285984423L;

	public InvalidEventTypeException(String eventType) {
		super(String.format("This method is not allowed for an event of type '%s'", eventType));
	}
}