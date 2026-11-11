package com.smarthome;
import com.smarthome.facade.SmartHomeFacade;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        SmartHomeFacade smartHome = new SmartHomeFacade();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            System.out.println("SMART HOME AUTOMATION");
            System.out.println("Simulated devices and sensors are ready.");
            while (running) {
                printMenu();
                System.out.print("Choose an option: ");
                if (!scanner.hasNextLine()) break;
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> smartHome.turnLightOn();
                        case "2" -> smartHome.turnLightOff();
                        case "3" -> {
                            System.out.print("Enter temperature (16-30): ");
                            smartHome.setTemperature(Integer.parseInt(scanner.nextLine().trim()));
                        }
                        case "4" -> smartHome.lockDoor();
                        case "5" -> smartHome.unlockDoor();
                        case "6" -> smartHome.simulateMotion();
                        case "7" -> {
                            System.out.println("1) ECO  2) COMFORT  3) AWAY");
                            System.out.print("Mode: ");
                            smartHome.activateMode(scanner.nextLine().trim());
                        }
                        case "8" -> smartHome.showStatus();
                        case "9" -> smartHome.undoLastCommand();
                        case "10" -> {
                            System.out.print("Simulate sensor reading (-40 to 80 C): ");
                            smartHome.simulateTemperature(Integer.parseInt(scanner.nextLine().trim()));
                        }
                        case "0" -> { running = false; System.out.println("Goodbye!"); }
                        default -> System.out.println("Unknown menu option.");
                    }
                } catch (NumberFormatException e) { System.out.println("Enter a valid whole number."); }
                catch (IllegalArgumentException | IllegalStateException e) { System.out.println("Error: " + e.getMessage()); }
                System.out.println();
            }
        }
    }
    private static void printMenu() {
        System.out.println("1. Light ON     2. Light OFF    3. Set temperature");
        System.out.println("4. Lock door    5. Unlock door  6. Simulate motion");
        System.out.println("7. Apply mode   8. Show status  9. Undo last command");
        System.out.println("10. Simulate temperature sensor    0. Exit");
    }
}
