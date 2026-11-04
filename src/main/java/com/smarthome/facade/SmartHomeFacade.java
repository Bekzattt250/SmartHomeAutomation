package com.smarthome.facade;

import com.smarthome.command.CommandInvoker;
import com.smarthome.command.TurnOffCommand;
import com.smarthome.command.TurnOnCommand;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.SmartDevice;
import com.smarthome.devices.Thermostat;
import com.smarthome.factory.DeviceFactory;
import com.smarthome.observer.MotionSensor;
import com.smarthome.observer.PhoneNotification;
import com.smarthome.observer.SecurityMonitor;
import com.smarthome.strategy.AwayMode;
import com.smarthome.strategy.AutomationStrategy;
import com.smarthome.strategy.ComfortMode;
import com.smarthome.strategy.EcoMode;

public class SmartHomeFacade {
    private final SmartDevice light;
    private final Thermostat thermostat;
    private final DoorLock doorLock;
    private final MotionSensor motionSensor;
    private final CommandInvoker commandInvoker;

    public SmartHomeFacade() {
        DeviceFactory factory = new DeviceFactory();
        this.light = factory.createDevice("zigbee-light"); // Factory + Adapter
        this.thermostat = (Thermostat) factory.createDevice("thermostat");
        this.doorLock = (DoorLock) factory.createDevice("door-lock");
        this.motionSensor = new MotionSensor();
        this.commandInvoker = new CommandInvoker();
        motionSensor.addObserver(new SecurityMonitor());
        motionSensor.addObserver(new PhoneNotification());
    }

    public void turnLightOn() { commandInvoker.executeCommand(new TurnOnCommand(light)); }
    public void turnLightOff() { commandInvoker.executeCommand(new TurnOffCommand(light)); }
    public void setTemperature(int temperature) { thermostat.setTemperature(temperature); }
    public void lockDoor() { commandInvoker.executeCommand(new TurnOnCommand(doorLock)); }
    public void unlockDoor() { commandInvoker.executeCommand(new TurnOffCommand(doorLock)); }
    public void simulateMotion() { motionSensor.detectMotion(); }

    public void activateMode(String selection) {
        AutomationStrategy strategy = switch (selection) {
            case "1" -> new EcoMode();
            case "2" -> new ComfortMode();
            case "3" -> new AwayMode();
            default -> null;
        };
        if (strategy == null) {
            System.out.println("Invalid mode selection.");
            return;
        }
        System.out.println("Applying " + strategy.getName() + " mode...");
        strategy.apply(light, thermostat, doorLock);
    }

    public void showStatus() {
        System.out.println("----------- DEVICE STATUS -----------");
        System.out.println(light.getName() + ": " + light.getStatus());
        System.out.println(thermostat.getName() + ": " + thermostat.getStatus());
        System.out.println(doorLock.getName() + ": " + doorLock.getStatus());
        System.out.println("-------------------------------------");
    }
}
