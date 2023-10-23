package org.example.commands;

import org.springframework.stereotype.Controller;

@Controller
public class Quit implements Command {
    @Override
    public boolean canHandle(String userInput) {
        return "quit".equalsIgnoreCase(userInput);
    }

    @Override
    public String handle(String userInput) {
        return "Good bye";
    }

    @Override
    public String getHelpString() {
        return "quit - terminate the application";
    }
}
