package com.maze;

import com.maze.events.FollowEvent;
import com.maze.tools.PrintWriterFrom;
import com.maze.users.User;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class UserTests {
    @Test
    public void writing_event_should_succeed() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintWriter writer = PrintWriterFrom.FromStream(byteStream);
        User user = new User(1, writer);
        user.consumeEvent(new FollowEvent(44, 22, 11));

        byte[] bytes = byteStream.toByteArray();
        String str = new String(bytes);

        assertEquals("44|F|22|11\r\n", str);
    }
}
