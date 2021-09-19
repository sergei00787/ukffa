package com.jbond.ukffa.service.api;

import com.jbond.ukffa.service.core.entity.DeviceItem;
import com.jbond.ukffa.service.core.entity.GroupItem;
import com.jbond.ukffa.service.core.entity.PropType;
import com.jbond.ukffa.service.core.entity.Property;
import com.jbond.ukffa.service.core.repositories.DeviceItemRepository;
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
public class DeviceItemServiceTest {
    @Mock
    private DeviceItemRepository deviceItemRepository;

    private DeviceItemService deviceItemService;

    @BeforeEach
    void setUp(){
        deviceItemService = new DeviceItemService(deviceItemRepository);
    }

    @Test
    void createDeviceItem(){
        UUID deviceItemId = deviceItemService.createDeviceItem();
        assertNotNull(deviceItemId);

        GroupItem groupItem= new GroupItem(UUID.randomUUID(), null,"MainGroup");
        UUID deviceItemIdWithData = deviceItemService.createDeviceItem(UUID.randomUUID(),111,"device1",groupItem,
                true, "img", "imgColor",true);
        assertNotNull(deviceItemIdWithData);
    }

    @Test
    void addPropertyToDeviceItem(){
        GroupItem groupItem = new GroupItem(UUID.randomUUID(),null,"Group1");
        DeviceItem deviceItem = new DeviceItem(UUID.randomUUID(),123,"device1", groupItem,
                true, "img.png", null, new ArrayList<>(),false);
        doReturn(deviceItem).when(deviceItemRepository).findByIdOrFail(any());

        Property property1 = new Property(UUID.randomUUID(),deviceItem, false, PropType.Number, "AGID", "11");
        UUID deviceItemId = deviceItemService.addPropertyToDeviceItem(deviceItem.getId(), property1);

        assertNotNull(deviceItemId);
        DeviceItem deviceItem1 = deviceItemRepository.findByIdOrFail(deviceItemId);
    }



}
