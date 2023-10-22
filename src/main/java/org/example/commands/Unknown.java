package org.example.commands;

public class Unknown implements Command {
    @Override
    public String exec() {
        return "Unknown command. Enter help for usage info.";
    }
}
