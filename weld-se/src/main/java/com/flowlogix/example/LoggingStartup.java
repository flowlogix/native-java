package com.flowlogix.example;

import lombok.SneakyThrows;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggingStartup {
    @SneakyThrows(IOException.class)
    public static Void init() {
        // must set before the Logger
        // loads logging.properties from the classpath
        try (InputStream is = LoggingStartup.class.getClassLoader().
                getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        }
        return null;
    }
}
