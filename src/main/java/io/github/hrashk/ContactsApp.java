package io.github.hrashk;

import io.github.hrashk.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContactsApp {
    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            var processor = ctx.getBean(UserInputProcessor.class);
            loop(processor);
        }
    }

    private static void loop(UserInputProcessor processor) {
        prompt();
        for (String output : processor) {
            System.out.println(output);
            prompt();
        }
    }

    private static void prompt() {
        System.out.print("contacts-app> ");
        System.out.flush();
    }
}
