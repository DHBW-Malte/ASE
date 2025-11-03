package com.cor.commands;

import com.cor.Command;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Macro implements Command {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        if (command != null) {
            commands.add(command);
        }
    }

    public void remove(Command command) {
        commands.remove(command);
    }

    public void clear() {
        commands.clear();
    }

    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }

    @Override
    public void execute() {
        for (Command c : commands) {
            c.execute();
        }
    }

    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}