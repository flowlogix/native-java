/*
 * Copyright (C) 2011-2025 Flow Logix, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
