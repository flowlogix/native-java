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
package com.flowlogix.bootstrap.cmdline;

import jakarta.enterprise.context.ApplicationScoped;
import java.lang.ScopedValue.Carrier;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class CommandLine {
    private final ScopedValue<String[]> arguments = ScopedValue.newInstance();
    private final ScopedValue<AtomicInteger> exitCode = ScopedValue.newInstance();

    public Carrier setArguments(String[] args) {
        return ScopedValue.where(arguments, args)
                .where(exitCode, new AtomicInteger());
    }

    public String[] getArguments() {
        return arguments.orElseThrow(NoSuchElementException::new);
    }

    public ScopedValue<AtomicInteger> getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode.orElseThrow(NoSuchElementException::new).set(exitCode);
    }
}
