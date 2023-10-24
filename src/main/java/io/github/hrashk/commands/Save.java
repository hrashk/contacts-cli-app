package io.github.hrashk.commands;

import io.github.hrashk.Contact;
import io.github.hrashk.ContactsList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class Save implements Command {
    public static final String NO_CONTACTS = "You have no contacts";
    public static final String FILE_SAVED = "Contacts saved in file ";

    private final ContactsList repo;
    private final String filePath;

    public Save(ContactsList repo, @Value("${app.save.path}") String filePath) {
        this.repo = repo;
        this.filePath = filePath;
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
            return FILE_SAVED + filePath;
        } catch (IOException e) {
            return "Failed to save the contacts: " + e.getMessage();
        }
    }

    private void writeToFile() throws IOException {
        try (var out = new BufferedWriter(new FileWriter(filePath))) {
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
