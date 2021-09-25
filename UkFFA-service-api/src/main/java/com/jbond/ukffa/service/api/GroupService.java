package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    @Transactional
    public UUID createGroupItem() {
        Group group = new Group();

        groupRepository.save(group);
        log.info("Created a new groupItem <id: {}>", group.getId());
        return group.getId();
    }

    @Transactional
    public UUID createGroupItem(UUID id, UUID parentId, String name) {
        Group group = new Group(id, parentId, name);

        groupRepository.save(group);
        log.info("Created a new groupItem <id: {}>", group.getId());
        return group.getId();
    }

}
