package com.jbond.ukffa.service.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jbond.ukffa.service.core.entity.Group;
import com.jbond.ukffa.service.core.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
public class DeviceDTO {
    private final UUID id;
    private int serial;
    private String name;

    private Group group;

    private boolean allowed;
    private String image;
    private String imageColored;
    private boolean isAreaEnabled;

    public DeviceDTO(UUID id) {
        this.id = id;
    }
}
