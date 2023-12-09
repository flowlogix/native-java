package com.flowlogix.examples.greeter;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.stream.Collectors;

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
            log.info(greeter.greet(Arrays.stream(cmdLine.getArguments())
                    .collect(Collectors.joining(" "))));
        }
    }
}
