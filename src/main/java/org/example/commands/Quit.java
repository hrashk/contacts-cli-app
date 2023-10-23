package org.example.commands;

import org.springframework.stereotype.Controller;

@Controller
public class Quit implements Command {

    public static final String GOOD_BYE = "Good bye";

    @Override
    public boolean canHandle(String userInput) {
        return "quit".equalsIgnoreCase(userInput);
    }

    @Override
    public String handle(String userInput) {
        return GOOD_BYE;
    }

    @Override
    public String getHelpString() {
        return "quit - terminate the application";
    }
}
