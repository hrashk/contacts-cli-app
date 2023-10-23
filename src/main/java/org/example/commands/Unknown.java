package org.example.commands;

/**
 * This class is used as a sentinel and is intentionally not marked as a component.
 */
public final class Unknown implements Command {
    @Override
    public boolean canHandle(String userInput) {
        return false;
    }

    @Override
    public String handle(String userInput) {
        return "Unknown command. Enter help for usage info.";
    }

    @Override
    public String getHelpString() {
        return null;
    }
}
