package com.smarthome;
import com.smarthome.facade.SmartHomeFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
class SmartHomeFacadeTest {
    @TempDir Path tempDir;
    private SmartHomeFacade home() { return new SmartHomeFacade(tempDir.resolve("state.properties")); }
    @Test void defaultState() {
        SmartHomeFacade home = home();
        assertEquals("OFF", home.lightStatus());
        assertEquals("LOCKED", home.doorStatus());
        assertEquals(22, home.temperature());
    }
    @Test void lightAndUndoRestorePreviousState() {
        SmartHomeFacade home = home();
        home.turnLightOn();
        home.turnLightOn();
        assertTrue(home.undoLastCommand());
        assertEquals("ON", home.lightStatus());
        assertTrue(home.undoLastCommand());
        assertEquals("OFF", home.lightStatus());
        assertFalse(home.undoLastCommand());
    }
    @Test void lockedDoorStaysLockedAfterUndoOfLock() {
        SmartHomeFacade home = home();
        home.lockDoor();
        assertTrue(home.undoLastCommand());
        assertEquals("LOCKED", home.doorStatus());
    }
    @Test void temperatureValidationDoesNotCreateHistory() {
        SmartHomeFacade home = home();
        assertThrows(IllegalArgumentException.class, () -> home.setTemperature(31));
        assertEquals(22, home.temperature());
        assertEquals(0, home.commandHistorySize());
    }
    @Test void temperatureUndo() {
        SmartHomeFacade home = home();
        home.setTemperature(27);
        assertEquals(27, home.temperature());
        home.undoLastCommand();
        assertEquals(22, home.temperature());
    }
    @Test void awayModeIsSingleUndoableOperation() {
        SmartHomeFacade home = home();
        home.turnLightOn();
        home.unlockDoor();
        home.setTemperature(25);
        int before = home.commandHistorySize();
        assertTrue(home.activateMode("3"));
        assertEquals(before + 1, home.commandHistorySize());
        assertEquals("OFF", home.lightStatus());
        assertEquals("LOCKED", home.doorStatus());
        assertEquals(17, home.temperature());
        home.undoLastCommand();
        assertEquals("ON", home.lightStatus());
        assertEquals("UNLOCKED", home.doorStatus());
        assertEquals(25, home.temperature());
    }
    @Test void invalidModeDoesNotChangeAnything() {
        SmartHomeFacade home = home();
        assertFalse(home.activateMode("invalid"));
        assertEquals(0, home.commandHistorySize());
    }
    @Test void allModesChangeTemperature() {
        SmartHomeFacade home = home();
        home.activateMode("1");
        assertEquals(19, home.temperature());
        home.activateMode("2");
        assertEquals(22, home.temperature());
        home.activateMode("3");
        assertEquals(17, home.temperature());
    }
    @Test void statePersistsAcrossNewFacadeInstances() {
        SmartHomeFacade original = home();
        original.turnLightOn();
        original.unlockDoor();
        original.setTemperature(26);
        SmartHomeFacade restored = home();
        assertEquals("ON", restored.lightStatus());
        assertEquals("UNLOCKED", restored.doorStatus());
        assertEquals(26, restored.temperature());
        assertEquals(0, restored.commandHistorySize());
    }
    @Test void sensorsDoNotAddUndoableCommands() {
        SmartHomeFacade home = home();
        home.simulateMotion();
        home.simulateTemperature(35);
        assertEquals(0, home.commandHistorySize());
        assertThrows(IllegalArgumentException.class, () -> home.simulateTemperature(81));
    }
}
