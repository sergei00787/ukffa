package com.jbond.ukffa.service.core.entity.agentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgTripStageItem {

    @JsonProperty("Index")
    private int index;       // Индекс отрезка (нумерация с 0)

    @JsonProperty("SD")
    private Date sd;     // дата/время начала отрезка (в UTC)

    @JsonProperty("ED")
    private Date ed;     // дата/время конца отрезка (в UTC)

    @JsonProperty("Status")
    private int status;      // ID статуса (0 = выключенный, >0 = порядковый номер статуса)

    @JsonProperty("StatusID")
    private String statusID;   // ID геозоны (если отрезок геозон)

    @JsonProperty("StatusIDs")
    private String[] statusIDs;// ID геозон (если есть перекрытия - тут может быть больше одного элемента)

    @JsonProperty("Caption")
    private String caption;  // имя статуса (Move, Park, ...), название геозоны (если отрезок геозон), ФИО водителя, ...

    @JsonProperty("Values")
    private Object[] values; // значения для колонок в табличке отрезка (количество и порядок соответствуют RTripStage.Params)
}
