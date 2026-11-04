package com.smarthome.strategy;

import com.smarthome.devices.DoorLock;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;

public interface AutomationStrategy {
    String getName();
    void apply(SmartDevice light, Thermostat thermostat, DoorLock doorLock);
}
