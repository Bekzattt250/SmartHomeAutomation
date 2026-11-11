package com.smarthome.strategy;
import com.smarthome.command.*;
import com.smarthome.devices.*;
import java.util.List;
public class ComfortMode implements AutomationStrategy {
    public String getName() { return "COMFORT"; }
    public List<Command> commands(SmartDevice light, Thermostat thermostat, DoorLock doorLock) {
        return List.of(new TurnOnCommand(light), new SetTemperatureCommand(thermostat, 22));
    }
}
