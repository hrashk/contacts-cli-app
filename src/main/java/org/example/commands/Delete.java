package org.example.commands;

import org.example.ContactsList;
import org.springframework.stereotype.Controller;

@Controller
public class Delete implements Command {
    public static final String CONTACT_DELETED = "1 contact deleted";
    private final ContactsList repo;

    public Delete(ContactsList repo) {
        this.repo = repo;
    }

    @Override
    public boolean canHandle(String userInput) {
        String command = userInput.split("\\s", 2)[0];
        return "rm".equalsIgnoreCase(command);
    }

    @Override
    public String handle(String userInput) {
        return CONTACT_DELETED;
    }

    @Override
    public String getHelpString() {
        return "rm <email> - delete the contact with the specified email";
    }
}
