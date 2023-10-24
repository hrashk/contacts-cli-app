package io.github.hrashk.commands;

import io.github.hrashk.Contact;
import io.github.hrashk.ContactsList;
import org.springframework.stereotype.Component;

@Component
public class Add implements Command {
    public static final String CONTACT_ADDED = "1 contact added";
    public static final String CONTACT_UPDATED = "1 contact updated";
    public static final String INVALID_FORMAT = "Contact format is invalid. Type help for usage info.";

    private final ContactsList repo;

    public Add(ContactsList repo) {
        this.repo = repo;
    }

    @Override
    public boolean canHandle(String userInput) {
        String command = userInput.split("\\s", 2)[0];
        return "add".equalsIgnoreCase(command);
    }

    @Override
    public String handle(String userInput) {
        try {
            Contact contact = parse(userInput);
            return handle(contact);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return INVALID_FORMAT;
        }
    }

    private String handle(Contact contact) {
        if (repo.findByEmail(contact.email()).isEmpty()) {
            repo.add(contact);
            return CONTACT_ADDED;
        } else {
            repo.update(contact);
            return CONTACT_UPDATED;
        }
    }

    private static Contact parse(String userInput) {
        String contactDetails = userInput.split("\\s+", 2)[1];
        return Contact.fromString(contactDetails);
    }

    @Override
    public String getHelpString() {
        return "add <name; phone; email> - adds a new contact to the list or updates existing one by email";
    }
}
