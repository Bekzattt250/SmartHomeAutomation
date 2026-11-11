package com.smarthome.facade;
import com.smarthome.command.*;
import com.smarthome.devices.*;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.observer.*;
import com.smarthome.persistence.StateStore;
import com.smarthome.strategy.*;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
public class SmartHomeFacade {
    private final SmartDevice light;
    private final Thermostat thermostat;
    private final DoorLock doorLock;
    private final MotionSensor motionSensor;
    private final TemperatureSensor temperatureSensor;
    private final CommandInvoker commandInvoker;
    private final StateStore store;
    private final Map<String, AutomationStrategy> strategies = Map.of("1", new EcoMode(), "2", new ComfortMode(), "3", new AwayMode());
    public SmartHomeFacade() { this(Path.of("data", "smarthome.properties")); }
    public SmartHomeFacade(Path statePath) {
        DeviceFactory factory = new DeviceFactory();
        light = factory.createDevice("zigbee-light");
        thermostat = (Thermostat) factory.createDevice("thermostat");
        doorLock = (DoorLock) factory.createDevice("door-lock");
        motionSensor = new MotionSensor();
        temperatureSensor = new TemperatureSensor();
        commandInvoker = new CommandInvoker();
        store = new StateStore(statePath);
        motionSensor.addObserver(new SecurityMonitor());
        motionSensor.addObserver(new PhoneNotification());
        temperatureSensor.addObserver(new PhoneNotification());
        restoreState();
    }
    private void apply(Command command) { commandInvoker.executeCommand(command); saveState(); }
    public void turnLightOn() { apply(new TurnOnCommand(light)); }
    public void turnLightOff() { apply(new TurnOffCommand(light)); }
    public void setTemperature(int temperature) { apply(new SetTemperatureCommand(thermostat, temperature)); }
    public void lockDoor() { apply(new TurnOnCommand(doorLock)); }
    public void unlockDoor() { apply(new TurnOffCommand(doorLock)); }
    public void simulateMotion() { motionSensor.detectMotion(); }
    public void simulateTemperature(int temperature) { temperatureSensor.detectTemperature(temperature); }
    public boolean activateMode(String selection) {
        AutomationStrategy strategy = strategies.get(selection);
        if (strategy == null) { System.out.println("Invalid mode selection."); return false; }
        apply(new CompositeCommand(strategy.commands(light, thermostat, doorLock)));
        System.out.println(strategy.getName() + " mode activated.");
        return true;
    }
    public boolean undoLastCommand() {
        boolean undone = commandInvoker.undoLastCommand();
        if (undone) { saveState(); System.out.println("Last command was undone."); }
        else System.out.println("There is no command to undo.");
        return undone;
    }
    public int commandHistorySize() { return commandInvoker.historySize(); }
    public String lightStatus() { return light.getStatus(); }
    public String doorStatus() { return doorLock.getStatus(); }
    public int temperature() { return thermostat.getTemperature(); }
    public boolean thermostatEnabled() { return thermostat.isOn(); }
    public void showStatus() {
        System.out.println("----------- DEVICE STATUS -----------");
        System.out.println(light.getName() + ": " + light.getStatus());
        System.out.println(thermostat.getName() + ": " + thermostat.getStatus());
        System.out.println(doorLock.getName() + ": " + doorLock.getStatus());
        System.out.println("Undo history in current session: " + commandInvoker.historySize());
        System.out.println("-------------------------------------");
    }
    private void restoreState() {
        Properties state = store.load();
        if (state.isEmpty()) return;
        if (Boolean.parseBoolean(state.getProperty("light.on", "false"))) light.turnOn(); else light.turnOff();
        if (Boolean.parseBoolean(state.getProperty("door.locked", "true"))) doorLock.turnOn(); else doorLock.turnOff();
        try {
            int target = Integer.parseInt(state.getProperty("thermostat.temperature", "22"));
            boolean enabled = Boolean.parseBoolean(state.getProperty("thermostat.enabled", "true"));
            thermostat.restore(target, enabled);
        } catch (NumberFormatException e) { throw new IllegalStateException("Invalid saved thermostat temperature", e); }
    }
    private void saveState() {
        Properties state = new Properties();
        state.setProperty("light.on", String.valueOf(light.isOn()));
        state.setProperty("door.locked", String.valueOf(doorLock.isOn()));
        state.setProperty("thermostat.temperature", String.valueOf(thermostat.getTemperature()));
        state.setProperty("thermostat.enabled", String.valueOf(thermostat.isOn()));
        store.save(state);
    }
}
