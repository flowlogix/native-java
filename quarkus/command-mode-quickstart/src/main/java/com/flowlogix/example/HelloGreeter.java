package com.flowlogix.example;

import com.flowlogix.example.data.entities.Sample;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Dependent
public class HelloGreeter implements Greeter {
    @Inject
    EntityManager em;

    @Override
    @Transactional
    public String greet(String name) {
        Sample birthday = em.createNamedQuery("Sample.findAll", Sample.class).setMaxResults(1).getResultStream()
                .findFirst().get();
        return "Hello %s! - your birthday is %s".formatted(name, birthday.getDateOfBirth());
    }
}
