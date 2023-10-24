package io.github.hrashk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactsListWithInitTest {

    @Test
    public void readContacts() {
        var repo = new ContactsListWithInit("src/main/resources/default-contacts.txt");
        assertEquals(3, repo.getSize());
    }
}
