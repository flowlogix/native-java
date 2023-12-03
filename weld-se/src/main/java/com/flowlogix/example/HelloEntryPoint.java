package com.flowlogix.example;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;

public class HelloEntryPoint {
    private static final Void loggerInit = LoggingStartup.init();
    private static final SeContainer container = SeContainerInitializer.newInstance().initialize();

    public static void printHello(String name) {
        var helloPrinter = CDI.current().select(HelloPrinter.class).get();
        helloPrinter.printHello(name);
    }

    public static void entry(String name) {
        try (container) {
            printHello(name);
        }
    }
}
