package org.example.commands;

import org.example.Contact;
import org.example.ContactsList;

import java.util.stream.Collectors;

public class Show implements Command {
    private final ContactsList repo;

    public Show(ContactsList repo) {
        this.repo = repo;
    }

    @Override
    public String exec() {
        if (repo == null || repo.isEmpty())
            return "You have no contacts";

        return repo.getAll().stream()
                .map(Show::contactToString)
                .collect(Collectors.joining("\n"));
    }

    private static String contactToString(Contact contact) {
        return String.format("%s | %s | %s", contact.name(), contact.phone(), contact.email());
    }
}
