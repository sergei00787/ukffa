package com.jbond.ukffa.service.core.repositories;

import com.jbond.ukffa.service.core.entity.Device;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface DeviceRepository {
    Device save(Device device);

    Optional<Device> findById(UUID id);

    default Device findByIdOrFail(UUID id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id: <" + id + "> not found!"));
    }

    void deleteAll();

}
