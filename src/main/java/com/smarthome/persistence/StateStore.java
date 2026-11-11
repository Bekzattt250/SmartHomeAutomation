package com.smarthome.persistence;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
public class StateStore {
    private final Path path;
    public StateStore(Path path) { this.path = path; }
    public Properties load() {
        Properties properties = new Properties();
        if (!Files.exists(path)) return properties;
        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
            return properties;
        } catch (IOException e) { throw new IllegalStateException("Cannot load smart home state: " + path, e); }
    }
    public void save(Properties properties) {
        try {
            Path absolute = path.toAbsolutePath();
            Files.createDirectories(absolute.getParent());
            Path temp = Files.createTempFile(absolute.getParent(), "smarthome-", ".tmp");
            try {
                try (OutputStream output = Files.newOutputStream(temp)) { properties.store(output, "Smart Home Automation"); }
                try { Files.move(temp, absolute, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE); }
                catch (java.nio.file.AtomicMoveNotSupportedException e) { Files.move(temp, absolute, StandardCopyOption.REPLACE_EXISTING); }
            } finally { Files.deleteIfExists(temp); }
        } catch (IOException e) { throw new IllegalStateException("Cannot save smart home state: " + path, e); }
    }
}
