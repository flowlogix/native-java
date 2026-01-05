/*
 * Copyright (C) 2011-2026 Flow Logix, Inc. All Rights Reserved.
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
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class HelloEntryPoint implements QuarkusApplication {
    @Inject
    HelloPrinter helloPrinter;
    @Inject
    CommandLine cmdLine;

    @Override
    public int run(String... args) {
        var carrier = cmdLine.setArguments(args);
        carrier.run(helloPrinter::printHello);
        return carrier.get(cmdLine.getExitCode()).get();
    }
}
