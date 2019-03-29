package com.maze;

public abstract class Event implements Comparable<Event> {
	private final int sequenceNumber;

	public Event(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

    @Override
    public int compareTo(Event other) {
        return Integer.compare(getSequenceNumber(), other.getSequenceNumber());
    }
    
    public abstract void raiseEvent(Users users);
}
