package com.smarthome.command;

import com.smarthome.devices.SmartDevice;

public class TurnOnCommand implements Command {
    private final SmartDevice device;
    public TurnOnCommand(SmartDevice device) { this.device = device; }
    @Override public void execute() { device.turnOn(); }
    @Override public void undo() { device.turnOff(); }
}
