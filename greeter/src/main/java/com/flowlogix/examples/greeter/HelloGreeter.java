package com.flowlogix.examples.greeter;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;
import static jakarta.interceptor.Interceptor.Priority.APPLICATION;

@Dependent
@Alternative
@Priority(APPLICATION + 1)
public class HelloGreeter implements Greeter {
    @Override
    public String greet(String name) {
        return "Hello %s \uD83D\uDE00".formatted(name);
    }
}
