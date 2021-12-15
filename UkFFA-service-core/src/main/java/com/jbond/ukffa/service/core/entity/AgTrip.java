package com.jbond.ukffa.service.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Dictionary;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgTrip {
    @JsonProperty("Index")
    private int index;           // порядковый номер рейса (с 0)
    @JsonProperty("SD")
    private Date sd;         // дата/время начала рейса (в UTC)
    @JsonProperty("ED")
    private Date ed;         // дата/время конца рейса (в UTC)
    @JsonProperty("PointStart")
    private AgPoint pointStart;   // координата начала рейса
    @JsonProperty("PointEnd")
    private AgPoint pointEnd;     // координата конца рейса
    @JsonProperty("Stages")
    private AgTripStage[] stages; // отрезки для данного рейса
    @JsonProperty("Total")
    private Dictionary<String, Object> total;// параметры для РЕЙСА (визуально это колонки в таблице рейсов после колонок "Начало" и "Конец")
    @JsonProperty("Areas")
    private AgTripArea[] areas;   // полигоны обработанных полей (только для методов GetTripsArea, GetTripsAreaTotal, GetTripAreaItems)
}
