package com.smarthome.observer;
import java.util.ArrayList;
import java.util.List;
public class TemperatureSensor implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer) { if (observer != null && !observers.contains(observer)) observers.add(observer); }
    public void removeObserver(Observer observer) { observers.remove(observer); }
    public void notifyObservers(String event) { for (Observer observer : List.copyOf(observers)) observer.update(event); }
    public void detectTemperature(int temperature) {
        if (temperature < -40 || temperature > 80) throw new IllegalArgumentException("Sensor temperature must be between -40 and 80 C.");
        System.out.println("Temperature sensor reading: " + temperature + " C.");
        notifyObservers(temperature > 30 ? "High temperature detected: " + temperature + " C." : "Temperature reading: " + temperature + " C.");
    }
}
