package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Group group;

    private boolean allowed;
    private String image;
    private String imageColored;

    private boolean isAreaEnabled;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @JsonIgnoreProperties("device")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "device"})
    private List<Property> properties;

    public Device(UUID id) {
        this.id = id;
    }
}
