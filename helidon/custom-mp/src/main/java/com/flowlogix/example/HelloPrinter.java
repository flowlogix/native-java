package com.flowlogix.example;

import com.flowlogix.cmdline.CommandLine;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class HelloPrinter {
    @Inject
    Greeter greeter;
    @Inject
    CommandLine cmdLine;

    public void printHello() {
        if (cmdLine.getArguments().length == 0) {
            log.warn("No one to greet \uD83D\uDE00");
        } else {
            log.info(greeter.greet(cmdLine.getArguments()[0]));
        }
    }
}
