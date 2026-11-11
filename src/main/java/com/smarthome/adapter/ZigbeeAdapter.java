package com.smarthome.adapter;
import com.smarthome.devices.SmartDevice;
import java.util.Objects;
public class ZigbeeAdapter implements SmartDevice {
    private final LegacyZigbeeBulb bulb;
    public ZigbeeAdapter(LegacyZigbeeBulb bulb) { this.bulb = Objects.requireNonNull(bulb); }
    public String getName() { return "Living room Zigbee light"; }
    public String getStatus() { return bulb.isPowered() ? "ON" : "OFF"; }
    public boolean isOn() { return bulb.isPowered(); }
    public void turnOn() { bulb.powerOn(); }
    public void turnOff() { bulb.powerOff(); }
}
