package org.example;

import org.example.commands.Command;
import org.example.commands.Quit;
import org.example.commands.Show;
import org.example.commands.Unknown;

import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class UserInputProcessor implements Iterable<String>, Iterator<String> {
    private Scanner scanner;
    private final ContactsRepo repo;

    public UserInputProcessor(Reader reader) {
        this(reader, null);
    }

    public UserInputProcessor(Reader reader, ContactsRepo repo) {
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
        String input = scanner.nextLine();

        Command c = findCommand(input);

        if (c instanceof Quit) {
            scanner = null;
        }

        return c.exec();
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
