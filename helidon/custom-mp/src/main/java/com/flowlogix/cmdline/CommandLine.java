package com.flowlogix.cmdline;

import jakarta.enterprise.context.Dependent;

import java.lang.ScopedValue.Carrier;
import java.util.NoSuchElementException;

@Dependent
public class CommandLine {
    private static final ScopedValue<String[]> ARGS = ScopedValue.newInstance();

    public static Carrier setArguments(String[] args) {
        return ScopedValue.where(ARGS, args);
    }

    public String[] getArguments() {
        return ARGS.orElseThrow(NoSuchElementException::new);
    }
}
