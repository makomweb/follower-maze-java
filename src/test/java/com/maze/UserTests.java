package com.maze;

import com.maze.events.FollowEvent;
import com.maze.tools.NullStream;
import com.maze.tools.PrintWriterFrom;
import com.maze.users.User;
import com.maze.users.UsersRepository;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class UserTests {
    @Test
    public void Writing_event_should_succeed() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintWriter writer = PrintWriterFrom.Stream(byteStream);
        User user = new User(1, writer);
        user.consumeEvent(new FollowEvent(44, 22, 11));

        byte[] bytes = byteStream.toByteArray();
        String str = new String(bytes);

        assertEquals("44|F|22|11\r\n", str);
    }

    @Test
    public void Adding_user_to_repo_should_succeed() {
        UsersRepository repo = new UsersRepository();
        repo.add(1, new NullStream());
        User user = repo.get(1);
        assertTrue(user.getId() == 1);
    }

    @Test
    public void Fetching_from_empty_repo_should_fill_repo_and_give_user() {
        UsersRepository repo = new UsersRepository();
        User user = repo.get(44);
        assertTrue(user.getId() == 44);
    }
}
