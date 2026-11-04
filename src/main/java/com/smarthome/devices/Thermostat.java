package com.smarthome.devices;

public class Thermostat implements SmartDevice {
    private int temperature = 22;
    private boolean enabled = true;
    @Override public String getName() { return "Thermostat"; }
    @Override public String getStatus() { return enabled ? temperature + " C (active)" : "OFF"; }
    @Override public void turnOn() { enabled = true; System.out.println("Thermostat is active."); }
    @Override public void turnOff() { enabled = false; System.out.println("Thermostat is OFF."); }
    public void setTemperature(int temperature) {
        if (temperature < 16 || temperature > 30) {
            System.out.println("Temperature must be between 16 and 30 C.");
            return;
        }
        this.temperature = temperature;
        enabled = true;
        System.out.println("Thermostat temperature set to " + temperature + " C.");
    }
    public int getTemperature() { return temperature; }
}
