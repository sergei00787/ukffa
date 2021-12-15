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
public class AgParameters {

    @JsonProperty("ID")
    private String id;                    // ID ТС

    @JsonProperty("FinalParams")
    private AgParameter[] finalParams;   // финальные (итоговые) параметры

    @JsonProperty("OnlineParams")
    private AgParameter[] onlineParams;  // онлайн (табличные) параметры

    @JsonProperty("TripsParams")
    private AgParameter[] tripsParams;   // рейсовые параметры
}
