package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.repositories.GroupRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GroupJpaRepository extends CrudRepository<Group, UUID>, GroupRepository {
}


