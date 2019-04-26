package com.maze.users;

import java.io.IOException;
import java.net.Socket;

public interface IUsersRepository {
    void add(int id, Socket socket) throws IOException;
}