# Smart Home Automation System

A Java 17 console project demonstrating six design patterns for a simulated smart home.
No physical smart-home devices are required.

## Run in IntelliJ IDEA
1. Extract `SmartHomeAutomation.zip`.
2. In IntelliJ IDEA choose **File -> Open** and select the extracted `SmartHomeAutomation` folder (the folder containing `pom.xml`).
3. If IntelliJ asks, trust the project and load it as a Maven project.
4. Set Project SDK to Java 17 (File -> Project Structure -> Project SDK).
5. Open `src/main/java/com/smarthome/Main.java` and click the green Run icon beside `main`.

## Menu
- Turn the light on/off
- Change thermostat temperature (16-30 C)
- Lock/unlock the front door
- Simulate motion detection and receive observer notifications
- Activate ECO, COMFORT, or AWAY automation mode
- View current device status

## Design patterns used
1. **Observer** — `MotionSensor` notifies `SecurityMonitor` and `PhoneNotification` when motion is detected.
2. **Command** — `TurnOnCommand`, `TurnOffCommand`, and `CommandInvoker` encapsulate device actions and keep command history.
3. **Strategy** — `EcoMode`, `ComfortMode`, and `AwayMode` implement interchangeable automation algorithms.
4. **Adapter** — `ZigbeeAdapter` adapts the legacy `LegacyZigbeeBulb` API to the common `SmartDevice` interface.
5. **Factory** — `DeviceFactory` creates the required device objects.
6. **Facade** — `SmartHomeFacade` provides a simple interface to the subsystems and is used by `Main`.

## Important note
This is a working educational simulation. It does not connect to real Wi-Fi/Zigbee hardware, sensors, locks, or cloud services. The team should understand the code, test it, and adapt names/features to match its actual implementation before submitting or defending it.
