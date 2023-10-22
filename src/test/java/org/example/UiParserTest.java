package org.example;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class UiParserTest {

    @Test
    public void quit() {
        var parser = new UiParser(new StringReader("quit"));

        assertTrue(parser.hasNext());

        var c = parser.next();
        String output = c.exec();

        assertEquals("Good bye", output);
        assertFalse(parser.hasNext());
    }
}
