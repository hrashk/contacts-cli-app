package org.example;

import org.example.commands.Command;
import org.example.commands.Quit;
import org.example.commands.Show;
import org.example.commands.Unknown;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class UserInputProcessor implements Iterable<String>, Iterator<String> {
    private Scanner scanner;
    private final ContactsList repo;

    public UserInputProcessor(Reader reader, ContactsList repo) {
        this.scanner = new Scanner(reader);
        this.repo = repo;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return scanner != null && scanner.hasNext();
    }

    @Override
    public String next() {
        String userInput = scanner.nextLine();

        Command c = findCommand(userInput);

        if (c instanceof Quit) {
            scanner = null;
        }

        return c.handle(userInput);
    }

    private Command findCommand(String input) {
        if ("quit".equalsIgnoreCase(input)) {
            return new Quit();
        } else if ("show".equalsIgnoreCase(input)) {
            return new Show(repo);
        }

        return new Unknown();
    }
}
