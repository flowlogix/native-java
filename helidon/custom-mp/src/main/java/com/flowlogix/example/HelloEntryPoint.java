package com.flowlogix.example;

import com.flowlogix.cmdline.CommandLine;
import io.helidon.microprofile.cdi.Main;
import jakarta.enterprise.inject.spi.CDI;

public class HelloEntryPoint {
    public static void main(String[] args) {
        Main.main(args);
        CommandLine.setArguments(args).run(CDI.current().select(HelloPrinter.class).get()::printHello);
    }
}
