package com.flowlogix.example;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import com.flowlogix.examples.greeter.HelloPrinter;
import com.flowlogix.weld.graalvm.DynamicWeld;
import io.helidon.microprofile.cdi.Main;
import jakarta.enterprise.inject.spi.CDI;

public class HelloEntryPoint {
    public static void main(String[] args) {
        DynamicWeld.initialize();
        Main.main(args);
        CDI.current().select(CommandLine.class).get().setArguments(args)
                .run(CDI.current().select(HelloPrinter.class).get()::printHello);
    }
}
