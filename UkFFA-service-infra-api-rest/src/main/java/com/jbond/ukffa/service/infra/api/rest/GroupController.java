package com.jbond.ukffa.service.infra.api.rest;


import com.jbond.ukffa.service.api.GroupService;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.infra.jpa.GroupJpaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.ResponseEntity.created;


@RestController
@RequestMapping(GroupController.BASE_URI)
@Api(value = "API to Group Items", produces = "application/json")
@RequiredArgsConstructor
public class GroupController {

    protected static final String BASE_URI = "/api/v1/groups";

    private final GroupService groupService;
    private final GroupJpaService groupJpaService;

    @ApiOperation(value = "Get all groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAllGroups(){
        return groupJpaService.getAllGroups();
    }

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