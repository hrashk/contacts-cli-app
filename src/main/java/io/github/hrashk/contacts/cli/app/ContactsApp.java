package io.github.hrashk.contacts.cli.app;

import io.github.hrashk.contacts.cli.app.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;

public class ContactsApp {

    static final String PROMPT = "contacts-app> ";

    public static void main(String[] args) {
        new ContactsApp().run();
    }

    void run() {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            var processor = ctx.getBean(UserInputProcessor.class);
            loop(processor, System.out);
        }
    }

    void loop(UserInputProcessor processor, PrintStream out) {
        prompt(out);
        for (String output : processor) {
            out.println(output);
            prompt(out);
        }
    }

    private static void prompt(PrintStream out) {
        out.print(PROMPT);
        out.flush();
    }
}
