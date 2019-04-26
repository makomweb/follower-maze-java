package com.maze.tools;

import java.util.concurrent.atomic.AtomicBoolean;

public class ShutdownHook extends Thread {
    private final AtomicBoolean wasCancelled;
    private final Thread mainThread;

    public ShutdownHook(AtomicBoolean wasCancelled, Thread mainThread) {
        this.wasCancelled = wasCancelled;
        this.mainThread = mainThread;
    }

    public void run() {
        wasCancelled.set(false);
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
