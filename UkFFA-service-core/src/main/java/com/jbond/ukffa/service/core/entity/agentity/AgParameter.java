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
public class AgParameter {

    @JsonProperty("Name")
    private String name;              // имя параметра (внутренее название, латиница - например Daylight, Speed, ...)

    @JsonProperty("Caption")
    private String caption;           // название параметра (например "Дн. освещ", "Скорость", ...)

    @JsonProperty("Alias")
    private String alias;             // алиас параметра, может быть пустой строкой

    @JsonProperty("ReturnType")
    private AgReturnType returnType;    // тип параметра (см. ниже)

    @JsonProperty("ValueType")
    private AgAddValueType valueType;   // модификатор параметра (см. ниже)

    @JsonProperty("Unit")
    private String unit;              // ед.измерения параметра (км/ч, км, кг, ...)

    @JsonProperty("Format")
    private String format;            // форматирование параметра (dd.MM.yyyy, ...)

    @JsonProperty("Statuses")
    private AgParameterStatus[] statuses;// список статусов параметра.
    // например для параметра Motion (Движение) возможны три статуса - Стоянка, Движение, Полёт)
}
