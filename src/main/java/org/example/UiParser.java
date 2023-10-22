package org.example;

import org.example.commands.Command;
import org.example.commands.Quit;

import java.io.Reader;
import java.util.Scanner;

public class UiParser {
    private final Scanner scanner;

    public UiParser(Reader reader) {
        this.scanner = new Scanner(reader);
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public Command next() {
        var input = scanner.nextLine();
        if ("quit".equalsIgnoreCase(input))
            return new Quit();

        return null;
    }
}
