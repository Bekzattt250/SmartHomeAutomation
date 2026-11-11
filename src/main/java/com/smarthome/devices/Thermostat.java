package com.smarthome.devices;
public class Thermostat implements SmartDevice {
    private int temperature = 22;
    private boolean enabled = true;
    public String getName() { return "Thermostat"; }
    public String getStatus() { return enabled ? temperature + " C (active)" : "OFF"; }
    public boolean isOn() { return enabled; }
    public void turnOn() { enabled = true; System.out.println("Thermostat is active."); }
    public void turnOff() { enabled = false; System.out.println("Thermostat is OFF."); }
    public void setTemperature(int value) {
        if (value < 16 || value > 30) throw new IllegalArgumentException("Temperature must be between 16 and 30 C.");
        temperature = value;
        enabled = true;
        System.out.println("Thermostat temperature set to " + value + " C.");
    }
    public int getTemperature() { return temperature; }
    public void restore(int value, boolean active) {
        setTemperature(value);
        if (!active) turnOff();
    }
}
