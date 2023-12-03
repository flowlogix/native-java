package com.flowlogix.example;

import jakarta.enterprise.context.Dependent;

@Dependent
public class HelloGreeter implements Greeter {
    @Override
    public String greet(String name) {
        return "Hello %s \uD83D\uDE00".formatted(name);
    }
}
