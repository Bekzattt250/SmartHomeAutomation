package com.smarthome.devices;

public class Light implements SmartDevice {
    private final String name;
    private boolean on;

    public Light(String name) { this.name = name; }
    @Override public String getName() { return name; }
    @Override public String getStatus() { return on ? "ON" : "OFF"; }
    @Override public void turnOn() { on = true; System.out.println(name + " is ON."); }
    @Override public void turnOff() { on = false; System.out.println(name + " is OFF."); }
}
