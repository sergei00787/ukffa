package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.GroupItem;
import com.jbond.ukffa.service.core.repositories.GroupItemRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaGroupItemRepository extends CrudRepository<GroupItem, UUID>, GroupItemRepository {
}


