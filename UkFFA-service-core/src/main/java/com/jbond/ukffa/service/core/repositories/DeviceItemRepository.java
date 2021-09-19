package com.jbond.ukffa.service.core.repositories;

import com.jbond.ukffa.service.core.entity.DeviceItem;

import java.util.Optional;
import java.util.UUID;


public interface DeviceItemRepository {
    DeviceItem save(DeviceItem deviceItem);

    Optional<DeviceItem> findById(UUID id);

    default DeviceItem findByIdOrFail(UUID id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id: <" + id + "> not found!"));
    }

    void deleteAll();

}
