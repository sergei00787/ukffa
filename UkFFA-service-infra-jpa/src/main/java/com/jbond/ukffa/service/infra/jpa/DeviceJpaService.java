package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Property;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceJpaService {

    private final DeviceJpaRepository deviceJPARepository;
    private final PropertyJpaRepository propertyJpaRepository;


    @Transactional
    public List<Device> findAllDevice() {
        List<Device> devices = (List<Device>) jpaDeviceRepository.findAll();
        return devices;
    }

    @Transactional
    public Device findDeviceById(UUID id) {
        return deviceJPARepository.findById(id).orElse(null);
    }

    @Transactional
    public UUID createDevice(Device device) {
        deviceJPARepository.save(device);

        List<Property> properties = device.getProperties();
        if (properties != null && properties.size() > 0) {
            properties.forEach(prop -> {
                prop.setDevice(device);
                propertyJpaRepository.save(prop);
            });

        }

        log.info("Created a new DeviceItem <id: {}>", device.getId());
        return device.getId();
    }

    @Transactional
    public void deleteDeviceById(UUID id){
        log.info("Delete a Device <id: {}>", id);
        deviceJPARepository.deleteById(id);
    }

}
