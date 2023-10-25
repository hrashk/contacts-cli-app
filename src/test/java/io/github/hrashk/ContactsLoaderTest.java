package io.github.hrashk;

import io.github.hrashk.config.AppConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactsLoaderTest {
    private AnnotationConfigApplicationContext ctx;

    @AfterEach
    public void closeContext() {
        ctx.close();
    }

    @Test
    public void withoutLoader() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactsList contacts = ctx.getBean(ContactsList.class);
        assertTrue(contacts.isEmpty(), "There are no contacts");
    }


    @Test
    public void withLoader() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("init");
        ctx.register(AppConfig.class);
        ctx.refresh();

        ContactsList contacts = ctx.getBean(ContactsList.class);
        assertFalse(contacts.isEmpty(), "There are some contacts in the repo");
    }
}
