package com.jbond.ukffa.infra.jpa;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.infra.jpa.DeviceJpaService;
import com.jbond.ukffa.service.infra.jpa.DeviceJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DeviceJpaServiceTest {

    @Mock
    private DeviceJpaRepository deviceJPARepository;

    @Autowired
    @InjectMocks
    private DeviceJpaService deviceJpaService;

    private Group group1;
    private Device device1;
    private Device device2;
    private List<Device> deviceList;

    @BeforeEach
    void setUp() {
        group1 = new Group(UUID.randomUUID(), null, "group1");
        device1 = new Device(UUID.fromString("71ac1110-26b1-11ec-9621-0242ac130002"), 1, "device1", group1, true, "img1", "imgColor", false, null);
        device2 = new Device(UUID.randomUUID(), 1, "device2", group1, false, "img2", "imgColor", true, null);
        deviceList = new ArrayList<>();
        deviceList.add(device1);
        deviceList.add(device2);

        //deviceJpaService = new DeviceJpaService(jpaDeviceRepository);

    }

    @Test
    public void testGetAllDevice(){
        deviceJPARepository.save(device1);

        when(deviceJPARepository.findAll()).thenReturn(deviceList);

        List<Device> devices = deviceJpaService.findAllDevice();
        System.out.println(devices);

        assertEquals(devices, deviceList);
        verify(deviceJPARepository, times(1)).save(device1);
        verify(deviceJPARepository, times(1)).findAll();

        assertNotNull(devices);
    }

    @Test
    void createDeviceShouldReturnDevice() {
        //stubbing
        when(deviceJPARepository.save(any())).thenReturn(device1);
        deviceJpaService.createDevice(device1);
        verify(deviceJPARepository,times(1)).save(any());
    }

    @Test
    public void givenIdThenShouldReturnDeviceOfThatId() {
        Mockito.when(deviceJPARepository.findById(UUID.fromString("71ac1110-26b1-11ec-9621-0242ac130002"))).thenReturn(Optional.ofNullable(device1));
        assertThat(deviceJpaService.findDeviceById(device1.getId())).isEqualTo(device1);
    }

}
