package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.DeviceService;
import com.jbond.ukffa.service.core.dto.DeviceDTO;
import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.infra.jpa.DeviceJpaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(DeviceController.BASE_URI)
@Api(value = "API to devices", produces = "application/json")
@RequiredArgsConstructor
public class DeviceController {

    protected static final String BASE_URI = "${spring.data.rest.base-path}/devices";

    private final DeviceService deviceService;
    private final DeviceJpaService deviceJpaService;

    @ApiOperation(value = "Get all devices", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> findAllDevices(){
        return deviceJpaService.findAllDevice();
    }

    @ApiOperation(value = "Get device by Id", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{device_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Device getDeviceById(@PathVariable UUID device_id){
        return deviceJpaService.findDeviceById(device_id);
    }


    @ApiOperation(value = "Create new device item", produces = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createDevice(UriComponentsBuilder uriComponentsBuilder,
                                       @RequestBody DeviceDTO device) {

        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/")
                .buildAndExpand(
                        deviceService.createDeviceItem(device.getId(), device.getSerial(),
                                device.getName(), device.getGroup(), device.isAllowed(), device.getImage(),
                                device.getImageColored(), device.isAreaEnabled())
                );

        return created(uriComponents.toUri()).build();
    }

}