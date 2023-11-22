package com.flowlogix.example;

import jakarta.enterprise.inject.spi.CDI;
import org.jboss.weld.environment.se.Weld;

public class HelloEntryPoint {
    public static void printHello(String name) {
        try (var _ = new Weld().initialize()) {
            var helloPrinter = CDI.current().select(HelloPrinter.class).get();
            helloPrinter.printHello(name);
        }
    }

    public static void main(String... argv) {
        printHello("Anya");
    }

    public static void main() {
        main(new String[0]);
    }
}
