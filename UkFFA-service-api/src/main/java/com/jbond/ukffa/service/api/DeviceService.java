package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Transactional
    public UUID createDeviceItem() {
        Device device = new Device();

        // deviceItem.setId(UUID.randomUUID());//TEST ONLY

        deviceRepository.save(device);
        log.info("Created a new DeviceItem <id: {}>", device.getId());
        return device.getId();
    }

    @Transactional
    public UUID createDeviceItem(UUID id, int serial, String name, Group group, boolean allowed,
                                 String image, String imageColored, boolean isAreaEnabled) {
        Device device = new Device(id, serial, name, group, allowed,
                image, imageColored, null, isAreaEnabled);

        deviceRepository.save(device);
        log.info("Created a new DeviceItem <id: {}>", device.getId());
        return device.getId();
    }


    @Transactional
    public UUID addPropertyToDeviceItem(UUID deviceId, Property property) {
        Device device = deviceRepository.findByIdOrFail(deviceId);
        device.getProperties().add(property);
        log.info("Added a new property <{}> to the device <id: {}>",
                device, device.getId());
        return device.getId();
    }

    @Transactional
    public UUID removePropertyFromDeviceItem(UUID deviceId, Property property) {
        Device device = deviceRepository.findByIdOrFail(deviceId);
        device.getProperties().remove(property);
        log.info("Remove property <{}> from the device <id: {}>",
                device, device.getId());
        return device.getId();
    }

}