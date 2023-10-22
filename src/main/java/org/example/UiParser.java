package org.example;

import org.example.commands.Command;
import org.example.commands.Quit;
import org.example.commands.Unknown;

import java.io.Reader;
import java.util.Scanner;

public class UiParser {
    private Scanner scanner;

    public UiParser(Reader reader) {
        this.scanner = new Scanner(reader);
    }

    public boolean hasNext() {
        return scanner != null && scanner.hasNext();
    }

    public Command next() {
        var input = scanner.nextLine();

        if ("quit".equalsIgnoreCase(input)) {
            scanner = null;
            return new Quit();
        }

        return new Unknown();
    }
}
