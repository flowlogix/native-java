package com.flowlogix.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import java.time.Duration;

@ApplicationScoped
public class HelloPrinter {
    @Inject
    Greeter greeter;

    @SneakyThrows(InterruptedException.class)
    public void printHello(String name) {
        System.out.println(greeter.greet(name));
        Thread.sleep(Duration.ofSeconds(10));
    }
}
