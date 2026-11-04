package com.smarthome.strategy;

import com.smarthome.devices.DoorLock;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;

public class ComfortMode implements AutomationStrategy {
    @Override public String getName() { return "COMFORT"; }
    @Override public void apply(SmartDevice light, Thermostat thermostat, DoorLock doorLock) {
        light.turnOn();
        thermostat.setTemperature(22);
        System.out.println("Comfort mode activated.");
    }
}
