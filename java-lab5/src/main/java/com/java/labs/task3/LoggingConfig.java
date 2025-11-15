package com.java.labs.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;

public final class LoggingConfig {

    private static final Logger ROOT_LOGGER = Logger.getLogger("com.java.labs");
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = LOG_DIR + "/app.log";

    static {
        setup();
    }

    private LoggingConfig() { }

    private static void setup() {
        try {
            ROOT_LOGGER.setUseParentHandlers(false);
            ROOT_LOGGER.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());
            ROOT_LOGGER.addHandler(consoleHandler);

            Files.createDirectories(Paths.get(LOG_DIR));
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            ROOT_LOGGER.addHandler(fileHandler);

        } catch (IOException e) {
            System.err.println("Failed to initialize file logging: " + e.getMessage());
            Logger fallback = Logger.getLogger(LoggingConfig.class.getName());
            fallback.log(Level.SEVERE, "Failed to initialize file logging", e);
        }
    }

    public static Logger getLogger(Class<?> cls) {
        return Logger.getLogger("com.java.labs." + cls.getSimpleName());
    }
}
