package org.example.commands;

public interface Command {
    boolean canHandle(String userInput);
    String handle(String userInput);
    String getHelpString();
}
