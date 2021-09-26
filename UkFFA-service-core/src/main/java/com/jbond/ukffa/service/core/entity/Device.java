package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    private UUID id = UUID.randomUUID();

    private int serial;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    private boolean allowed;
    private String image;
    private String imageColored;

    private boolean isAreaEnabled;

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("device")
    private List<Property> properties;

    public Device(UUID id) {
        this.id = id;
    }
}
