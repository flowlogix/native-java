package com.flowlogix.example;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import com.flowlogix.examples.greeter.HelloPrinter;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class HelloEntryPoint implements QuarkusApplication {
    @Inject
    HelloPrinter helloPrinter;
    @Inject
    CommandLine cmdLine;

    @Override
    public int run(String... args) {
        cmdLine.setArguments(args).run(helloPrinter::printHello);
        return 0;
    }
}
