package org.example;

import org.example.commands.Command;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContactsApp {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var parser = ctx.getBean(UiParser.class);

        while (parser.hasNext()) {
            Command c = parser.next();
            String output = c.exec();
            System.out.println(output);
        }
    }
}
