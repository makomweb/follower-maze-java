package com.maze.tools;

import java.io.*;
import java.net.Socket;

public class PrintWriterFrom  {
	public static PrintWriter Socket(Socket socket) throws IOException {
		OutputStream stream = socket.getOutputStream();
		OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
		return new PrintWriter(streamWriter);
	}

	public static PrintWriter NullStream() {
		return new PrintWriter(new NullStream());
	}
}
