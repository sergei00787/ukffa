package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.DeviceItemService;
import com.jbond.ukffa.service.core.entity.GroupItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                                       @RequestParam UUID deviceId,
                                       @RequestParam int serial,
                                       @RequestParam String name,
                                       @RequestParam GroupItem groupItem) {
        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/{deviceItemId}")
                .buildAndExpand(
                        deviceItemService.createDeviceItem(deviceId, serial, name, groupItem, true, "", "", true)
                );

        return created(uriComponents.toUri()).build();
    }

}