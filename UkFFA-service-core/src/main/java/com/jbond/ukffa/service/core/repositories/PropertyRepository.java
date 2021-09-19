package com.jbond.ukffa.service.core.repositories;

import com.jbond.ukffa.service.core.entity.Property;

import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository {

    Property save(Property property);

    Optional<Property> findById(UUID id);

    void deleteAll();
}
