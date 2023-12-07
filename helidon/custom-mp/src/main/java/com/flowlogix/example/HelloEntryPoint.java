package com.flowlogix.example;

import com.flowlogix.cmdline.CommandLine;
import com.flowlogix.weld.graalvm.DynamicWeld;
import io.helidon.microprofile.cdi.Main;
import jakarta.enterprise.inject.spi.CDI;

public class HelloEntryPoint {
    public static void main(String[] args) {
        DynamicWeld.initialize();
        Main.main(args);
        CommandLine.setArguments(args).run(CDI.current().select(HelloPrinter.class).get()::printHello);
    }
}
