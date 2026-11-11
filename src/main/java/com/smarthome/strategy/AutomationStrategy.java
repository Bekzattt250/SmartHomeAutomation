package com.smarthome.strategy;
import com.smarthome.command.Command;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;
import java.util.List;
public interface AutomationStrategy {
    String getName();
    List<Command> commands(SmartDevice light, Thermostat thermostat, DoorLock doorLock);
}
