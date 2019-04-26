package com.maze;

import com.maze.events.EventQueue;
import com.maze.tools.ShutdownHook;
import com.maze.users.UsersRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
	public static void main(String[] args) throws IOException {

		ServerSocket userClientSocket = new ServerSocket(9099);
		userClientSocket.setSoTimeout(1000);
		ServerSocket incomingEventSocket = new ServerSocket(9090);
		incomingEventSocket.setSoTimeout(1000);

		EventQueue eventQueue = new EventQueue();
		UsersRepository users = new UsersRepository();

		IncomingEventSocketServer incomingEventsSocketServer = new IncomingEventSocketServer(incomingEventSocket, eventQueue);
		UserClientSocketServer userClientSocketServer = new UserClientSocketServer(userClientSocket, users);
		EventQueueProcessor eventQueueProcessor = new EventQueueProcessor(users, eventQueue);

		Runtime.getRuntime().addShutdownHook(new ShutdownHook(Thread.currentThread(),
				Arrays.asList(incomingEventsSocketServer, userClientSocketServer, eventQueueProcessor)));

		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		threadPool.submit(incomingEventsSocketServer);
		threadPool.submit(userClientSocketServer);
		threadPool.submit(eventQueueProcessor);
	}
}
