package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupJpaService {
    private final JpaGroupRepository jpaGroupRepository;

    @Transactional
    public List<Group> getAllGroups(){
        return  (List<Group>) jpaGroupRepository.findAll();
    }
}
