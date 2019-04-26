package com.maze;

import com.maze.events.EventQueue;
import com.maze.tools.ShutdownHook;
import com.maze.users.UsersRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Program {
	private static final AtomicBoolean wasCancelled = new AtomicBoolean(false);

	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().addShutdownHook(new ShutdownHook(wasCancelled, Thread.currentThread()));

		ServerSocket userClientSocket = new ServerSocket(9099);
		userClientSocket.setSoTimeout(1000);
		ServerSocket incomingEventSocket = new ServerSocket(9090);
		incomingEventSocket.setSoTimeout(1000);

		EventQueue eventQueue = new EventQueue();
		UsersRepository users = new UsersRepository();

		IncomingEventSocketServer incomingEventsSocketServer = new IncomingEventSocketServer(incomingEventSocket, eventQueue, wasCancelled);
		UserClientSocketServer userClientSocketServer = new UserClientSocketServer(userClientSocket, users, wasCancelled);
		EventQueueProcessor eventQueueProcessor = new EventQueueProcessor(users, eventQueue, wasCancelled);

		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.submit(incomingEventsSocketServer);
		threadPool.submit(userClientSocketServer);
		threadPool.submit(eventQueueProcessor);
	}
}
