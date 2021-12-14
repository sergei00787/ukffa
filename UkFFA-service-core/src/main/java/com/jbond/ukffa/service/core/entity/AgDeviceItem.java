package com.jbond.ukffa.service.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgDeviceItem extends AgGroupItem{
    @JsonProperty("Serial")
    private int Serial;             // номер прибора

    @JsonProperty("Allowed")
    private boolean allowed;            // доступен или нет ( = включён ли этот прибор в ключ сервера)

    @JsonProperty("Properties")
    private List<AgProperty> properties;  // список свойств прибора (включая унаследованные)

    @JsonProperty("FixedLocation")
    private AgPoint fixedLocation;   // если !=null - данный прибор является стационарным и это его координаты (широта/долгота)

    @JsonProperty("Image")
    private String image;           // имя файла с изображением прибора (.png)

    @JsonProperty("ImageColored")
    private String imageColored;    // имя файла со всем модификаторами цвета или размера - например для картинки sedan.png здесь будет путь '''sedan2/default/transparent/32/-1/FF0000'''

    @JsonProperty("TripSplitters")
    private List<AgTripSplitter> tripSplitters;// делители на рейсы для этого прибора

    @JsonProperty("IsAreaEnabled")
    private boolean isAreaEnabled;     // включена или нет обработка полей на данном приборе
}
