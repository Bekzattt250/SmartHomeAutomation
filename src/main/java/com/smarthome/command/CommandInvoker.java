package com.smarthome.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();
    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }
    public void undoLastCommand() {
        if (history.isEmpty()) {
            System.out.println("There is no command to undo.");
            return;
        }
        history.pop().undo();
        System.out.println("Last command was undone.");
    }
}
