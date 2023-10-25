package io.github.hrashk.commands;

import io.github.hrashk.config.AppConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpTest {
    private ConfigurableApplicationContext ctx;
    private Help help;

    @BeforeEach
    public void setUpContext() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        help = ctx.getBean(Help.class);
    }

    @AfterEach
    public void closeContext() {
        ctx.close();
    }

    @Test
    void handle() {
        String output = help.handle("help");

        assertTrue(Stream.of("quit", "help", "save").allMatch(output::contains),
                "Expected usage to show all commands but was: " + output);
    }
}