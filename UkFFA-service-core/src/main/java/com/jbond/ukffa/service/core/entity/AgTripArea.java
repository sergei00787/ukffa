package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgTripArea {

    @JsonProperty("Color")
    private Color color;          // цвет полигона

    @JsonProperty("Polygons")
    private double[][][] polygons;// массив полигонов, состоящий из массива точек, каждая из которых возвращается как массив из двухх элементов - широта и долгота
}
