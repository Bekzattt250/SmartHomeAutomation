package com.smarthome.command;
import java.util.ArrayList;
import java.util.List;
public class CompositeCommand implements Command {
    private final List<Command> commands;
    private int executed;
    public CompositeCommand(List<Command> commands) { this.commands = List.copyOf(commands); }
    public void execute() {
        executed = 0;
        try {
            for (Command command : commands) { command.execute(); executed++; }
        } catch (RuntimeException e) {
            undo();
            throw e;
        }
    }
    public void undo() {
        for (int i = executed - 1; i >= 0; i--) commands.get(i).undo();
        executed = 0;
    }
}
