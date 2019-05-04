package com.maze.tools;

import java.io.*;

public class PrintWriterFrom  {
	public static PrintWriter NullStream() {
		return Stream(new NullStream());
	}

	public static PrintWriter Stream(OutputStream stream) {
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		return new PrintWriter(streamWriter);
	}
}
