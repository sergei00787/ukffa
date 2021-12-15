package com.jbond.ukffa.service.core.entity.agentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgPoint {

    @JsonProperty("Lat")
    private double lat;    // широта

    @JsonProperty("Lng")
    private double lng;    // долгота
}
