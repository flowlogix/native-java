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

@ApplicationScoped
public class HelloEntryPoint {
    @Inject
    CommandLine cmdLine;
    @Inject
    HelloPrinter helloPrinter;

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
