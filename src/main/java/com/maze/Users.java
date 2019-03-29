package com.maze;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.maze.FollowEvent;
import com.maze.UnfollowEvent;

public class Users {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();

    public User get(int id) {
    	if (!users.containsKey(id)) {
    		User offlineUser = new User(id, new PrintWriter(System.out));
    		users.put(id, offlineUser);
    		return offlineUser;
    	}
    	
    	return users.get(id);
    }
    
    public Collection<User> getAll() {
        return users.values();
    }

	public void add(int id, Socket socket) throws IOException {
		OutputStream stream = socket.getOutputStream();
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		PrintWriter writer = new PrintWriter(streamWriter);
		
		users.put(id, new User(id, writer));		
	}

	public void remove(int id) {
		users.remove(id);		
	}

	public void follow(int fromUserId, int toUserId, FollowEvent event) throws IOException {
		User to = get(toUserId);
		to.addFollower(fromUserId);
		to.consumeEvent(event);
	}

	public void unfollow(int fromUserId, int toUserId, UnfollowEvent event) {
		User to = get(toUserId);
		to.removeFollower(fromUserId);
		//to.consumeEvent(event);
	}
}
