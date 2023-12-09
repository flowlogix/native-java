package com.flowlogix.bootstrap.cmdline;

import jakarta.enterprise.context.ApplicationScoped;
import java.lang.ScopedValue.Carrier;
import java.util.NoSuchElementException;

@ApplicationScoped
public class CommandLine {
    private final ScopedValue<String[]> arguments = ScopedValue.newInstance();

    public Carrier setArguments(String[] args) {
        return ScopedValue.where(arguments, args);
    }

    public String[] getArguments() {
        return arguments.orElseThrow(NoSuchElementException::new);
    }
}
