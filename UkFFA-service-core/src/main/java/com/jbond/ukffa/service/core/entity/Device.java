package com.jbond.ukffa.service.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

//    private Point FixedLocation;

    private String image;
    private String imageColored;

    @OneToMany(mappedBy = "device")
    List<Property> properties = new ArrayList<>();

//    List<TripSplitter> tripSplitters;

    private boolean isAreaEnabled;

    public Device(UUID id) {
        this.id = id;
    }
}
