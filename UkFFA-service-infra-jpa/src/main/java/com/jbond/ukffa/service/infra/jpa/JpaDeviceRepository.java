package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.repositories.DeviceRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaDeviceRepository extends CrudRepository<Device, UUID>, DeviceRepository {
}
