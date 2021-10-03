package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.DeviceRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaDeviceRepository extends CrudRepository<Device, UUID>, DeviceRepository {
    @Override
    //@Query("select d from Device d JOIN FETCH d.group")
    Iterable<Device> findAll();

    @Override
    Optional<Device> findById(UUID uuid);

    default Device findByIdOrFail(UUID id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id: <" + id + "> not found!"));
    }

    @Override
    Device save(Device device);


}
