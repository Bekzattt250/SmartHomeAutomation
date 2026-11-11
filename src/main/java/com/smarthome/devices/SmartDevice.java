package com.smarthome.devices;
public interface SmartDevice {
    String getName();
    String getStatus();
    boolean isOn();
    void turnOn();
    void turnOff();
}
