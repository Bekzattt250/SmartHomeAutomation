package com.smarthome.adapter;
public class LegacyZigbeeBulb {
    private boolean powered;
    public void powerOn() { powered = true; System.out.println("Zigbee bulb: power ON."); }
    public void powerOff() { powered = false; System.out.println("Zigbee bulb: power OFF."); }
    public boolean isPowered() { return powered; }
}
