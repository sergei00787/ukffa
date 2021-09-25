package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.GroupService;
import com.jbond.ukffa.service.core.entity.Group;
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
@RequestMapping(GroupController.BASE_URI)
@Api(value = "API to Group Items", produces = "application/json")
@RequiredArgsConstructor
public class GroupController {

    protected static final String BASE_URI = "/api/v1/groups";

    private final GroupService groupService;

    @ApiOperation(value = "Create new group item", produces = "application/json", consumes = "application/json")
    @PostMapping("/")
    ResponseEntity<?> createDeviceItem(UriComponentsBuilder uriComponentsBuilder,
                                       @RequestBody Group group) {
        UriComponents uriComponents = uriComponentsBuilder
                .path(BASE_URI + "/{groupItem}")
                .buildAndExpand(
                        groupService.createGroupItem(group.getId(), group.getParentId(), group.getName())
                );

        return created(uriComponents.toUri()).build();
    }

}