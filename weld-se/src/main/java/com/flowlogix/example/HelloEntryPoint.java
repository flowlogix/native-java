package com.flowlogix.example;

import com.flowlogix.weld.HelidonProxyServices;
import jakarta.enterprise.inject.spi.CDI;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class HelloEntryPoint {
    private static final WeldContainer container;

    static {
        container = new Weld()
                .addServices(new HelidonProxyServices())
                .initialize();
    }

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
