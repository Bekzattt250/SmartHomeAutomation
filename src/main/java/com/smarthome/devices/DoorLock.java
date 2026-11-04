package com.smarthome.devices;

public class DoorLock implements SmartDevice {
    private boolean locked = true;
    @Override public String getName() { return "Front door lock"; }
    @Override public String getStatus() { return locked ? "LOCKED" : "UNLOCKED"; }
    // For this device, ON means the security lock is engaged.
    @Override public void turnOn() { locked = true; System.out.println("Front door is LOCKED."); }
    @Override public void turnOff() { locked = false; System.out.println("Front door is UNLOCKED."); }
}
