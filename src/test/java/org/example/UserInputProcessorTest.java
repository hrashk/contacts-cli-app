package org.example;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class UserInputProcessorTest {

    @Test
    public void quit() {
        var parser = new UserInputProcessor(new StringReader("quit\nlist"));

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertEquals("Good bye", output);
        assertFalse(parser.hasNext(), "More commands are available");
    }

    @Test
    public void unknown() {
        var parser = new UserInputProcessor(new StringReader("asdf fdsa"));

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertTrue(output.startsWith("Unknown"));
    }

    @Test
    public void showEmpty() {
        var parser = new UserInputProcessor(new StringReader("show"));

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertEquals("You have no contacts", output);
    }

    @Test
    public void show() {
        var parser = new UserInputProcessor(new StringReader("show"), TestData.sampleRepo());

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertTrue(output.contains("|"), "Shows contacts");
    }
}
