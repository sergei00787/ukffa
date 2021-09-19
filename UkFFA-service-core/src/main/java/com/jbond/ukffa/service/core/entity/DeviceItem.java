package com.jbond.ukffa.service.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceItem {
    @Id
    private UUID id = UUID.randomUUID();

    private int serial;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private GroupItem groupItem;

    private boolean allowed;

//    private Point FixedLocation;

    private String image;
    private String imageColored;

//    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "deviceItem")
//    List<Property> properties = new ArrayList<Property>();
    List<Property> properties;
//    List<TripSplitter> tripSplitters;

    private boolean isAreaEnabled;

    public DeviceItem(UUID id) {
        this.id = id;
    }
}
