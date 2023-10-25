package io.github.hrashk.contacts.cli.app.commands;

import io.github.hrashk.contacts.cli.app.Contact;
import io.github.hrashk.contacts.cli.app.ContactsList;
import io.github.hrashk.contacts.cli.app.TestData;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddTest {

    @Test
    void handleNewContact() {
        ContactsList repo = TestData.sampleRepo();
        var a = new Add(repo);
        String response = a.handle("add Peter Petroff; +78001112233; peter@petroff.com");

        assertEquals(Add.CONTACT_ADDED, response);
        assertEquals(4, repo.getSize());

        Optional<Contact> byEmail = repo.findByEmail("peter@petroff.com");
        assertTrue(byEmail.isPresent(), "Contact is found by email");
        assertEquals("Peter Petroff", byEmail.get().name());
    }

    @Test
    void handleExistingContact() {
        ContactsList repo = TestData.sampleRepo();
        var a = new Add(repo);
        String response = a.handle("add Peter Petroff; +78001112233; someEmail2@example.example");

        assertEquals(Add.CONTACT_UPDATED, response);
        assertEquals(3, repo.getSize());

        Optional<Contact> byEmail = repo.findByEmail("someEmail2@example.example");
        assertTrue(byEmail.isPresent(), "Contact is found by email");
        assertEquals("Peter Petroff", byEmail.get().name());
    }

    @Test
    void handleContactWithoutEmail() {
        ContactsList repo = TestData.sampleRepo();
        var a = new Add(repo);
        String response = a.handle("add Peter Petroff; +78001112233");

        assertEquals(Add.INVALID_FORMAT, response);
        assertEquals(3, repo.getSize());
    }
}
