package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputProcessorTest {

    @Test
    public void quit() {
        var processor = TestData.aProcessorForInput("quit\nlist");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertEquals("Good bye", output);
        assertFalse(processor.hasNext(), "More commands are available");
    }

    @Test
    public void unknown() {
        var processor = TestData.aProcessorForInput("asdf fdsa");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.startsWith("Unknown"));
    }

    @Test
    public void showEmpty() {
        var processor = TestData.aProcessorWithoutContacts("show");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertEquals("You have no contacts", output);
    }

    @Test
    public void show() {
        var processor = TestData.aProcessorForInput("show");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.contains("|"), "Shows contacts");
    }
}
