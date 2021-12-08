package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceServiceTest {
    @Mock
    private DeviceRepository deviceRepository;

    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        deviceService = new DeviceService(deviceRepository);
    }
/*
    @Test
    void createDeviceItem() {
        UUID deviceItemId = deviceService.createDeviceItem();
        assertNotNull(deviceItemId);

        Group group = new Group(UUID.randomUUID(), null, "MainGroup");
        UUID deviceItemIdWithData = deviceService.createDeviceItem(UUID.randomUUID(), 111, "device1", group,
                true, "img", "imgColor", true);
        assertNotNull(deviceItemIdWithData);
    }

    @Test
    void findDeviceById() {
        Group group = new Group(UUID.randomUUID(), null, "MainGroup");
        Device device = new Device(UUID.randomUUID(), 111, "device1", group,
                true, "img", "imgColor", true, null);

        UUID deviceItemIdWithData = deviceService.createDeviceItem(device.getId(), device.getSerial(),
                device.getName(), device.getGroup(), device.isAllowed(), device.getImage(), device.getImageColored(), device.isAreaEnabled());

        Device deviceFromDB = deviceService.findById(deviceItemIdWithData);


        assertAll(
                () -> assertEquals(deviceFromDB.getSerial(), device.getSerial()),
                () -> assertEquals(deviceFromDB.getGroup().getName(), device.getGroup().getName()),
                () -> assertEquals(deviceFromDB.getName(), device.getName()),
                () -> assertEquals(deviceFromDB.isAllowed(), device.isAllowed()),
                () -> assertEquals(deviceFromDB.getImage(), device.getImage()),
                () -> assertEquals(deviceFromDB.getImageColored(), device.getImageColored()),
                () -> assertEquals(deviceFromDB.isAreaEnabled(), device.isAreaEnabled())
        );

    }
    */

}
