package com.jbond.ukffa.service.infra.api.rest;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.infra.jpa.JpaDeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceJpaService {
    @Autowired
    private final JpaDeviceRepository jpaDeviceRepository;

    public List<Device> findAllDevice(){
        return (List<Device>) jpaDeviceRepository.findAll();
    }

}
