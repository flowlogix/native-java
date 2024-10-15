/*
 * Copyright (C) 2011-2024 Flow Logix, Inc. All Rights Reserved.
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
import com.flowlogix.weld.graalvm.DynamicWeld;
import io.helidon.microprofile.cdi.Main;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import org.jboss.weld.context.activator.ActivateRequestContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class HelloEntryPoint {
    @Inject
    CommandLine cmdLine;
    @Inject
    HelloPrinter helloPrinter;

    public static void main(String[] args) {
        var logger = Logger.getLogger("io.helidon.Main");
        logger.setLevel(Level.WARNING);
        logger.info("Starting the application");
        DynamicWeld.initialize();
        Main.main(args);
        System.exit(CDI.current().select(HelloEntryPoint.class).get().entryPoint(args));
    }

    @ActivateRequestContext
    int entryPoint(String[] args) {
        var carrier = cmdLine.setArguments(args);
        carrier.run(helloPrinter::printHello);
        return carrier.get(cmdLine.getExitCode()).get();
    }
}
