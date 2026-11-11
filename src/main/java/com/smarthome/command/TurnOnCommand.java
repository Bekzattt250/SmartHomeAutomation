package com.smarthome.command;
import com.smarthome.devices.SmartDevice;
import java.util.Objects;
public class TurnOnCommand implements Command {
    private final SmartDevice device;
    private boolean previous;
    private boolean executed;
    public TurnOnCommand(SmartDevice device) { this.device = Objects.requireNonNull(device); }
    public void execute() { previous = device.isOn(); device.turnOn(); executed = true; }
    public void undo() { if (executed) { if (previous) device.turnOn(); else device.turnOff(); executed = false; } }
}
