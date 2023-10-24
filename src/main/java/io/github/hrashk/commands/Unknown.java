package io.github.hrashk.commands;

/**
 * This class is used as a sentinel and is intentionally not marked as a component.
 */
public final class Unknown implements Command {

    public static final String UNKNOWN_COMMAND = "Unknown command. Enter help for usage info.";

    @Override
    public boolean canHandle(String userInput) {
        return false;
    }

    @Override
    public String handle(String userInput) {
        return UNKNOWN_COMMAND;
    }

    @Override
    public String getHelpString() {
        return null;
    }
}
