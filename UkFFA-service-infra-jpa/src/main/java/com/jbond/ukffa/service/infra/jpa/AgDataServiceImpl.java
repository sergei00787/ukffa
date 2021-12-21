package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.*;
import com.jbond.ukffa.service.core.utility.AgDateUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgDataServiceImpl implements AgDataService {
    public String baseAgUrl;

    public AgDataServiceImpl(String baseUrl){
        this.baseAgUrl = baseUrl;
    }

    @Override
    public Mono<String> getMonoEnumAgDevice(String token, AgSchema schema) {
        return WebClient.builder().baseUrl(this.baseAgUrl + "/EnumDevices")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("session", token)
                        .queryParam("schemaID", schema.getId())
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice) throws JsonProcessingException {
        String responseStringAgSchemas = monoEnumAgDevice.block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseStringAgSchemas, AgEnumDevices.class);
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
