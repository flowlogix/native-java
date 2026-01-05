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
package com.flowlogix.examples.greeter.birthday;

import com.flowlogix.examples.greeter.Greeter;
import com.flowlogix.examples.greeter.HelloGreeter;
import com.flowlogix.examples.greeter.entities.Sample;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import static jakarta.interceptor.Interceptor.Priority.APPLICATION;

@Dependent
@Alternative
@Priority(APPLICATION + 10)
public class BirthdayGreeter implements Greeter {
    @Inject
    EntityManager entityManager;
    @Inject
    HelloGreeter helloGreeter;

    @Override
    @Transactional
    public String greet(String name) {
        return entityManager.createNamedQuery("Sample.findAll", Sample.class)
                .setMaxResults(1).getResultStream()
                .findFirst().map(birthday -> {
                    birthday.setDateOfBirth(birthday.getDateOfBirth().plusDays(1));
                    return "%s Your birthday is %s".formatted(helloGreeter.greet(name),
                            birthday.getDateOfBirth());
                }).orElseGet(() -> helloGreeter.greet(name));
    }
}
