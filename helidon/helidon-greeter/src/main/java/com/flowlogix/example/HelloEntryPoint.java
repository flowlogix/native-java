package com.flowlogix.example;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import com.flowlogix.examples.greeter.HelloPrinter;
import com.flowlogix.weld.graalvm.DynamicWeld;
import io.helidon.microprofile.cdi.Main;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import org.jboss.weld.context.activator.ActivateRequestContext;

@ApplicationScoped
public class HelloEntryPoint {
    @Inject
    CommandLine cmdLine;
    @Inject HelloPrinter helloPrinter;

    public static void main(String[] args) {
        DynamicWeld.initialize();
        Main.main(args);
        CDI.current().select(HelloEntryPoint.class).get().entryPoint(args);
    }

    @ActivateRequestContext
    void entryPoint(String[] args) {
        cmdLine.setArguments(args).run(helloPrinter::printHello);
    }
}
