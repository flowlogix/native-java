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
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class HelloEntryPoint {
    @SuppressWarnings({"unused", "checkstyle:ConstantName"})
    private static final Void loggerInit = LoggingStartup.init();
    @SuppressWarnings("checkstyle:ConstantName")
    private static final SeContainer container = SeContainerInitializer.newInstance().initialize();

    public static int entry(String... args) {
        try (container) {
            var command = container.select(CommandLine.class).get();
            var carrier = command.setArguments(args);
            carrier.run(container.select(HelloPrinter.class).get()::printHello);
            return carrier.get(command.getExitCode()).get();
        }
    }
}
