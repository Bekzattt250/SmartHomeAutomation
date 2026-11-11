package com.smarthome;
import com.smarthome.adapter.*;
import com.smarthome.command.*;
import com.smarthome.devices.*;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.observer.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class PatternsTest {
    @Test void factoryCreatesMultipleDeviceKinds() {
        DeviceFactory factory = new DeviceFactory();
        assertInstanceOf(ZigbeeAdapter.class, factory.createDevice("zigbee-light"));
        assertInstanceOf(Light.class, factory.createDevice("light"));
        assertInstanceOf(Thermostat.class, factory.createDevice("thermostat"));
        assertInstanceOf(DoorLock.class, factory.createDevice("door-lock"));
        assertThrows(IllegalArgumentException.class, () -> factory.createDevice("unknown"));
    }
    @Test void adapterControlsLegacyBulb() {
        LegacyZigbeeBulb legacy = new LegacyZigbeeBulb();
        SmartDevice adapted = new ZigbeeAdapter(legacy);
        adapted.turnOn();
        assertTrue(legacy.isPowered());
        adapted.turnOff();
        assertFalse(legacy.isPowered());
    }
    @Test void observerNotifiesAndCanUnsubscribe() {
        MotionSensor sensor = new MotionSensor();
        List<String> events = new ArrayList<>();
        Observer listener = events::add;
        sensor.addObserver(listener);
        sensor.detectMotion();
        assertEquals(1, events.size());
        sensor.removeObserver(listener);
        sensor.detectMotion();
        assertEquals(1, events.size());
    }
    @Test void temperatureSensorNotifiesObservers() {
        TemperatureSensor sensor = new TemperatureSensor();
        List<String> events = new ArrayList<>();
        sensor.addObserver(events::add);
        sensor.detectTemperature(35);
        assertTrue(events.get(0).contains("High temperature"));
    }
    @Test void compositeCommandUndoIsReverseOrdered() {
        List<String> events = new ArrayList<>();
        Command a = new Command() { public void execute() { events.add("a"); } public void undo() { events.add("undo-a"); } };
        Command b = new Command() { public void execute() { events.add("b"); } public void undo() { events.add("undo-b"); } };
        CommandInvoker invoker = new CommandInvoker();
        invoker.executeCommand(new CompositeCommand(List.of(a, b)));
        assertTrue(invoker.undoLastCommand());
        assertEquals(List.of("a", "b", "undo-b", "undo-a"), events);
    }
}
