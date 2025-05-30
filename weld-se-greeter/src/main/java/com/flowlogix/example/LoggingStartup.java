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

import lombok.SneakyThrows;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class LoggingStartup {
    @SneakyThrows(IOException.class)
    public static Void init() {
        // must set before the Logger
        // loads logging.properties from the classpath
        try (InputStream is = LoggingStartup.class.getClassLoader().
                getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        }
        return null;
    }
}
