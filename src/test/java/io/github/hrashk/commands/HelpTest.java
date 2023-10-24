package io.github.hrashk.commands;

import io.github.hrashk.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpTest {
    private Help help;

    @BeforeEach
    public void setUpContext() {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        help = ctx.getBean(Help.class);
    }

    @Test
    void handle() {
        String output = help.handle("help");

        assertTrue(Stream.of("quit", "help", "save").allMatch(output::contains),
                "Expected usage to show all commands but was: " + output);
    }
}