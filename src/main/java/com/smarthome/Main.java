package com.smarthome;

import com.smarthome.facade.SmartHomeFacade;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SmartHomeFacade smartHome = new SmartHomeFacade();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("====================================");
        System.out.println("       SMART HOME AUTOMATION");
        System.out.println("====================================");
        System.out.println("The simulated smart home is ready.\n");

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1" -> smartHome.turnLightOn();
                case "2" -> smartHome.turnLightOff();
                case "3" -> {
                    System.out.print("Enter temperature (16-30): ");
                    try {
                        smartHome.setTemperature(Integer.parseInt(scanner.nextLine().trim()));
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid whole number.");
                    }
                }
                case "4" -> smartHome.lockDoor();
                case "5" -> smartHome.unlockDoor();
                case "6" -> smartHome.simulateMotion();
                case "7" -> {
                    System.out.println("Select mode: 1) ECO  2) COMFORT  3) AWAY");
                    System.out.print("Mode: ");
                    String mode = scanner.nextLine().trim();
                    smartHome.activateMode(mode);
                }
                case "8" -> smartHome.showStatus();
                case "0" -> {
                    running = false;
                    System.out.println("Smart Home system is shutting down. Goodbye!");
                }
                default -> System.out.println("Unknown option. Please choose from the menu.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("----------- MAIN MENU -----------");
        System.out.println("1. Turn light ON");
        System.out.println("2. Turn light OFF");
        System.out.println("3. Set thermostat temperature");
        System.out.println("4. Lock the door");
        System.out.println("5. Unlock the door");
        System.out.println("6. Simulate motion detection");
        System.out.println("7. Activate automation mode");
        System.out.println("8. Show device status");
        System.out.println("0. Exit");
        System.out.println("---------------------------------");
    }
}
