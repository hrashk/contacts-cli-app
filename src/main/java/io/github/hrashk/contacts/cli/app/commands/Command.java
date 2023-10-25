package io.github.hrashk.contacts.cli.app.commands;

public interface Command {
    boolean canHandle(String userInput);
    String handle(String userInput);
    String getHelpString();
}
