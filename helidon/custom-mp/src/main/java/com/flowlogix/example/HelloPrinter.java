package com.flowlogix.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HelloPrinter {
    @Inject
    Greeter greeter;

    public void printHello(String name) {
        System.out.println(greeter.greet(name));
    }
}
