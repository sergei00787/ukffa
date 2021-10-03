package com.jbond.ukffa.infra.jpa;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.infra.jpa.DeviceJpaService;
import com.jbond.ukffa.service.infra.jpa.JpaDeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DeviceJpaServiceTest {

    @Mock
    private JpaDeviceRepository jpaDeviceRepository;

    private DeviceJpaService deviceJpaService;

    @BeforeEach
    void setUp() {
        //deviceJpaService = new DeviceJpaService(jpaDeviceRepository);
    }

    @Test
    public void testGetAllDevice(){
        List<Device> devices = deviceJpaService.findAllDevice();
        System.out.println(devices);
        assertNotNull(devices);
    }

}
