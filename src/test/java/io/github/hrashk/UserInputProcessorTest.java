package io.github.hrashk;

import io.github.hrashk.commands.Add;
import io.github.hrashk.commands.Delete;
import io.github.hrashk.commands.Quit;
import io.github.hrashk.commands.Save;
import io.github.hrashk.config.AppConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class UserInputProcessorTest extends ContextAware {

    private UserInputProcessor processor;
    private String saveFilePath;

    @BeforeEach
    public void setUpContext() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var repo = ctx.getBean(ContactsList.class);
        TestData.addSampleContacts(repo);

        processor = ctx.getBean(UserInputProcessor.class);
        saveFilePath = ctx.getEnvironment().getProperty("app.save.path");
    }

    @AfterEach
    public void deleteSavedFile() {
        new File(saveFilePath).delete();
    }

    @Test
    public void quit() {
        processor.setReader(new StringReader("quit\nlist"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        Assertions.assertEquals(Quit.GOOD_BYE, output);
        assertFalse(processor.hasNext(), "More commands are available");
    }

    @Test
    public void unknown() {
        processor.setReader(new StringReader("asdf fdsa"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.startsWith("Unknown"), "Expected unknown message but was " + output);
    }

    @Test
    public void show() {
        processor.setReader(new StringReader("show"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.contains("|"), "Expected contacts listed but was " + output);
    }

    @Test
    public void add() {
        processor.setReader(new StringReader("add Peter Petroff; +78001112233; peter@petroff.com"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertEquals(Add.CONTACT_ADDED, output);
    }

    @Test
    public void delete() {
        processor.setReader(new StringReader("rm someEmail2@example.example"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        Assertions.assertEquals(Delete.CONTACT_DELETED, output);
    }

    @Test
    public void save() {
        processor.setReader(new StringReader("save"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.startsWith(Save.FILE_SAVED), "Expected contacts to be saved but was " + output);

        File savedFile = new File(saveFilePath);
        assertTrue(savedFile.exists(), "File was created");
        assertTrue(savedFile.length() > 0, "File is not empty");
    }

    @Test
    public void help() {
        processor.setReader(new StringReader("help"));

        assertTrue(processor.hasNext(), "More commands are available");

        String output = processor.next();

        assertTrue(output.contains("Commands"), "Expected usage info but was: " + output);
    }
}
