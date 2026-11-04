package com.smarthome.adapter;

import com.smarthome.devices.SmartDevice;

public class ZigbeeAdapter implements SmartDevice {
    private final LegacyZigbeeBulb bulb;
    public ZigbeeAdapter(LegacyZigbeeBulb bulb) { this.bulb = bulb; }
    @Override public String getName() { return "Living room Zigbee light"; }
    @Override public String getStatus() { return bulb.isPowered() ? "ON" : "OFF"; }
    @Override public void turnOn() { bulb.powerOn(); }
    @Override public void turnOff() { bulb.powerOff(); }
}
