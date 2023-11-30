package com.flowlogix.example;

import jakarta.enterprise.inject.spi.CDI;

public class HelloEntryPoint {
    static public void printHello(String name) {
        var helloPrinter = CDI.current().select(HelloPrinter.class).get();
        helloPrinter.printHello(name);
    }

    public static void main(String[] args) {
        io.helidon.Main.main(args);
        printHello(args[0]);
        System.exit(0);
    }
}
