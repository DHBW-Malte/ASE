package com.cor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Invoker {

    // Relative to the *project root* (working dir)
    private static final Path DEFAULT_HISTORY_FILE = Paths.get("")
            .toAbsolutePath()
            .resolve("Behavioral - Exercises/Liam-Malte-Cas-Behavioral/RobotControlSystem/src/main/java/com/cor/command_history");

    private final Stack<Command> commandHistory = new Stack<>();
    private final CommandRecorder recorder = new CommandRecorder();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
        saveHistory();
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
            saveHistory();
        }
    }

    private void saveHistory() {
        recorder.save(copyOfHistory(), DEFAULT_HISTORY_FILE);
    }

    private List<Command> copyOfHistory() {
        return new ArrayList<>(commandHistory);
    }
}