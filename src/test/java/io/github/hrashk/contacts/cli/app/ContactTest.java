package io.github.hrashk.contacts.cli.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ContactTest {

    @Test
    void fromString() {
        String input = "   george   ; 8901   ; a@b.com   ";
        Contact actual = Contact.fromString(input);
        assertEquals(new Contact("george", "8901", "a@b.com"), actual);
    }

    @Test
    void comparingWithNullContact() {
        var c = new Contact("george", "8901", "a@b.com");

        assertFalse(c.hasSameEmailAs((Contact) null), "Handle null gracefully");
    }
}
