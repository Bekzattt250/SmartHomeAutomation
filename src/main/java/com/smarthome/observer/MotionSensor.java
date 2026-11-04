package com.smarthome.observer;

import java.util.ArrayList;
import java.util.List;

public class MotionSensor implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    @Override public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) observers.add(observer);
    }
    @Override public void removeObserver(Observer observer) { observers.remove(observer); }
    @Override public void notifyObservers(String event) {
        for (Observer observer : List.copyOf(observers)) observer.update(event);
    }
    public void detectMotion() {
        System.out.println("Motion sensor: movement detected!");
        notifyObservers("Movement detected near the front door.");
    }
}
