package org.example;

import java.io.StringReader;

public class TestData {
    public static ContactsList sampleRepo() {
        ContactsList repo = new ContactsList();
        repo.add(new Contact("Иванов Иван Иванович", "+890999999", "someEmail@example.example"));
        repo.add(new Contact("Смирнов Иван Петрович", "+890999998", "someEmail2@example.example"));
        repo.add(new Contact("Сидоров Иван Михайлович", "+890999997", "someEmail3@example.example"));
        return repo;
    }

    public static UserInputProcessor aProcessor(String userInput) {
        return new UserInputProcessor(new StringReader(userInput), sampleRepo());
    }

    public static UserInputProcessor aProcessorWithoutContacts(String userInput) {
        return new UserInputProcessor(new StringReader(userInput), new ContactsList());
    }
}
