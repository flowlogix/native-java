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
package com.flowlogix.examples.greeter;

import com.flowlogix.bootstrap.cmdline.CommandLine;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class HelloPrinter {
    @Inject
    Greeter greeter;
    @Inject
    CommandLine cmdLine;

    public void printHello() {
        if (cmdLine.getArguments().length == 0) {
            log.warn("No one to greet \uD83D\uDE00");
        } else {
            log.info(greeter.greet(Arrays.stream(cmdLine.getArguments())
                    .collect(Collectors.joining(" "))));
        }
    }
}
