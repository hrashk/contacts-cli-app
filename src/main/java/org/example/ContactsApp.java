package org.example;

import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContactsApp {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var parser = ctx.getBean(UserInputProcessor.class);

        prompt();
        for (String output : parser) {
            System.out.println(output);
            prompt();
        }
    }

    private static void prompt() {
        System.out.print("contacts-app> ");
        System.out.flush();
    }
}
