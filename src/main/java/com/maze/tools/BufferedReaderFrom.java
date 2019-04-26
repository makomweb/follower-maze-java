package com.maze.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class BufferedReaderFrom {
    public static BufferedReader Socket(Socket socket) throws IOException {
        InputStream stream = socket.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(stream);
        return new BufferedReader(streamReader);
    }
}
