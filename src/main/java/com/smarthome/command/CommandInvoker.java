package com.smarthome.command;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();
    public void executeCommand(Command command) {
        Objects.requireNonNull(command).execute();
        history.push(command);
    }
    public boolean undoLastCommand() {
        if (history.isEmpty()) return false;
        history.peek().undo();
        history.pop();
        return true;
    }
    public int historySize() { return history.size(); }
}
