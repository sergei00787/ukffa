package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgEnumDevices {
    @JsonProperty("ID")
    private String id;         // идентификатор схемы

    @JsonProperty("Groups")
    private AgGroupItem[] groups;// все группы приборов в схеме

    @JsonProperty("Items")
    private AgDeviceItem[] items; // все ТС в схеме

    @JsonProperty("VirtualTrees")
    private AgVirtualTree[] virtualTrees;// виртуальные деревья в схеме для объектов типа "ТС"

    @JsonProperty("Assigned")
    public String assigned;// назначенное аутентифицированному пользователю виртуальное дерево из списка VirtualTrees
}
