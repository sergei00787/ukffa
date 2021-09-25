package com.jbond.ukffa.service.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private UUID id;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "group_name")
    private String name;
}
