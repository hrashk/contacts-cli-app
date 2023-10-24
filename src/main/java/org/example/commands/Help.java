package org.example.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Controller
public class Help implements Command {

    private Collection<String> helpStrings;

    /**
     * Spring does not inject a Help bean into the collection despite the fact that we're injecting in a setter
     * rather than the constructor.
     */
    @Autowired
    public void setCommands(Collection<Command> commands) {
        helpStrings = new HashSet<>();
        helpStrings.add(getHelpString());
        commands.stream().map(Command::getHelpString).forEach(h -> helpStrings.add(h));
    }

    @Override
    public boolean canHandle(String userInput) {
        return "help".equalsIgnoreCase(userInput);
    }

    @Override
    public String handle(String userInput) {
        return "Commands:\n" + helpStrings.stream().sorted().collect(Collectors.joining("\n"));
    }

    @Override
    public String getHelpString() {
        return "help - list available commands with their short description";
    }
}
