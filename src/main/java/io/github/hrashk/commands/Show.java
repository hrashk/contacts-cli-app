package io.github.hrashk.commands;

import io.github.hrashk.Contact;
import io.github.hrashk.ContactsList;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Show implements Command {
    public static final String NO_CONTACTS = "You have no contacts";

    private final ContactsList repo;

    public Show(ContactsList repo) {
        this.repo = repo;
    }

    @Override
    public String getHelpString() {
        return "show - list all contacts";
    }

    @Override
    public boolean canHandle(String userInput) {
        return "show".equalsIgnoreCase(userInput);
    }

    @Override
    public String handle(String userInput) {
        if (repo == null || repo.isEmpty())
            return NO_CONTACTS;

        return repo.getAll().stream()
                .map(Show::contactToString)
                .collect(Collectors.joining("\n"));
    }

    private static String contactToString(Contact contact) {
        return String.format("%s | %s | %s", contact.name(), contact.phone(), contact.email());
    }
}
