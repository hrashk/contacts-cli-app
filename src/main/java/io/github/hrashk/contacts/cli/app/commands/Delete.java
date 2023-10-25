package io.github.hrashk.contacts.cli.app.commands;

import io.github.hrashk.contacts.cli.app.Contact;
import io.github.hrashk.contacts.cli.app.ContactsList;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Delete implements Command {
    public static final String CONTACT_DELETED = "1 contact deleted";
    public static final String WRONG_CONTACT = "There is no contact with that email";
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
        String email = userInput.split("\\s+", 2)[1];
        Optional<Contact> byEmail = repo.findByEmail(email);

        if (byEmail.isEmpty()){
            return WRONG_CONTACT;
        } else {
            repo.removeByEmail(email);
            return CONTACT_DELETED;
        }
    }

    @Override
    public String getHelpString() {
        return "rm <email> - delete the contact with the specified email";
    }
}
