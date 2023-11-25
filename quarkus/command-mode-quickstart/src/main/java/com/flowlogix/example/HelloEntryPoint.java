package com.flowlogix.example;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class HelloEntryPoint implements QuarkusApplication {
    @Inject
    HelloPrinter helloPrinter;

    @Override
    public int run(String... args) throws Exception {
        printHello(args[0]);
        return 0;
    }

    public void printHello(String name) {
//        var helloPrinter = CDI.current().select(HelloPrinter.class).get();
        helloPrinter.printHello(name);
    }
}
