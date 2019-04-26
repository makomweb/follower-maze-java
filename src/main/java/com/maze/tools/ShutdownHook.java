package com.maze.tools;

import com.maze.diagnostics.Logger;

import java.util.Collection;

public class ShutdownHook extends Thread {
    private final Thread mainThread;
    private final Collection<CancellationRunnable> runnables;

    public ShutdownHook(Thread mainThread, Collection<CancellationRunnable> runnables) {
        this.mainThread = mainThread;
        this.runnables = runnables;
    }

    public void run() {
        Logger.logShutdownInitiated();

        runnables.forEach(r -> r.requestCancellation());

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Logger.logShutdownFinished();
        }
    }
}
