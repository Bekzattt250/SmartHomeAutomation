package com.smarthome.command;
import com.smarthome.devices.Thermostat;
import java.util.Objects;
public class SetTemperatureCommand implements Command {
    private final Thermostat thermostat;
    private final int target;
    private int previous;
    private boolean previousEnabled;
    private boolean executed;
    public SetTemperatureCommand(Thermostat thermostat, int target) {
        this.thermostat = Objects.requireNonNull(thermostat);
        if (target < 16 || target > 30) throw new IllegalArgumentException("Temperature must be between 16 and 30 C.");
        this.target = target;
    }
    public void execute() {
        previous = thermostat.getTemperature();
        previousEnabled = thermostat.isOn();
        thermostat.setTemperature(target);
        executed = true;
    }
    public void undo() { if (executed) { thermostat.restore(previous, previousEnabled); executed = false; } }
}
