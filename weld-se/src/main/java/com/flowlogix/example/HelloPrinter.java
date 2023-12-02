package com.flowlogix.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class HelloPrinter {
    @Inject
    Greeter greeter;

    public void printHello(String name) {
        log.info(greeter.greet(name));
    }
}
