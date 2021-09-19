package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.DeviceItem;
import com.jbond.ukffa.service.core.repositories.DeviceItemRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaDeviceItemRepository extends CrudRepository<DeviceItem, UUID>, DeviceItemRepository {
}
