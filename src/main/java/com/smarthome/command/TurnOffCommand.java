package com.smarthome.command;
import com.smarthome.devices.SmartDevice;
import java.util.Objects;
public class TurnOffCommand implements Command {
    private final SmartDevice device;
    private boolean previous;
    private boolean executed;
    public TurnOffCommand(SmartDevice device) { this.device = Objects.requireNonNull(device); }
    public void execute() { previous = device.isOn(); device.turnOff(); executed = true; }
    public void undo() { if (executed) { if (previous) device.turnOn(); else device.turnOff(); executed = false; } }
}
