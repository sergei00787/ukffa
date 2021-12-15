package com.jbond.ukffa.service.core.entity.agentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgSchema {
    @Setter
    @JsonProperty("ID")
    private String id;

    @Getter
    @Setter
    @JsonProperty("Name")
    private String name;
}
