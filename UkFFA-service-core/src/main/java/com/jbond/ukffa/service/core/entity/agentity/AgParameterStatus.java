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
public class AgParameterStatus {

    @JsonProperty("Value")
    private int Value;                  // численное значение статуса

    @JsonProperty("Caption")
    private String Caption;             // название статуса

    @JsonProperty("ReferenceID")
    private String ReferenceID;           // если статус геозона или водитель - здесь хранится GUID этого объекта

    @JsonProperty("ReferenceIDs")
    private String[] ReferenceIDs;        // если геозоны с наложениями - здесь полный список геозон (максимум 4), в которых находится прибор
}
