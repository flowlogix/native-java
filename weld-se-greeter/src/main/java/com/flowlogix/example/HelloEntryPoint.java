package com.flowlogix.example;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import com.flowlogix.examples.greeter.HelloPrinter;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloEntryPoint {
    @SuppressWarnings("unused")
    private static final Void loggerInit = LoggingStartup.init();
    private static final SeContainer container = SeContainerInitializer.newInstance().initialize();

    public static void entry(String... args) {
        try (container) {
            CDI.current().select(CommandLine.class).get().setArguments(args)
                    .run(CDI.current().select(HelloPrinter.class).get()::printHello);
        }
    }
}
