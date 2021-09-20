package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.GroupItem;
import com.jbond.ukffa.service.core.repositories.GroupItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupItemService {
    private final GroupItemRepository groupItemRepository;

    @Transactional
    public UUID createGroupItem() {
        GroupItem groupItem = new GroupItem();

        groupItemRepository.save(groupItem);
        log.info("Created a new groupItem <id: {}>", groupItem.getId());
        return groupItem.getId();
    }

    @Transactional
    public UUID createGroupItem(UUID id, UUID parentId, String name) {
        GroupItem groupItem = new GroupItem(id, parentId, name);

        groupItemRepository.save(groupItem);
        log.info("Created a new groupItem <id: {}>", groupItem.getId());
        return groupItem.getId();
    }

}
