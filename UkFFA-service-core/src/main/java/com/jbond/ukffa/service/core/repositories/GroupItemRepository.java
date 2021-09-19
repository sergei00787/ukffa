package com.jbond.ukffa.service.core.repositories;

import com.jbond.ukffa.service.core.entity.GroupItem;

import java.util.Optional;
import java.util.UUID;

public interface GroupItemRepository {

    GroupItem save(GroupItem groupItem);

    Optional<GroupItem> findById(UUID id);

    void deleteAll();
}
