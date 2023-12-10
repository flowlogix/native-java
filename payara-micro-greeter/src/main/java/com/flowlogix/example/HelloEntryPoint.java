package com.flowlogix.example;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import com.flowlogix.examples.greeter.HelloPrinter;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;
import java.util.stream.Stream;
import static java.util.function.Predicate.not;

@ApplicationScoped
@Slf4j
@DataSourceDefinition(name = "java:app/hope", className = "${MPCONFIG=hope.className}",
        url = "${MPCONFIG=hope.url}",
        user = "${MPCONFIG=hope.db-username:}", password = "${MPCONFIG=hope.db-password:}",
        maxPoolSize = 32, minPoolSize = 8,
        properties = {
                "fish.payara.is-connection-validation-required = true",
                "fish.payara.connection-validation-method = auto-commit",
                "fish.payara.fail-all-connections = true"})
public class HelloEntryPoint {
    @Inject
    CommandLine cmdLine;
    @Inject
    HelloPrinter helloPrinter;

    @ActivateRequestContext
    void main(@Observes Startup startup) {
        String[] args = Optional.ofNullable(System.getProperty("greeting"))
                .map(str -> Stream.of(str.split("\s"))
                        .filter(not(String::isBlank))
                        .toArray(String[]::new))
                .orElse(new String[0]);
        log.debug("Number or arguments is {} and the array is {}", args.length, args);
        cmdLine.setArguments(args).run(helloPrinter::printHello);
    }
}
