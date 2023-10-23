package org.example.commands;

import org.example.Contact;
import org.example.ContactsList;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class Save implements Command {
    public static final String NO_CONTACTS = "You have no contacts";

    private final ContactsList repo;

    public Save(ContactsList repo) {
        this.repo = repo;
    }

    @Override
    public String getHelpString() {
        return "save - save all contacts into a file specified in the app configuration";
    }

    @Override
    public boolean canHandle(String userInput) {
        return "save".equalsIgnoreCase(userInput);
    }

    @Override
    public String handle(String userInput) {
        if (repo == null || repo.isEmpty())
            return NO_CONTACTS;

        try {
            writeToFile();
            return "File saved at contacts.txt";
        } catch (IOException e) {
            return "Failed to save the contacts: " + e.getMessage();
        }
    }

    private void writeToFile() throws IOException {
        try (var out = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (Contact contact : repo.getAll()) {
                out.write(contactToString(contact));
                out.newLine();
            }
        }
    }

    private static String contactToString(Contact contact) {
        return String.format("%s;%s;%s", contact.name(), contact.phone(), contact.email());
    }
}
