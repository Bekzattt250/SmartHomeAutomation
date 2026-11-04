package com.smarthome.devices;

public interface SmartDevice {
    String getName();
    String getStatus();
    void turnOn();
    void turnOff();
}
