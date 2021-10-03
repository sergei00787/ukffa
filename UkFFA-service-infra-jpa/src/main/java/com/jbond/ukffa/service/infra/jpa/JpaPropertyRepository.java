package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.PropertyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaPropertyRepository extends CrudRepository<Property, UUID>, PropertyRepository {
    @Override
    @Query("select p from Property p JOIN FETCH p.device")
    Iterable<Property> findAll();

    @Override
    Property save(Property property);
}
