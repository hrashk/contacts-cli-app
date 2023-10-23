package org.example.commands;

import org.example.ContactsList;
import org.springframework.stereotype.Controller;

@Controller
public class Add implements Command {
    public static final String CONTACT_ADDED = "1 contact added";
    public static final String CONTACT_UPDATED = "1 contact updated";

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
        return CONTACT_ADDED;
    }

    @Override
    public String getHelpString() {
        return "add <name; phone; email> - adds a new contact to the list or updates existing one by email";
    }
}
