package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.DeviceItemService;
import com.jbond.ukffa.service.api.PropertyService;
import com.jbond.ukffa.service.core.entity.Property;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.ResponseEntity.created;


@RestController
@RequestMapping(PropertyController.BASE_URI)
@Api(value = "API to Group Items", produces = "application/json")
@RequiredArgsConstructor
public class PropertyController {

    protected static final String BASE_URI = "/api/v1/properties";

    private final PropertyService propertyService;
    private final DeviceItemService deviceItemService;

    @ApiOperation(value = "Create new property", produces = "application/json", consumes = "application/json")
    @PostMapping("/")
    ResponseEntity<Property> createProperty(UriComponentsBuilder uriComponentsBuilder,
                                     @RequestBody Property property) {
        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/{groupItem}")
                .buildAndExpand(
                        deviceItemService.addPropertyToDeviceItem(property.getDeviceItem().getId(), property),
                        propertyService.createProperty(property.getId(), property.getDeviceItem().getId(),
                                property.isInherited(), property.getPropType(), property.getName(), property.getValue())
                );

        return created(uriComponents.toUri()).build();
    }

}