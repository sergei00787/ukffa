package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.Device;
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

    private final JpaDeviceRepository jpaDeviceRepository;


    @Transactional
    public List<Device> findAllDevice() {
        List<Device> devices = (List<Device>) jpaDeviceRepository.findAll();
        return devices;
    }

    @Transactional
    public Device findDeviceById(UUID id) {
//        return jpaDeviceRepository.findByIdOrFail(id);
        return jpaDeviceRepository.findById(id).orElse(null);
    }

}
