# Smart Home Automation — Final Project

Java 17 console application demonstrating six coordinated design patterns in one smart-home simulation.

## Run in IntelliJ IDEA

Open the `SmartHomeAutomation` folder as a Maven project, set JDK 17 or newer, and run `com.smarthome.Main`.

## Terminal

```bash
mvn clean test
mvn exec:java
```

If Maven is unavailable, compile the Java sources manually:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.smarthome.Main
```

On Windows, IntelliJ IDEA is the easiest way to run the application and tests.

## Demonstration sequence

Enter `8` to show status, `1` to turn the light on, `5` to unlock the door, `3` then `25` to adjust temperature, `7` then `3` for AWAY mode, `8` to inspect, `9` to undo the entire mode, `8` to inspect restored values, `6` for motion, `10` then `35` for a high-temperature notification, and `0` to exit.

Restart the application and press `8` to confirm persisted device state.

## Design patterns

| Pattern | Implementation | Effect |
|---|---|---|
| Observer | MotionSensor, TemperatureSensor, SecurityMonitor, PhoneNotification | Sensor events notify subscribers |
| Command | CommandInvoker, TurnOnCommand, TurnOffCommand, SetTemperatureCommand, CompositeCommand | Unified reversible actions and mode-level Undo |
| Strategy | EcoMode, ComfortMode, AwayMode | Selectable home automation algorithms |
| Adapter | ZigbeeAdapter, LegacyZigbeeBulb | Legacy bulb controlled through SmartDevice |
| Simple Factory | DeviceFactory | Centralizes creation of different devices |
| Facade | SmartHomeFacade | Single public entry point used by the CLI |

The course's six-pattern minimum is met. DeviceFactory uses a Simple Factory rather than the GoF Factory Method subclass-based pattern; this is explicitly identified for defense accuracy.

## Team of two

Suggested ownership, to be confirmed with actual student contributions:

- Student A: Observer, Command, Strategy
- Student B: Adapter, Simple Factory, Facade

Each team member should be able to implement, explain, test, and demonstrate at least two patterns individually. Do not represent suggested allocation as a record of actual authorship.

## UML and report

- `docs/uml/class-diagram.puml`
- `docs/uml/sequence-diagram.puml`
- `docs/uml/class-diagram.svg`
- `docs/uml/sequence-diagram.svg`
- `docs/Final_Project_Report.pdf`

## Testing and limits

JUnit 5 tests cover factory, adapter, observer subscriptions, temperature validation, device Undo, group Undo, strategies, and state reloading. `data/smarthome.properties` is created after the first command and is not versioned. History exists only for the current process; persisted device state does not imply persisted Undo history. All sensors and the Zigbee integration are simulations without physical hardware or network protocols.
