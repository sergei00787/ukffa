package com.jbond.ukffa.service.core.repositories;

import com.jbond.ukffa.service.core.entity.Group;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository {

    Group save(Group group);

    Optional<Group> findById(UUID id);

    void deleteAll();
}
