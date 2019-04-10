package com.maze;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Program {
	private static final AtomicBoolean wasCancelled = new AtomicBoolean(false);
	private static final Thread mainThread = Thread.currentThread();
	private static final Thread shutdownHook = new Thread() {
		public void run() {
			wasCancelled.set(false);
			try {
				mainThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().addShutdownHook(shutdownHook);

		ServerSocket userClientSocket = new ServerSocket(9099);
		userClientSocket.setSoTimeout(1000);
		ServerSocket incomingEventSocket = new ServerSocket(9090);
		incomingEventSocket.setSoTimeout(1000);

		PriorityBlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();
		Users users = new Users();

		ExecutorService threadPool = Executors.newCachedThreadPool();

		IncomingEventSocketServer incomingEventsSocketServer = new IncomingEventSocketServer(incomingEventSocket, threadPool, eventQueue, wasCancelled);
		UserClientSocketServer userClientSocketServer = new UserClientSocketServer(userClientSocket, threadPool, users, wasCancelled);
		EventQueueProcessor eventQueueProcessor = new EventQueueProcessor(users, eventQueue, wasCancelled);

		threadPool.submit(incomingEventsSocketServer);
		threadPool.submit(userClientSocketServer);
		threadPool.submit(eventQueueProcessor);
	}
}
