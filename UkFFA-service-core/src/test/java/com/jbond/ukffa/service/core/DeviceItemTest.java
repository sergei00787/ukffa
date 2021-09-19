package com.jbond.ukffa.service.core;

import com.jbond.ukffa.service.core.entity.DeviceItem;
import com.jbond.ukffa.service.core.entity.GroupItem;
import com.jbond.ukffa.service.core.entity.PropType;
import com.jbond.ukffa.service.core.entity.Property;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class DeviceItemTest {

    @Test
    public void testCrudOperationsOnDeviceItem(){
        GroupItem groupItem1 = new GroupItem(UUID.randomUUID(), null, "MainGroup");
        GroupItem groupItem2 = new GroupItem(UUID.randomUUID(), groupItem1.getId(), "MainGroup");
        GroupItem groupItem3 = new GroupItem(UUID.randomUUID(), groupItem1.getId(), "MainGroup");

        DeviceItem deviceItem1 = new DeviceItem(UUID.randomUUID(), 1, "Device-1", groupItem2, true,
                "img1.png", "",null,false);
        DeviceItem deviceItem2 = new DeviceItem(UUID.randomUUID(), 2, "Device-2", groupItem3, true,
                "img1.png", "",null,false);
        DeviceItem deviceItem3 = new DeviceItem(UUID.randomUUID(), 3, "Device-3", groupItem1, true,
                "img1.png", "",null,false);

        Property property1 = new Property(UUID.randomUUID(),deviceItem1, false, PropType.Number, "AgUniqID", "11");
        Property property2 = new Property(UUID.randomUUID(),deviceItem2, false, PropType.Number, "AgUniqID", "22");
        Property property3 = new Property(UUID.randomUUID(),deviceItem3, false, PropType.Number, "AgUniqID", "33");

//        deviceItem1.getProperties().add(property1);
//        deviceItem2.getProperties().add(property2);
//        deviceItem3.getProperties().add(property3);

    }

}
