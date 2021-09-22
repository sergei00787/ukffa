package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.PropertyRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaPropertyRepository extends CrudRepository<Property, UUID>, PropertyRepository {
}
