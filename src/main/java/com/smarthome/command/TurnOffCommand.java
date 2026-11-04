package com.smarthome.command;

import com.smarthome.devices.SmartDevice;

public class TurnOffCommand implements Command {
    private final SmartDevice device;
    public TurnOffCommand(SmartDevice device) { this.device = device; }
    @Override public void execute() { device.turnOff(); }
    @Override public void undo() { device.turnOn(); }
}
