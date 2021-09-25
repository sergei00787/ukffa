package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.DeviceService;
import com.jbond.ukffa.service.core.entity.Device;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping(DeviceController.BASE_URI)
@Api(value = "API to shopping list", produces = "application/json")
@RequiredArgsConstructor
public class DeviceController {

    protected static final String BASE_URI = "${spring.data.rest.base-path}/devices";

    private final DeviceService deviceService;
    private final DeviceJpaService deviceJpaService;

    @ApiOperation(value = "Get all devices", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = BASE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Device> findAllDevices(){
        return deviceJpaService.findAllDevice();
    }

    @ApiOperation(value = "Create new device item", produces = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createDevice(UriComponentsBuilder uriComponentsBuilder,
                                       @RequestBody Device device) {
        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/{deviceId}")

                .buildAndExpand(
                        deviceService.createDeviceItem(device.getId(), device.getSerial(),
                                device.getName(), device.getGroup(), device.isAllowed(), device.getImage(),
                                device.getImageColored(), device.isAreaEnabled())
                );

        return created(uriComponents.toUri()).build();
    }

}