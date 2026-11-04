package com.smarthome.strategy;

import com.smarthome.devices.DoorLock;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;

public class EcoMode implements AutomationStrategy {
    @Override public String getName() { return "ECO"; }
    @Override public void apply(SmartDevice light, Thermostat thermostat, DoorLock doorLock) {
        light.turnOff();
        thermostat.setTemperature(19);
        doorLock.turnOn();
        System.out.println("Eco mode activated: energy use is reduced.");
    }
}
