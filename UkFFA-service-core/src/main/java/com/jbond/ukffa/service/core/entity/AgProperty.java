package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgProperty {
    @JsonProperty("Inherited")
    private boolean inherited;     // унаследованное (true) или собственное (false) свойство
    @JsonProperty("Type")
    private PropType type;     // тип свойства
    @JsonProperty("Name")
    private String name;        // название свойства
    @JsonProperty("Value")
    private Object value;       // значение (типа свойства зависит от поля Type)
}
