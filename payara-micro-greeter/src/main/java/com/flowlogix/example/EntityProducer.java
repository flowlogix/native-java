package com.flowlogix.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;

@RequestScoped
public class EntityProducer {
    @Getter(onMethod = @__(@Produces))
    @PersistenceContext(unitName = "jndi-hope")
    EntityManager entityManager;
}
