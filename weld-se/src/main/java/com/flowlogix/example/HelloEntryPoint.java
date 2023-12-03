package com.flowlogix.example;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloEntryPoint {
    private static final Void loggerInit = LoggingStartup.init();
    private static final SeContainer container = SeContainerInitializer.newInstance().initialize();

    public static void printHello(String name) {
        var helloPrinter = CDI.current().select(HelloPrinter.class).get();
        helloPrinter.printHello(name);
    }

    public static void entry(String... args) {
        try (container) {
            if (args.length == 0) {
                log.warn("No one to greet \uD83D\uDE00");
            } else {
                String name = args[0];
                printHello(name);
            }
        }
    }
}
