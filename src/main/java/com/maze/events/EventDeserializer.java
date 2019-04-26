package com.maze.events;

public class EventDeserializer {
	public static Event Deserialize(String raw) {
		String[] parts = raw.split("\\|");
		return Deserialize(parts);
	}

	private static Event Deserialize(String[] parts) {
		int sequenceNumber = Integer.parseInt(parts[0]);
		String eventType = parts[1];

		switch (eventType) {
		case "F": {
			int fromUserId = Integer.parseInt(parts[2]);
			int toUserId = Integer.parseInt(parts[3]);
			return new FollowEvent(sequenceNumber, fromUserId, toUserId);
		}

		case "U": {
			int fromUserId = Integer.parseInt(parts[2]);
			int toUserId = Integer.parseInt(parts[3]);
			return new UnfollowEvent(sequenceNumber, fromUserId, toUserId);
		}

		case "B":
			return new BroadcastEvent(sequenceNumber);

		case "P": {
			int fromUserId = Integer.parseInt(parts[2]);
			int toUserId = Integer.parseInt(parts[3]);
			return new PrivateMessageEvent(sequenceNumber, fromUserId, toUserId);
		}

		case "S": {
			int fromUserId = Integer.parseInt(parts[2]);
			return new StatusUpdateEvent(sequenceNumber, fromUserId);
		}

		default:
			throw new InvalidEventTypeException(eventType);
		}
	}
}
