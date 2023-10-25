package io.github.hrashk.contacts.cli.app;

import io.github.hrashk.contacts.cli.app.commands.Command;
import io.github.hrashk.contacts.cli.app.commands.Quit;
import io.github.hrashk.contacts.cli.app.commands.Unknown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

@Component
public class UserInputProcessor implements Iterable<String>, Iterator<String> {
    private Scanner scanner;
    private final Collection<Command> commands;

    public UserInputProcessor(Collection<Command> commands) {
        this.commands = commands;
    }

    @Autowired
    public void setReader(Reader reader) {
        this.scanner = new Scanner(reader);
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
        String userInput = scanner.nextLine().trim();

        Command c = findCommand(userInput);

        if (c instanceof Quit) {
            scanner = null;
        }

        return c.handle(userInput);
    }

    private Command findCommand(String userInput) {
        return commands.stream()
                .filter(c -> c.canHandle(userInput))
                .findAny()
                .orElse(new Unknown());
    }
}
