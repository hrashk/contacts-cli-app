package org.example;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class UiParserTest {

    @Test
    public void quit() {
        var parser = new UiParser(new StringReader("quit\nlist"));

        assertTrue(parser.hasNext(), "More commands are available");

        var c = parser.next();
        String output = c.exec();

        assertEquals("Good bye", output);
        assertFalse(parser.hasNext(), "More commands are available");
    }

    @Test
    public void unknown() {
        var parser = new UiParser(new StringReader("asdf fdsa"));

        assertTrue(parser.hasNext(), "More commands are available");

        var c = parser.next();
        String output = c.exec();

        assertTrue(output.startsWith("Unknown"));
    }

    @Test
    public void showEmpty() {
        var parser = new UiParser(new StringReader("show"));

        assertTrue(parser.hasNext(), "More commands are available");

        var c = parser.next();
        String output = c.exec();

        assertEquals("You have no contacts", output);
    }

    @Test
    public void show() {
        var parser = new UiParser(new StringReader("show"), TestData.sampleRepo());

        assertTrue(parser.hasNext(), "More commands are available");

        var c = parser.next();
        String output = c.exec();

        assertTrue(output.contains("|"), "Shows contacts");
    }
}
