package com.smarthome.strategy;

import com.smarthome.devices.DoorLock;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;

public class AwayMode implements AutomationStrategy {
    @Override public String getName() { return "AWAY"; }
    @Override public void apply(SmartDevice light, Thermostat thermostat, DoorLock doorLock) {
        light.turnOff();
        thermostat.setTemperature(17);
        doorLock.turnOn();
        System.out.println("Away mode activated: the home is secured.");
    }
}
