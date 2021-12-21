package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.*;
import com.jbond.ukffa.service.core.utility.AgDateUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AgDataService implements IAgDataService {
    public String baseAgUrl;

    public AgDataService(String baseUrl){
        this.baseAgUrl = baseUrl;
    }

    @Override
    public AgFindDevice[] findDevicesByRegNumber(String token, String schemaId, String regNumber) throws JsonProcessingException {
        String agDeviceItem = WebClient.builder().baseUrl(this.baseAgUrl + "/FindDevices")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(64 * 1024 * 1024))
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("session", token)
                        .queryParam("schemaID", schemaId)
                        .queryParam("vehicleRegNumbers", regNumber)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(agDeviceItem, AgFindDevice[].class);
    }



    @Override
    public long getDurationMoveByTrips(AgTrips agtrips) {
        long sumDurationMove = 0L;
        for (AgTrip agTrip : agtrips.getTrips()) {
            sumDurationMove = sumDurationMove + getDurationMove(agTrip);
        }
        return sumDurationMove;
    }

    @Override
    public long getDurationMove(AgTrip agtrip) {
        return AgDateUtility.getSecondFromStringDuration((String) agtrip.getTotal().get("MoveDuration"));
    }

}
