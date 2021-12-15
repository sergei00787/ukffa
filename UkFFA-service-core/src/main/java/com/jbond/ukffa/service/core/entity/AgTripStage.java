package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Dictionary;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgTripStage {
    @JsonProperty("Name")
    private String name;                     // название отрезка

    @JsonProperty("Alias")
    private String alias;                    // алиас (некоторые параметры именуются через Alias), может быть пустой строкой

    @JsonProperty("Params")
    private String[] params;                 // список имён колонок в отрезке - например "Motion", "Daylight", "Speed", "Speed Avg"

    @JsonProperty("ParamTypes")
    private AgReturnType[] paramTypes;         // типы параметров

    @JsonProperty("Items")
    private AgTripStageItem[] items;          // строки отрезка (только если GetTrips, в GetTripsTotal == null)

    @JsonProperty("Statuses")
    private AgParameterStatus[] statuses;     // список доступных статусов для данного параметра/отрезка

    @JsonProperty("Total")
    private Dictionary<String, Object> total;// финальные данные по отрезку (как правило - суммарные данные по всем Items)

}
