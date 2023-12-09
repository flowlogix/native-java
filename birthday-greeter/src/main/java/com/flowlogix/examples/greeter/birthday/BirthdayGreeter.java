package com.flowlogix.examples.greeter.birthday;

import com.flowlogix.examples.greeter.Greeter;
import com.flowlogix.examples.greeter.HelloGreeter;
import com.flowlogix.greeter.entities.Sample;
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
        Sample birthday = entityManager.createNamedQuery("Sample.findAll", Sample.class)
                .setMaxResults(1).getResultStream()
                .findFirst().get();
        birthday.setDateOfBirth(birthday.getDateOfBirth().plusDays(1));
        return "%s Your birthday is %s".formatted(helloGreeter.greet(name), birthday.getDateOfBirth());
    }
}
