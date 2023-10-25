package io.github.hrashk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    @Test
    void fromString() {
        String input = "   george   ; 8901   ; a@b.com   ";
        Contact actual = Contact.fromString(input);
        assertEquals(new Contact("george", "8901", "a@b.com"), actual);
    }
}