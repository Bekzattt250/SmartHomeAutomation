package com.smarthome.factory;

import com.smarthome.adapter.LegacyZigbeeBulb;
import com.smarthome.adapter.ZigbeeAdapter;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Light;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;

public class DeviceFactory {
    public SmartDevice createDevice(String type) {
        return switch (type.toLowerCase()) {
            case "light" -> new Light("Bedroom light");
            case "zigbee-light" -> new ZigbeeAdapter(new LegacyZigbeeBulb());
            case "thermostat" -> new Thermostat();
            case "door-lock" -> new DoorLock();
            default -> throw new IllegalArgumentException("Unknown device type: " + type);
        };
    }
}
