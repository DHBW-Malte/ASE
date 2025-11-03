package com.cor;

public class Invoker {
    private final Stack<Command> commandHistory = new Stack<>();
    private final CommandRecorder recorder = new CommandRecorder();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        }
    }

    public void saveHistory(String filePath) {
        recorder.save(new ArrayList<>(commandHistory), filePath);
    }
}