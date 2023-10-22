package org.example.commands;

public class Quit implements Command {
    @Override
    public String exec() {
        return "Good bye";
    }
}
