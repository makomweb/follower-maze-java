package com.maze;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
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

    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        
        ServerSocket userSocketServer = new ServerSocket(9099);
        userSocketServer.setSoTimeout(1000);
        ServerSocket socket = new ServerSocket(9090);
        socket.setSoTimeout(1000);
        
        PriorityBlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();
        Users users = new Users();
        
        ExecutorService threadPool = Executors.newCachedThreadPool();
        
        IncomingEventSocketServer incomingEventsSocketServer = new IncomingEventSocketServer(socket, threadPool, eventQueue, wasCancelled);       
        AcceptUserClientServer userClientServer = new AcceptUserClientServer(userSocketServer, threadPool, users, wasCancelled);
        UserResponseClientProcessor userResponseClientProcessor = new UserResponseClientProcessor(users, eventQueue, wasCancelled);

        threadPool.submit(incomingEventsSocketServer);
        threadPool.submit(userClientServer);
        threadPool.submit(userResponseClientProcessor);
    }
}
