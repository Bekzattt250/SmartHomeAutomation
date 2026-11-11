package com.smarthome.devices;
public class Light implements SmartDevice {
    private final String name;
    private boolean on;
    public Light(String name) { this.name = name; }
    public String getName() { return name; }
    public String getStatus() { return on ? "ON" : "OFF"; }
    public boolean isOn() { return on; }
    public void turnOn() { on = true; System.out.println(name + " is ON."); }
    public void turnOff() { on = false; System.out.println(name + " is OFF."); }
}
