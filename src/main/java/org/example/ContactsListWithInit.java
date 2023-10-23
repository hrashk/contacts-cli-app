package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Repository
@Profile("init")
public class ContactsListWithInit extends ContactsList {

    public ContactsListWithInit(@Value("${app.load.path}") String filePath) {
        readContacts(filePath);
    }

    private void readContacts(String filePath) {
        try {
            Files.readAllLines(Path.of(filePath)).stream()
                    .map(Contact::fromString)
                    .forEach(this::add);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
