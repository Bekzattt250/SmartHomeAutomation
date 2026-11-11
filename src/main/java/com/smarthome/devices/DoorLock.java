package com.smarthome.devices;
public class DoorLock implements SmartDevice {
    private boolean locked = true;
    public String getName() { return "Front door lock"; }
    public String getStatus() { return locked ? "LOCKED" : "UNLOCKED"; }
    public boolean isOn() { return locked; }
    public void turnOn() { locked = true; System.out.println("Front door is LOCKED."); }
    public void turnOff() { locked = false; System.out.println("Front door is UNLOCKED."); }
}
