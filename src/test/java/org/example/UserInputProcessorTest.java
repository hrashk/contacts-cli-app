package org.example;

import org.example.commands.Add;
import org.example.commands.Delete;
import org.example.commands.Quit;
import org.example.commands.Save;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputProcessorTest {

    @Test
    public void quit() {
        var processor = TestData.aProcessor("quit\nlist");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertEquals(Quit.GOOD_BYE, output);
        assertFalse(processor.hasNext(), "More commands are available");
    }

    @Test
    public void unknown() {
        var processor = TestData.aProcessor("asdf fdsa");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.startsWith("Unknown"), "Expected unknown message but was " + output);
    }

    @Test
    public void show() {
        var processor = TestData.aProcessor("show");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.contains("|"), "Expected contacts listed but was " + output);
    }

    @Test
    public void add() {
        var processor = TestData.aProcessor("add Peter Petroff; +78001112233; peter@petroff.com");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertEquals(Add.CONTACT_ADDED, output);
    }

    @Test
    public void delete() {
        var processor = TestData.aProcessor("rm someEmail2@example.example");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertEquals(Delete.CONTACT_DELETED, output);
    }

    @Test
    public void save() {
        var processor = TestData.aProcessor("save");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.startsWith(Save.FILE_SAVED), "Expected contacts to be saved but was " + output);
    }

    @Test
    public void help() {
        var processor = TestData.aProcessor("help");

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.contains("Usage"), "Expected usage info but was: " + output);
    }
}
