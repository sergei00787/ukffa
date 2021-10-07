package com.jbond.ukffa.service.core.repositories;

import com.jbond.ukffa.service.core.entity.Device;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;


public interface DeviceRepository {

    Optional<Device> findById(UUID id);

    default Device findByIdOrFail(UUID id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id: <" + id + "> not found!"));
    }

    void deleteAll();

}
