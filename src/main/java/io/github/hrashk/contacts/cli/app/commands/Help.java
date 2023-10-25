package io.github.hrashk.contacts.cli.app.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
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
        return "Commands:\n" + helpStrings.stream()
                .map(this::formatHelpLine)
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    private String formatHelpLine(String line) {
        String[] pieces = line.split("\\s*-\\s*", 2);
        return String.format("  %-30s%s", pieces[0], pieces[1]);
    }

    @Override
    public String getHelpString() {
        return "help - list available commands with their short description";
    }
}
