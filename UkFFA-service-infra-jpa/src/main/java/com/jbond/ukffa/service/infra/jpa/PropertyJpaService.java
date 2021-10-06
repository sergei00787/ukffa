package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Property;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PropertyJpaService {

    private final PropertyJpaRepository propertyJpaRepository;

    public List<Property> getAllProperties(){
        return (List<Property>) propertyJpaRepository.findAll();
    }

}
