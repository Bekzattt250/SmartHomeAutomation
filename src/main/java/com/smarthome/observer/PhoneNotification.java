package com.smarthome.observer;

public class PhoneNotification implements Observer {
    @Override public void update(String event) {
        System.out.println("[PHONE NOTIFICATION] " + event);
    }
}
