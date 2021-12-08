package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgSchema {
    @Setter
    @JsonProperty("ID")
    private String id;

    @Getter
    @Setter
    @JsonProperty("Name")
    private String name;
}
