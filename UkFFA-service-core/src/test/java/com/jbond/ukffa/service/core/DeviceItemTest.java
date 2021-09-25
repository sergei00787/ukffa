package com.jbond.ukffa.service.core;

import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.entity.PropType;
import com.jbond.ukffa.service.core.entity.Property;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class DeviceItemTest {

    @Test
    public void testCrudOperationsOnDeviceItem(){
        Group group1 = new Group(UUID.randomUUID(), null, "MainGroup");
        Group group2 = new Group(UUID.randomUUID(), group1.getId(), "MainGroup");
        Group group3 = new Group(UUID.randomUUID(), group1.getId(), "MainGroup");

        Device device1 = new Device(UUID.randomUUID(), 1, "Device-1", group2, true,
                "img1.png", "",null,false);
        Device device2 = new Device(UUID.randomUUID(), 2, "Device-2", group3, true,
                "img1.png", "",null,false);
        Device device3 = new Device(UUID.randomUUID(), 3, "Device-3", group1, true,
                "img1.png", "",null,false);

        Property property1 = new Property(UUID.randomUUID(), device1, false, PropType.Number, "AgUniqID", "11");
        Property property2 = new Property(UUID.randomUUID(), device2, false, PropType.Number, "AgUniqID", "22");
        Property property3 = new Property(UUID.randomUUID(), device3, false, PropType.Number, "AgUniqID", "33");

//        deviceItem1.getProperties().add(property1);
//        deviceItem2.getProperties().add(property2);
//        deviceItem3.getProperties().add(property3);

    }

}
