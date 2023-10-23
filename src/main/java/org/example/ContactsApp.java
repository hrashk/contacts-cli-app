package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContactsApp {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var parser = ctx.getBean(UserInputProcessor.class);

        for (String output : parser) {
            System.out.println(output);
        }
    }
}
