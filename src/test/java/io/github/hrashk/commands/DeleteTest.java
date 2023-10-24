package io.github.hrashk.commands;

import io.github.hrashk.Contact;
import io.github.hrashk.ContactsList;
import io.github.hrashk.TestData;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteTest {

    @Test
    void deleteExistingContact() {
        ContactsList repo = TestData.sampleRepo();
        var d = new Delete(repo);
        String response = d.handle("rm someEmail2@example.example");

        assertEquals(Delete.CONTACT_DELETED, response);
        assertEquals(2, repo.getSize());

        Optional<Contact> byEmail = repo.findByEmail("someEmail2@example.example");
        assertFalse(byEmail.isPresent(), "Contact is removed");
    }

    @Test
    void deleteNonexistentContact() {
        ContactsList repo = TestData.sampleRepo();
        var d = new Delete(repo);
        String response = d.handle("rm peter@petroff.com");

        assertEquals(Delete.WRONG_CONTACT, response);
        assertEquals(3, repo.getSize());
    }
}