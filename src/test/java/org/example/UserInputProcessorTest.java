package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputProcessorTest {

    @Test
    public void quit() {
        var parser = TestData.aProcessorForInput("quit\nlist");

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertEquals("Good bye", output);
        assertFalse(parser.hasNext(), "More commands are available");
    }

    @Test
    public void unknown() {
        var parser = TestData.aProcessorForInput("asdf fdsa");

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertTrue(output.startsWith("Unknown"));
    }

    @Test
    public void showEmpty() {
        var parser = TestData.aProcessorWithoutContacts("show");

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertEquals("You have no contacts", output);
    }

    @Test
    public void show() {
        var parser = TestData.aProcessorForInput("show");

        assertTrue(parser.hasNext(), "More commands are available");

        String output = parser.next();

        assertTrue(output.contains("|"), "Shows contacts");
    }
}
