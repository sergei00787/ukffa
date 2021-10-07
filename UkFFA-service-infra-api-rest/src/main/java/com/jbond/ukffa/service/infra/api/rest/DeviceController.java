package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.core.entity.Device;
import com.jbond.ukffa.service.infra.jpa.DeviceJpaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(DeviceController.BASE_URI)
@Api(value = "API to devices", produces = "application/json")
@RequiredArgsConstructor
public class DeviceController {

    protected static final String BASE_URI = "${spring.data.rest.base-path}/devices";

    private final DeviceJpaService deviceJpaService;

    @ApiOperation(value = "Get all devices", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> findAllDevices() {
        return deviceJpaService.findAllDevice();
    }

    @ApiOperation(value = "Get device by Id", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{device_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Device getDeviceById(@PathVariable UUID device_id) {
        return deviceJpaService.findDeviceById(device_id);
    }


    @ApiOperation(value = "Create new device item", produces = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createDevice(@RequestBody Device device) {
        UUID createdId = deviceJpaService.createDevice(device);
        ResponseEntity<?> responseEntity;

        if (createdId == null) {
            responseEntity = new ResponseEntity<>("Bad request on created devece - " + device.getId(), HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity<>(device, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete device item")
    @DeleteMapping("/{device_id}")
    public void deleteDeviceByID(@PathVariable UUID device_id) {
        deviceJpaService.deleteDeviceById(device_id);
    }

    @ApiOperation(value = "Delete All devices")
    @DeleteMapping("/")
    public void deleteAllDevices(){
        deviceJpaService.deleteAllDevices();
    }

    @ApiOperation(value = "Delete list devices", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDevices(@RequestParam ArrayList<UUID> listDeviceIds){
        deviceJpaService.deleteDevices(listDeviceIds);
    }


}