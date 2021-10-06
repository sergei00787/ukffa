package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Group;
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
    public Device createDeviceItem(UUID id, int serial, String name, Group group, boolean allowed,
                                   String image, String imageColored, boolean isAreaEnabled) {
        Device device = new Device(id, serial, name, group, allowed,
                image, imageColored, isAreaEnabled, null);

        log.info("Created a new Device <id: {}>", device.getId());
        return device;
    }


    @Transactional
    public Device findById(UUID id) {
        return deviceRepository.findByIdOrFail(id);
    }

//    @Transactional
//    public void deleteByID(UUID id) {
//        deviceRepository.deleteByID(id);
//    }

}
