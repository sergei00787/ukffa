package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.DeviceItemService;
import com.jbond.ukffa.service.core.entity.DeviceItem;
import com.jbond.ukffa.service.core.entity.GroupItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;


@RestController
@RequestMapping(DeviceItemController.BASE_URI)
@Api(value = "API to shopping list", produces = "application/json")
@RequiredArgsConstructor
public class DeviceItemController {

    protected static final String BASE_URI = "/api/v1/deviceitem";

    private final DeviceItemService deviceItemService;

    @ApiOperation(value = "Create new device item", produces = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createDeviceItem(UriComponentsBuilder uriComponentsBuilder,
                                       @RequestBody DeviceItem deviceItem) {
        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/{deviceItemId}")
                .buildAndExpand(
                        deviceItemService.createDeviceItem(deviceItem.getId(), deviceItem.getSerial(),
                                deviceItem.getName(), deviceItem.getGroupItem(),deviceItem.isAllowed(), deviceItem.getImage(),
                                deviceItem.getImageColored(), deviceItem.isAreaEnabled())
                );

        return created(uriComponents.toUri()).build();
    }

}