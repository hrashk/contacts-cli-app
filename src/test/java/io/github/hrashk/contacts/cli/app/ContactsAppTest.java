package io.github.hrashk.contacts.cli.app;

import io.github.hrashk.contacts.cli.app.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContactsAppTest extends ContextAware {

    @Test
    public void runCallsLoopWithValidArguments() {
        new TestApp().run();
    }

    @Test
    public void loopConsumesCommands() throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var processor = ctx.getBean(UserInputProcessor.class);
        processor.setReader(new StringReader("help\nquit"));

        String output = callLoop(new ContactsApp(), processor);

        assertEquals(3, StringUtils.countOccurrencesOf(output, ContactsApp.PROMPT), output);
    }

    private static String callLoop(ContactsApp app, UserInputProcessor processor) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outputStream)) {

            app.loop(processor, printStream);

            return outputStream.toString();
        }
    }

    static class TestApp extends ContactsApp {
        @Override
        void loop(UserInputProcessor processor, PrintStream out) {
            assertNotNull(processor);
            assertNotNull(out);
        }
    }
}