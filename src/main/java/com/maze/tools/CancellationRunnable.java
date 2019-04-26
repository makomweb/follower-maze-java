package com.maze.tools;

public abstract class CancellationRunnable implements Runnable {
	protected volatile boolean cancellationRequested = false;
	public void requestCancellation() {
		cancellationRequested = true;
	}
}
