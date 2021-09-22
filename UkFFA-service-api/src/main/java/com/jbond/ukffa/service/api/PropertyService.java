package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.DeviceItem;
import com.jbond.ukffa.service.core.entity.GroupItem;
import com.jbond.ukffa.service.core.entity.PropType;
import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.DeviceItemRepository;
import com.jbond.ukffa.service.core.repositories.GroupItemRepository;
import com.jbond.ukffa.service.core.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final DeviceItemRepository deviceItemRepository;

    @Transactional
    public UUID createProperty() {
        Property property = new Property();

        propertyRepository.save(property);
        log.info("Created a new property <id: {}>", property.getId());
        return property.getId();
    }

    @Transactional
    public UUID createProperty(UUID id, UUID deviceItemId, boolean inherited, PropType propType, String name, String value) {
        if (deviceItemRepository.findById(deviceItemId).isPresent()) {
            DeviceItem deviceItem = deviceItemRepository.findById(deviceItemId).get();
            Property property = new Property(id, deviceItem, inherited, propType, name, value);
            propertyRepository.save(property);
            log.info("Created a new property <id: {}>", property.getId());
            return property.getId();
        } else {
            throw new RuntimeException("Error in create property function, device with id = " + deviceItemId + "not found");
        }
    }

}
