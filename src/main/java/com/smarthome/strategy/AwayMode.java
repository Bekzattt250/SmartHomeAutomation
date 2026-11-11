package com.smarthome.strategy;
import com.smarthome.command.*;
import com.smarthome.devices.*;
import java.util.List;
public class AwayMode implements AutomationStrategy {
    public String getName() { return "AWAY"; }
    public List<Command> commands(SmartDevice light, Thermostat thermostat, DoorLock doorLock) {
        return List.of(new TurnOffCommand(light), new SetTemperatureCommand(thermostat, 17), new TurnOnCommand(doorLock));
    }
}
