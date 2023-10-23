package org.example;

import org.example.commands.Command;
import org.example.commands.Quit;
import org.example.commands.Show;
import org.example.commands.Unknown;

import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class UiParser implements Iterable<Command>, Iterator<Command> {
    private Scanner scanner;
    private final ContactsRepo repo;

    public UiParser(Reader reader) {
        this(reader, null);
    }

    public UiParser(Reader reader, ContactsRepo repo) {
        this.scanner = new Scanner(reader);
        this.repo = repo;
    }

    @Override
    public boolean hasNext() {
        return scanner != null && scanner.hasNext();
    }

    @Override
    public Command next() {
        var input = scanner.nextLine();

        if ("quit".equalsIgnoreCase(input)) {
            scanner = null;
            return new Quit();
        } else if ("show".equalsIgnoreCase(input)) {
            return new Show(repo);
        }

        return new Unknown();
    }

    @Override
    public Iterator<Command> iterator() {
        return this;
    }
}
