package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.entity.PropType;
import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    @Mock
    private DeviceRepository deviceRepository;

    private DeviceService deviceService;

    @BeforeEach
    void setUp(){
        deviceService = new DeviceService(deviceRepository);
    }

    @Test
    void createDeviceItem(){
        UUID deviceItemId = deviceService.createDeviceItem();
        assertNotNull(deviceItemId);

        Group group = new Group(UUID.randomUUID(), null,"MainGroup");
        UUID deviceItemIdWithData = deviceService.createDeviceItem(UUID.randomUUID(),111,"device1", group,
                true, "img", "imgColor",true);
        assertNotNull(deviceItemIdWithData);
    }

}
