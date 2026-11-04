package com.smarthome.observer;

public class SecurityMonitor implements Observer {
    @Override public void update(String event) {
        System.out.println("[SECURITY ALERT] " + event);
    }
}
