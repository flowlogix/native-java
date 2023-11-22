package com.flowlogix.example;

import lombok.SneakyThrows;
import java.time.Duration;

public class HelloPrinter {
    @SneakyThrows(InterruptedException.class)
    public void printHello(String name) {
        System.out.println(name);
        Thread.sleep(Duration.ofSeconds(10));
    }
}
