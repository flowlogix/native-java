package com.flowlogix.example;

import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;

public class HelloEntryPoint {
    public static void printHello(String name) {
        try (var _ = SeContainerInitializer.newInstance()
                .addBeanClasses(HelloPrinter.class, HelloGreeter.class)
                .disableDiscovery()
                .initialize()) {
//        try (var _ = SeContainerInitializer.newInstance().initialize()) {
            var helloPrinter = CDI.current().select(HelloPrinter.class).get();
            helloPrinter.printHello(name);
        }
    }

    public static void main(String... args) {
        printHello(args[0]);
    }

    public static void main() {
        main(new String[0]);
    }
}
