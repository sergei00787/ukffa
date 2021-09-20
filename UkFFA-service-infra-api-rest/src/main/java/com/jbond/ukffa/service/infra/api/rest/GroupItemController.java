package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.DeviceItemService;
import com.jbond.ukffa.service.api.GroupItemService;
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
@RequestMapping(GroupItemController.BASE_URI)
@Api(value = "API to Group Items", produces = "application/json")
@RequiredArgsConstructor
public class GroupItemController {

    protected static final String BASE_URI = "/api/v1/groupitem";

    private final GroupItemService groupItemService;

    @ApiOperation(value = "Create new group item", produces = "application/json", consumes = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createDeviceItem(UriComponentsBuilder uriComponentsBuilder,
                                       @RequestBody GroupItem groupItem){
        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/{groupItem}")
                .buildAndExpand(
                        groupItemService.createGroupItem(groupItem.getId(), groupItem.getParentId(), groupItem.getName())
                );

        return created(uriComponents.toUri()).build();
    }

}