package org.example;

import org.example.commands.Add;
import org.example.commands.Command;
import org.example.commands.Quit;
import org.example.commands.Show;

import java.io.StringReader;
import java.util.List;

public class TestData {
    public static ContactsList sampleRepo() {
        ContactsList repo = new ContactsList();
        repo.add(new Contact("Иванов Иван Иванович", "+890999999", "someEmail@example.example"));
        repo.add(new Contact("Смирнов Иван Петрович", "+890999998", "someEmail2@example.example"));
        repo.add(new Contact("Сидоров Иван Михайлович", "+890999997", "someEmail3@example.example"));
        return repo;
    }

    public static UserInputProcessor aProcessor(String userInput) {
        ContactsList repo = sampleRepo();
        return new UserInputProcessor(new StringReader(userInput), allCommands(repo));
    }

    public static UserInputProcessor aProcessorWithoutContacts(String userInput) {
        ContactsList repo = new ContactsList();
        return new UserInputProcessor(new StringReader(userInput), allCommands(repo));
    }

    public static List<Command> allCommands(ContactsList repo) {
        return List.of(new Show(repo), new Quit(), new Add(repo));
    }
}
