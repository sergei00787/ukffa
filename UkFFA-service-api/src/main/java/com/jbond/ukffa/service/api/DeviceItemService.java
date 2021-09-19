package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.DeviceItem;
import com.jbond.ukffa.service.core.entity.GroupItem;
import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.DeviceItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceItemService {

    private DeviceItemRepository deviceItemRepository;

    public DeviceItemService(DeviceItemRepository deviceItemRepo) {
        this.deviceItemRepository = deviceItemRepo;
    }

    @Transactional
    public UUID createDeviceItem() {
        DeviceItem deviceItem = new DeviceItem();

        // deviceItem.setId(UUID.randomUUID());//TEST ONLY

        deviceItemRepository.save(deviceItem);
        log.info("Created a new DeviceItem <id: {}>", deviceItem.getId());
        return deviceItem.getId();
    }

    @Transactional
    public UUID createDeviceItem(UUID id, int serial, String name, GroupItem groupItem, boolean allowed,
                                 String image, String imageColored, boolean isAreaEnabled) {
        DeviceItem deviceItem = new DeviceItem(id, serial, name, groupItem, allowed,
                image, imageColored, null, isAreaEnabled);

        deviceItemRepository.save(deviceItem);
        log.info("Created a new DeviceItem <id: {}>", deviceItem.getId());
        return deviceItem.getId();
    }


    @Transactional
    public UUID addPropertyToDeviceItem(UUID deviceId, Property property) {
        DeviceItem deviceItem = deviceItemRepository.findByIdOrFail(deviceId);
        deviceItem.getProperties().add(property);
        log.info("Added a new property <{}> to the device <id: {}>",
                deviceItem, deviceItem.getId());
        return deviceItem.getId();
    }

    @Transactional
    public UUID removePropertyFromDeviceItem(UUID deviceId, Property property) {
        DeviceItem deviceItem = deviceItemRepository.findByIdOrFail(deviceId);
        deviceItem.getProperties().remove(property);
        log.info("Remove property <{}> from the device <id: {}>",
                deviceItem, deviceItem.getId());
        return deviceItem.getId();
    }

}
